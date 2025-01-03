# Tree
Tree Data Structure is a non-linear data structure in which a collection of elements known as nodes are connected to each other via edges such that there exists exactly one path between any two nodes. It is a hierarchical structure that is used to represent and organize data in the form of parent-child relationship.
- Folder structure in an operating system.
- Tag structure in an HTML (root tag the as html tag) or XML document.

The topmost node of the tree is called the root, and the nodes below it are called the child nodes. Each node can have multiple child nodes, and these child nodes can also have their own child nodes, forming a recursive structure.

**Why Tree is considered a non-linear data structure?** The data in a tree are not stored in a sequential manner i.e., they are not stored linearly. Instead, they are arranged on multiple levels or we can say it is a hierarchical structure.

## Traversals
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

