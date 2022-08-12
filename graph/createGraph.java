import java.util.ArrayList;
import java.util.Scanner;

class Scratch {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    // If path exists from Vertex 1 to Vertex 2
    public static boolean hasPath(ArrayList<ArrayList<Edge>> graph, int src, int dest, boolean[] visited){
        if(src == dest) return true;
        visited[src] = true;
        for(Edge edg : graph.get(src)){
            if(visited[edg.nbr] == false){
                boolean hasNbrPath = hasPath(graph, edg.nbr, dest, visited);
                if (hasNbrPath == true) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int vces = 7; // 0, 1, 2, 3, 4, 5, 6, 7
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>(7);

        for (int i = 0; i < vces; i++) {
            graph.add(new ArrayList<Edge>());
        }

        graph.get(0).add(new Edge(0,3,40));
        graph.get(0).add(new Edge(0,1,10));

        graph.get(1).add(new Edge(1,0,10));
        graph.get(1).add(new Edge(1,2,10));

        graph.get(2).add(new Edge(2,3,10));
        graph.get(2).add(new Edge(2,1,10));

        graph.get(3).add(new Edge(3,0,40));
        graph.get(3).add(new Edge(3,2,10));
        graph.get(3).add(new Edge(3,4,2));

        graph.get(4).add(new Edge(4,3,2));
        graph.get(4).add(new Edge(4,5,3));
        graph.get(4).add(new Edge(4,6,3));

        graph.get(5).add(new Edge(5,4,3));
        graph.get(5).add(new Edge(5,6,3));

        graph.get(6).add(new Edge(6,5,3));
        graph.get(6).add(new Edge(6,4,8));

        //System.out.println(graph);
        System.out.println(hasPath(graph,0,6, new boolean[vces]));
    }
}
