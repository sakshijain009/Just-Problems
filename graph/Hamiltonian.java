import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

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

        int src = Integer.parseInt(br.readLine());

        /*
            EXAMPLE INPUT :
            7
            9
            0 1 10
            1 2 10
            2 3 10
            0 3 10
            3 4 10
            4 5 10
            5 6 10
            4 6 10
            2 5 10
            0
         */

        HashSet<Integer> visited = new HashSet<>();
        Hamiltonian(graph,src,visited,src+"",src);

    }

    // Hamiltonian Path
    public static void Hamiltonian(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited, String path, int originalSource){
        if (visited.size() == graph.length-1){
            boolean closingEdge = false;

            for (Edge e: graph[src]){
                if(e.nbr == originalSource){
                    closingEdge = true;
                    break;
                }
            }

            if (closingEdge){
                System.out.println("Hamiltonian Cycle: "+path);
            }else{
                System.out.println("Hamiltonian Path: "+path);
            }
        }

        visited.add(src);
        for (Edge e: graph[src]){
            if (!visited.contains(e.nbr)){
                Hamiltonian(graph,e.nbr,visited,path+e.nbr,originalSource);
            }
        }
        visited.remove(src);
    }

}
