import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
    PRIM'S ALGORITHM - FOR MST (MINIMUM SPANNING TREE)
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

    static class Pair implements Comparable<Pair> {
        int v; // Vertex
        int av; // Acquiring Vertex
        int wt; // Weight

        Pair(int v, int av, int wt){
            this.v = v;
            this.av = av;
            this.wt = wt;
        }

        public int compareTo(Pair o){
            return this.wt - o.wt;
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
            7
            8
            0 3 25
            0 1 10
            1 2 10
            2 3 10
            3 4 2
            4 5 3
            5 6 3
            4 6 8
         */

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0,-1,0));
        boolean[] visited = new boolean[vtces]; // Stores level of the vertex

        while (pq.size() > 0){
            Pair rem = pq.remove();

            if (visited[rem.v] == true){
                continue;
            }

            visited[rem.v] = true;

            if (rem.av != -1){ // Source Node
                System.out.println("[ " + rem.av + " , " + rem.v + " ] : Weight = " + rem.wt);
            }

            for (Edge e: graph[rem.v]){
                if (visited[e.nbr] == false){
                    pq.add(new Pair(e.nbr, rem.v, e.wt));
                }
            }
        }
    }
}
