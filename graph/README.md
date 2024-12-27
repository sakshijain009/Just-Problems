# Graphs
A Graph is a non-linear data structure consisting of vertices and edges. The vertices are sometimes also referred to as nodes and the edges are lines or arcs that connect any two nodes in the graph. More formally a Graph is composed of a set of vertices( V ) and a set of edges( E ). The graph is denoted by G(V, E).

The two most common ways to represent graphs are :
- Adjacency Matrix
- Adjacency List

## Depth First Search (DFS)
Depth first Search or Depth first traversal is a recursive algorithm for searching all the vertices of a graph or tree data structure. Traversal means visiting all the nodes of a graph. When we traverse an adjacent vertex, we finish the traversal of all vertices reachable through that adjacent vertex. After we finish traversing one adjacent vertex and its reachable vertices, we move to the next adjacent vertex and repeat the process. This is similar to a tree, where we first completely traverse the left subtree and then move to the right subtree. The key difference is that, unlike trees, graphs may contain cycles (a node may be visited more than once). To avoid processing a node multiple times, we use a boolean visited array.

### Hamiltonian Cycle
Hamiltonian Cycle or Circuit in a graph G is a cycle that visits every vertex of G exactly once and returns to the starting vertex.

### Hamiltonian Path
Hamiltonian Path in a graph G is a path that visits every vertex of G exactly once and Hamiltonian Path doesn’t have to return to the starting vertex. It’s an open path.

## Breadth First Search (BFS)
Breadth-first search is a graph traversal algorithm that starts traversing the graph from the root node and explores all the neighboring nodes. Then, it selects the nearest node and explores all the unexplored nodes. BFS puts every vertex of the graph into two categories - visited and non-visited. It selects a single node in a graph and, after that, visits all the nodes adjacent to the selected node.

### Cyclic Graph
- A cyclic graph contains one or more cycles or closed paths, which means that you can traverse the graph and end up where you started.
- A cyclic graph may have multiple cycles of different lengths and shapes. Some cycles may be contained within other cycles. A cyclic graph can be either directed or undirected.
- In a directed cyclic graph, the edges have a direction, and the cycle must follow the direction of the edges. In an undirected cyclic graph, the edges have no direction, and the cycle can go in any direction.
- A cyclic graph is bipartite if and only if all its cycles are of even length.

