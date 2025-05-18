import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/*
 WHEN IS A GRAPH BIPARTITE?
    Case 1: Graph is Acyclic ----> There is no cycle
    Case 2: Graph is Cyclic and cycle has even edges
 */

class Scratch {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    static class Pair {
        int v;
        String psf; // Path So Far
        int level;

        Pair(int v, String psf, int level){
            this.v = v;
            this.psf = psf;
            this.level = level;
        }
    }

    // Main function
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for(int i = 0; i < vtces; i++){
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for(int i = 0; i < edges; i++){
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        /*
            EXAMPLE INPUT :
            5
            4
            4 0 1 (vertex 1, vertex 2, weight edge)
            0 1 1
            1 2 1
            2 3 1

            GRAPH : 4 ---- 0 ---- 1 ---- 2 ---- 3
            OUTPUT : True
         */

        int[] visited = new int[vtces]; // Stores level of the vertex
        Arrays.fill(visited, -1); // -1 when vertex not visited

        for (int i = 0; i < vtces; i++) {
            if (visited[i] == -1){
                boolean isComponentBipartite = checkComponentForBipartiteness(graph,i,visited);
                if (!isComponentBipartite){
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

    // FINDING IF GRAPH IS BIPARTITE
    public static boolean checkComponentForBipartiteness(ArrayList<Edge>[] graph, int src, int[] visited){
        ArrayDeque<Pair> q = new ArrayDeque<Pair>();
        q.add(new Pair(src,src+"", 0));

        while (q.size() > 0){
            Pair rem = q.removeFirst();

            if (visited[rem.v] != -1){ // Vertex is visited
                if (rem.level != visited[rem.v]){
                    return false;
                }
            }else{ // Vertex is not visited
                visited[rem.v] = rem.level;
            }

            for (Edge e: graph[rem.v]){
                if (visited[e.nbr]==-1){
                    q.add(new Pair(e.nbr, rem.psf+e.nbr, rem.level + 1));
                }
            }
        }
        return true;
    }

}
