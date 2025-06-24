# 🌳 Tree Data Structure
Tree Data Structure is a non-linear data structure in which a collection of elements known as nodes are connected to each other via edges such that there exists exactly one path between any two nodes. It is a hierarchical structure that is used to represent and organize data in the form of parent-child relationship.
- Folder structure in an operating system.
- Tag structure in an HTML (root tag the as html tag) or XML document.

The topmost node of the tree is called the root, and the nodes below it are called the child nodes. Each node can have multiple child nodes, and these child nodes can also have their own child nodes, forming a recursive structure.

**Why Tree is considered a non-linear data structure?** The data in a tree are not stored in a sequential manner i.e., they are not stored linearly. Instead, they are arranged on multiple levels or we can say it is a hierarchical structure.

## 🧩 Key Terminology

| Term        | Meaning                                                          |
|-------------|------------------------------------------------------------------|
| **Node**    | An element of the tree                                           |
| **Root**    | Topmost node without a parent                                   |
| **Edge**    | Connection between one node and another                          |
| **Parent**  | A node that has children                                         |
| **Child**   | A node that has a parent                                         |
| **Leaf**    | A node with no children                                          |
| **Subtree** | A tree formed by a node and its descendants                      |
| **Depth**   | Number of edges from the root to a node                          |
| **Height**  | Number of edges in the longest path from a node to a leaf        |
| **Level**   | Depth of a node + 1                                              |
| **Degree**  | Number of children a node has                                    |
| **Sibling** | Nodes with the same parent                                       |

## Traversals
| Type      | Order                             |
|-----------|------------------------------------|
| Inorder   | Left → Root → Right (BST → Sorted) |
| Preorder  | Root → Left → Right                |
| Postorder | Left → Right → Root                |

```
      1
     / \
    2   3
   / \
  4   5

Inorder:     4 2 5 1 3
Preorder:    1 2 4 5 3
Postorder:   4 5 2 3 1
Level-order: 1 2 3 4 5
```

### Preorder
Preorder traversal is defined as a type of tree traversal that follows the Root-Left-Right policy where:

- The root node of the subtree is visited first.
- Then the left subtree  is traversed.
- At last, the right subtree is traversed.

### Postorder
Postorder traversal is defined as a type of tree traversal which follows the Left-Right-Root policy such that for each node:

- The left subtree is traversed first
- Then the right subtree is traversed
- Finally, the root node of the subtree is traversed

### LevelOrder
Level Order Traversal technique is defined as a method to traverse a Tree such that all nodes present in the same level are traversed completely before traversing the next level.
> using a Queue

##  Binary Search Tree (BST)
A **Binary Search Tree (BST)** is a special type of **binary tree** in which every node follows the property:

For any node N:

All values in the left subtree of N < N

All values in the right subtree of N > N

- Each node has **at most two children**.
- Left child < Parent < Right child.
- No duplicate values (in most implementations).
- Inorder traversal gives a **sorted list** of elements.
