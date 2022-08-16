import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Scratch {
  
    //Edge Class
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

    // Pair Class
    static class Pair {
        int v;
        String psf; // Path So Far

        Pair(int v, String psf){
            this.v = v;
            this.psf = psf;
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
            6
            0 1 10
            1 2 10
            2 3 10
            3 4 10
            4 5 10
            5 6 10
         */

        boolean[] visited = new boolean[vtces];
        for (int i = 0; i < vtces; i++) {
            if (visited[i] == false){
                boolean cycle = isCyclic(graph,i,visited);
                if (cycle){
                    System.out.println(true);
                    return;
                }
            }
        }
        System.out.println(false);
    }

    // FINDING CYCLE IN GRAPH USING ------> BFS
    public static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited){
        ArrayDeque<Pair> q = new ArrayDeque<Pair>();
        q.add(new Pair(src,src+""));

        while (q.size() > 0){
            Pair rem = q.removeFirst();

            if (visited[rem.v]==true){
                return true;
            }
            visited[rem.v] = true;

            for (Edge e: graph[rem.v]){
                if (visited[e.nbr]==false){
                    q.add(new Pair(e.nbr, rem.psf+e.nbr));
                }
            }
        }
        return false;
    }

}
