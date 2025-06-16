# 🧠 Understanding `HashMap` in Java

## 📘 Overview

A `HashMap` in Java stores key-value pairs and offers **average-case constant-time** (`O(1)`) performance for `put()` and `get()` operations. It is part of the Java Collections Framework and is widely used due to its efficiency.


## ⚙️ Internal Working

### 1. **Underlying Data Structure**
- `HashMap` uses an **array of buckets** internally.
- Each bucket stores entries using:
  - A **Linked List** (Java 7 and earlier).
  - A **Red-Black Tree** (Java 8 and later, if a bucket has more than 8 entries).


### 2. **Hashing the Key**

When you insert a key-value pair:
```java
map.put(key, value);
```

Java computes:

```java
int hash = key.hashCode();
int index = (n - 1) & hash; // n = array length
```

This determines which bucket to place the key-value pair in.

### 3. Collision Handling
If two keys have the same bucket index, Java places both entries in the same bucket:

- Using a Linked List, or
- A Balanced Tree (for better performance when many collisions occur).

To distinguish keys, it uses key.equals().

### 4. Load Factor & Resizing
The load factor in a HashMap is a measure that determines when the map should resize (rehash) to maintain performance. 
- A higher load factor means more entries per bucket (saves space, slower lookups).
- A lower load factor means fewer entries per bucket (faster lookups, more memory use).

Load Factor = (Number of entries) / (Number of buckets in the hash table).

Capacity refers to the number of buckets (slots) available in the internal array of the HashMap for storing key-value pairs.

```java
threshold = capacity × loadFactor
```
Default load factor: 0.75

Once the number of entries exceeds the threshold:

- The HashMap doubles its capacity.
- All existing entries are rehashed and redistributed.

#### Example:
Capacity = 16

Load factor = 0.75

Resize threshold = 16 × 0.75 = 12

So when you add the 13th entry, the HashMap resizes to capacity 32.

#### ✅ Key Points:
Initial Capacity: The number of buckets the HashMap starts with. You can set it manually:

```java
new HashMap<>(16);  // 16 is the initial capacity
```

**Why is it a power of 2?**  Java always keeps the capacity a power of 2 (e.g., 16, 32, 64, ...).

This enables fast computation of the index using:
```java
index = (n - 1) & hash
```
instead of hash % n — a faster bitwise operation.


#### 📈 Effects of Load Factor

| Load Factor | Pros                         | Cons                        |
|-------------|------------------------------|-----------------------------|
| 1.0         | Less memory overhead         | More collisions (slower)   |
| 0.75        | Balanced (default in Java)   | -                           |
| 0.5         | Fewer collisions (faster)    | More memory usage           |

#### 🤔 But why not use all 16 buckets first?
Because HashMap doesn't just care about available buckets — it cares about performance. More entries per bucket → more collisions → worse lookup time. Java uses load factor to balance between:

Space efficiency (not growing too early)

Time efficiency (keeping O(1) average time for get()/put())

#### 📦 Analogy
Imagine you have 16 bins (buckets) for books. If bins get more than 75% full (12 books), you get a bigger shelf with 32 bins. Why? Because if too many books pile into one bin, it's harder to find a specific one.

### 5. Key Comparison (equals):

Even if two keys have the same hash code, Java uses .equals() to ensure they’re actually equal before replacing the value.

#### Retrieval (get(key)):

- Hash code is computed → index is found → bucket is searched.
- If collision: loop through the list or search the tree → match using .equals().

### 6. ✅ Performance

| Operation | Average Time | Worst Case |
|-----------|---------------|-------------|
| get()     | O(1)          | O(n)        |
| put()     | O(1)          | O(n)        |
| remove()  | O(1)          | O(n)        |

> 💡 Worst-case happens when all entries land in the same bucket (rare due to good hash distribution).

