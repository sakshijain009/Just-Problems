# Graphs
A Graph is a non-linear data structure consisting of vertices and edges. The vertices are sometimes also referred to as nodes and the edges are lines or arcs that connect any two nodes in the graph. More formally a Graph is composed of a set of vertices( V ) and a set of edges( E ). The graph is denoted by G(V, E).

The two most common ways to represent graphs are :
- Adjacency Matrix
- Adjacency List

#### Connected Components
Connected component in an undirected graph refers to a group of vertices that are connected to each other through edges, but not connected to other vertices outside the group.

For example in the graph shown below, {0, 1, 2} form a connected component and {3, 4} form another connected component.

![image](https://github.com/user-attachments/assets/1e712157-b7ed-4998-9666-1837b4f795af)


## Depth First Search (DFS)
Depth first Search or Depth first traversal is a recursive algorithm for searching all the vertices of a graph or tree data structure. Traversal means visiting all the nodes of a graph. When we traverse an adjacent vertex, we finish the traversal of all vertices reachable through that adjacent vertex. After we finish traversing one adjacent vertex and its reachable vertices, we move to the next adjacent vertex and repeat the process. This is similar to a tree, where we first completely traverse the left subtree and then move to the right subtree. The key difference is that, unlike trees, graphs may contain cycles (a node may be visited more than once). To avoid processing a node multiple times, we use a boolean visited array.
> Data Structure : Stack

### Hamiltonian Cycle
Hamiltonian Cycle or Circuit in a graph G is a cycle that visits every vertex of G exactly once and returns to the starting vertex.

### Hamiltonian Path
Hamiltonian Path in a graph G is a path that visits every vertex of G exactly once and Hamiltonian Path doesn’t have to return to the starting vertex. It’s an open path.

## Breadth First Search (BFS)
Breadth-first search is a graph traversal algorithm that starts traversing the graph from the root node and explores all the neighboring nodes. Then, it selects the nearest node and explores all the unexplored nodes. BFS puts every vertex of the graph into two categories - visited and non-visited. It selects a single node in a graph and, after that, visits all the nodes adjacent to the selected node.
> Data Structure : Queue

### Cyclic Graph
- A cyclic graph contains one or more cycles or closed paths, which means that you can traverse the graph and end up where you started.
- A cyclic graph may have multiple cycles of different lengths and shapes. Some cycles may be contained within other cycles. A cyclic graph can be either directed or undirected.
- In a directed cyclic graph, the edges have a direction, and the cycle must follow the direction of the edges. In an undirected cyclic graph, the edges have no direction, and the cycle can go in any direction.
- A cyclic graph is bipartite if and only if all its cycles are of even length.

### Bipartite Graph
A graph is called bipartite if it is possible to split it's vertices in two sets of mutually exclusive and exhaustive vertices such that all edges are across sets. Formally, a graph G = (V, E) is bipartite if and only if its vertex set V can be partitioned into two non-empty subsets X and Y, such that every edge in E has one endpoint in X and the other endpoint in Y.

A graph can be bipartite if
- It is acyclic
- It has a cycle of even size (Not if it has any cycle of odd size)

![image](https://github.com/user-attachments/assets/2d40c884-c62d-477b-a581-d7a9d7199210)

### Dijkstra’s Algorithm
It starts at the source vertex and iteratively selects the unvisited vertex with the smallest tentative distance from the source. It then visits the neighbors of this vertex and updates their tentative distances if a shorter path is found. This process continues until the destination vertex is reached, or all reachable vertices have been visited.
> Data Structure : Priority Queue

- Dijkstra’s algorithm is used to find the shortest path between two vertices in a weighted graph. It is not designed to find a minimum spanning tree.
- Dijkstra’s algorithm has a time complexity of O(V^2) for a dense graph and O(E log V) for a sparse graph
- Dijkstra’s algorithm does not work with graphs that have negative edge weights, as it assumes that all edge weights are non-negative.

### Minimum Spanning Tree (MST)
A spanning tree is defined as a tree-like subgraph of a connected, undirected graph that includes all the vertices of the graph. Or, to say in Layman’s words, it is a subset of the edges of the graph that forms a tree (acyclic) where every node of the graph is a part of the tree.

The minimum spanning tree has all the properties of a spanning tree with an added constraint of having the minimum possible weights among all possible spanning trees. Like a spanning tree, there can also be many possible MSTs for a graph.

- The number of vertices (V) in the graph and the spanning tree is the same.
- There is a fixed number of edges in the spanning tree which is equal to one less than the total number of vertices ( E = V-1 ).

A disconnected graph cannot have a spanning tree because it does not span all the vertices. Therefore, it also cannot have a minimum spanning tree. The spanning tree should be acyclic, which means there would not be any cycle in the tree.

#### Prim's Algorithm
This is a greedy algorithm. This algorithm has the following workflow:

- It starts by selecting an arbitrary vertex and then adding it to the MST. Then, it repeatedly checks for the minimum edge weight that connects one vertex of MST to another vertex that is not yet in the MST.
- This process is continued until all the vertices are included in the MST.

To efficiently select the minimum weight edge for each iteration, this algorithm uses priority_queue to store the vertices sorted by their minimum edge weight currently. It also simultaneously keeps track of the MST using an array or other data structure suitable considering the data type it is storing.

- Prim’s algorithm are designed for undirected graphs only.
- Prim’s algorithms has a time complexity of O(ElogE), where E is the number of edges in the graph.
