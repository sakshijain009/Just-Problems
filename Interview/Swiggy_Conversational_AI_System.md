# 🧠 Swiggy Conversational AI System Design – Deep-Dive Questions

## 🔍 Problem Statement

_Imagine Swiggy is building a new conversational AI assistant for its delivery partners. This assistant needs to handle multi-turn conversations, understand context across different interactions, and integrate with various internal systems. How would you design the core data model to store and manage the conversation context for such an agent, ensuring it's scalable, consistent, and handles sensitive information appropriately?_

### 🧭 Step 1: Understand the System Requirements

### 🔹 The Assistant Needs To:
- Support multi-turn conversations (track history and context)
- Understand context across interactions (sessions may span minutes, hours, or days)
- Integrate with internal systems (order info, routing, partner onboarding, payments)
- Be scalable (handle millions of delivery partners)
- Ensure data consistency and security (manage sensitive info like location, payment)

### ✅ 1. What is Conversation Context?

The assistant must retain structured memory across turns, including:

- **Identity** of the delivery partner (ID, language, region)
- **Dialog flow state** (current goal, sub-task progress)
- **Conversation memory** (recent user intents, bot actions)
- **External data references** (e.g., order ID, payout info)
- **Interaction flags** (e.g., retry attempts, handoff triggers)

### ✅ Context Includes:

| Context Type         | Example                                |
|----------------------|----------------------------------------|
| User Profile         | Partner ID, name, language, city       |
| Session Metadata     | Channel, timestamp, device info        |
| Conversation History | Previous turns, intents, entities      |
| Task/Goal State      | "Partner is checking payment status"   |
| External Data        | Order details, payout, delivery status |
| Flags/Memory         | "Partner asked this before", preferences |

### 🧱 2. Core Data Model Design

A **hybrid design** is used:

- **Fast in-memory store** (e.g., Redis) for active session context
- **Document store** (e.g., MongoDB/DynamoDB) for durability and querying
- **Time-to-live (TTL)** on session documents for cleanup

| Layer | Component            | Purpose                                       | Storage Type                    |
|-------|----------------------|-----------------------------------------------|----------------------------------|
| 1️⃣    | `ConversationSession` | Stores metadata for each session              | Relational DB (PostgreSQL/MySQL) |
| 2️⃣    | `ConversationTurn`    | Stores message logs and parsed NLU data       | Relational DB / S3 / BigQuery    |
| 3️⃣    | `ContextStore`        | Stores real-time conversation state & memory  | Redis / DynamoDB / MongoDB       |

#### 📄 1. `ConversationSession` Table

Stores metadata about the current session. Used for session tracking, cleanup, and analytics.

| Field               | Type      | Notes                                           |
|---------------------|-----------|-------------------------------------------------|
| `session_id`         | UUID      | Primary key                                     |
| `partner_id`         | String    | Delivery partner identifier                     |
| `start_time`         | Timestamp | Session start time                              |
| `last_activity_time` | Timestamp | Updated on each message to support TTL tracking |
| `channel`            | Enum      | e.g., `whatsapp`, `ivr`, `mobile_app`           |
| `current_task`       | String    | Optional — e.g., `"resolve_payment"`            |
| `language`           | String    | ISO language code (e.g., `en`, `hi`)            |
| `expires_at`         | Timestamp | Optional — can support fixed TTLs               |

#### 💬 2. `ConversationTurn` Table

Stores each turn (user or bot message) in the conversation. Used for replay, training, auditing.

| Field               | Type      | Notes                                              |
|---------------------|-----------|----------------------------------------------------|
| `turn_id`            | UUID      | Primary key                                        |
| `session_id`         | FK        | Foreign key → `ConversationSession.session_id`     |
| `timestamp`          | Timestamp | When the message was sent/received                 |
| `sender`             | Enum      | Either `'user'` or `'bot'`                         |
| `message_text`       | Text      | Raw user or bot message                            |
| `parsed_intent`      | String    | Intent detected by NLU system                      |
| `extracted_entities` | JSONB     | Example: `{"order_id": "ORD123", "date": "today"}` |
| `response_text`      | Text      | Agent's final response (if applicable)             |


#### 🧠 3. `ContextStore` – Active Context (Document / KV Store)

Used for live reasoning — this is the working memory of the assistant. TTL-based, high-speed access.

Example document (stored in Redis/MongoDB/DynamoDB):

```json
{
  "session_id": "sess-abc123",
  "partner_id": "P7689",
  "order_tracking": {
    "order_id": "ORD9823",
    "status": "out_for_delivery"
  },
  "goal_state": {
    "current_goal": "resolve_payment",
    "step": "verify_bank",
    "status": "in_progress"
  },
  "conversation_flags": {
    "is_repeat_query": true,
    "asked_about_payment": true,
    "awaiting_external_data": false
  },
  "short_term_memory": {
    "preferred_language": "Hindi",
    "last_user_intent": "ask_payment_status",
    "last_bot_action": "confirm_month"
  }
}
```

I would design a session-scoped JSON context model that includes the current goal state, conversation memory, external references, and runtime flags. Active sessions would be stored in Redis (with TTL) for low-latency access, backed by MongoDB or DynamoDB for durability. External systems like orders or payouts are accessed via APIs or Kafka listeners, with updates reflected in the context in real time. The system is scalable, fault-tolerant, and ensures secure handling of sensitive partner information through encryption, TTL cleanup, and tokenization.

## 2. Contact Store JSON Structure & Short-Term Memory

**Q:**  
_Let’s drill down. Regarding the "contact store document" for short-term memory and goal state: What would be the *structure* of this document? How would you represent the "goal state" and "short-term memory" within it, and how would the agent *update* this structure efficiently across turns?_

**Q:**  
_Also, you mentioned "optimized for read and write performance." How would your chosen schema and storage mechanisms specifically ensure scalability for millions of concurrent conversations and rapid context retrieval, especially considering the hybrid nature?_

---

## 3. Concurrent Updates and Data Consistency

**Q:**  
_Now, how does the agent *update* this complex JSON document efficiently and reliably across turns? Consider the implications for concurrent updates and data consistency, especially if multiple parts of the document need to change simultaneously._

---

## 4. Redis MULTI/EXEC and Optimistic Concurrency Control

**Q:**  
_Can you elaborate on how `MULTI/EXEC` in Redis specifically guarantees atomicity for multiple updates to a single context document? And for optimistic concurrency control, how would you implement that using versioning or timestamps to prevent lost updates when multiple agents or processes might try to modify the same session context concurrently?_

---

## 5. Implementing OCC with Versioning

**Q:**  
_Now, let's focus on the optimistic concurrency control you mentioned. How would you implement versioning or timestamps within your context document schema, and what would the exact read-modify-write sequence look like to leverage this for preventing lost updates, especially in a scenario where the agent might be running across multiple instances or services?_

---

## 6. Internal System Integrations (Order, Payments, etc.)

**Q:**  
_Now, let's consider a scenario where the agent needs to integrate with various internal systems, as mentioned in the job description. For example, fetching order details or updating delivery status. How would your context management system facilitate these integrations while maintaining data consistency and ensuring the conversation flow isn't disrupted by external system latencies or failures?_

---

## 7. Kafka for Real-Time Orchestration

**Q:**  
_However, the job description also emphasizes "real-time updates" and "system orchestration" using technologies like Apache Kafka. How would you leverage a message queue like Kafka within your context management system to handle these real-time updates from external systems and orchestrate complex multi-step interactions that might involve several external service calls, all while ensuring the conversation context remains consistent and responsive?_

---

## 8. Multi-Service Query via Kafka (Order XYZ)

**Q:**  
_"What's the status of order XYZ?" This involves querying an order service and potentially a logistics service. How would Kafka facilitate the real-time updates of the conversation context as these external service calls progress, and how would it help orchestrate the responses back to the agent, ensuring the user gets a coherent, real-time update without the agent waiting synchronously for all external calls to complete? Detail the Kafka topics, producers, and consumers involved._

---

## 9. Real-Time Push vs Polling for Agent Continuation

**Q:**  
_My concern is about the "real-time" aspect and ensuring the conversation remains fluid. If the agent responds with "I'm fetching your order status, I'll update you in a moment," how does the system *proactively* push the final, complete update to the agent's active conversation context without the agent having to poll or the user having to re-initiate the query? Specifically, how does the "context updater" consumer ensure the *active* agent instance handling that specific conversation receives the update immediately, and how does the agent then seamlessly integrate this into the ongoing dialogue?_

---

## 10. Notification Dispatcher / Session Manager Internals

**Q:**  
_I need more detail on the "notification dispatcher" or "session manager" component itself. Specifically, how does this component *know* which active agent instance (which server, which process ID) is currently handling a particular session ID? What data structure does it maintain to map session IDs to active agent instances, and how is this mapping kept up-to-date in a highly dynamic, distributed environment where agent instances can scale up or down, or even fail and restart?_

---
