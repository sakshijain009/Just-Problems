import java.util.ArrayList;
import java.util.Comparator;

class Main {
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

    // If path exists from Vertex 1 to Vertex 2 Print the path
    public static void printAllPaths(ArrayList<ArrayList<Edge>> graph, int src, int dest, boolean[] visited, String pathSoFar){
        if(src == dest) System.out.println(pathSoFar);

        visited[src] = true;
        for(Edge edg : graph.get(src)){
            if(!visited[edg.nbr]){
                printAllPaths(graph, edg.nbr, dest, visited, pathSoFar+" "+edg.nbr);
            }
        }

        visited[src] = false;
    }

    public static void printAllPathsLexographically(ArrayList<ArrayList<Edge>> graph, int src, int dest, boolean[] visited, String pathSoFar){
        if(src == dest) System.out.println(pathSoFar);

        visited[src] = true;

        ArrayList<Edge> sortedListofNbrs = graph.get(src);
        sortedListofNbrs.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.nbr, o2.nbr);

            /*  if (o1.nbr == o2.nbr) {   (Integer.compare is same as this)
                    return 0;
                } else if (o1.nbr > o2.nbr) {
                    return 1;
                } else {
                    return -1;
                }  */
            }
        });
        for(Edge edg : sortedListofNbrs){
            if(!visited[edg.nbr]){
                printAllPaths(graph, edg.nbr, dest, visited, pathSoFar+" "+edg.nbr);
            }
        }

        visited[src] = false;
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

        System.out.println("Print All Paths");
        printAllPaths(graph,0,6, new boolean[vces], "0");

        System.out.println("\nPrint All Paths Lexographically");
        printAllPathsLexographically(graph,0,6, new boolean[vces], "0");
    }
}
