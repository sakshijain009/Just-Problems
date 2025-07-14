# 🔗 Linked List: Complete Guide

## 📌 What is a Linked List?

A **Linked List** is a linear data structure where elements (called **nodes**) are connected using **pointers**.

Each node contains:
- **Data** (value)
- **Pointer** to the next node

Unlike arrays, linked lists do not store elements in contiguous memory, making insertion/deletion more efficient.


## 🧱 Types of Linked Lists

### 1. Singly Linked List
Each node points to the **next** node.

### 2. Doubly Linked List
Each node has pointers to the **next** and **previous** nodes.

### 3. Circular Linked List
The last node points back to the **head** instead of `null`.

- **Singly Circular**: last → first
- **Doubly Circular**: first.prev = last, last.next = first

## ✅ Advantages

- Dynamic size (no need to predefine size)
- Efficient insertion/deletion (especially in the middle)
- Better memory utilization (no fixed size)

## ⚠️ Disadvantages

- No random access (must traverse to access an element)
- Extra memory for pointers
- Slower lookup compared to arrays

## ⏱ Time Complexities

| Operation         | Singly Linked List | Doubly Linked List |
|------------------|--------------------|---------------------|
| Insertion (Head) | O(1)               | O(1)                |
| Insertion (Tail) | O(n)*              | O(1)**              |
| Deletion (Head)  | O(1)               | O(1)                |
| Deletion (Tail)  | O(n)               | O(1)                |
| Search           | O(n)               | O(n)                |

\* unless tail pointer is maintained  
\** if tail pointer is maintained


## 🔧 Basic Operations

- Insertion (head, tail, middle)
- Deletion (head, tail, by value)
- Traversal
- Reversal
