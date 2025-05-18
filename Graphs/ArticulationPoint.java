class Solution
{
    static int[] parent;
    static int[] low;
    static int[] disc;
    static boolean[] visited;
    static boolean[] ar;
    static int time;
        
    public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        parent = new int[V];
        low = new int[V];
        disc = new int[V];
        visited = new boolean[V];
        ar = new boolean[V];
        time = 0;
        
        parent[0] = -1;
        dfs(0,adj);
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<V;i++){
            if(ar[i]) ans.add(i);
        }
        
        if(ans.size()==0) ans.add(-1);
        
        return ans;
    }
    
    public static void dfs(int src, ArrayList<ArrayList<Integer>> graph){
    
        disc[src] = low[src] = time;
        time++;
        int count=0;
        
        visited[src] = true;
        
        for(int nbr: graph.get(src)){
            if(parent[src]==nbr) continue;
            else if(visited[nbr]==true){
                low[src] = Math.min(low[src],disc[nbr]);
            }else{
                parent[nbr] = src;
                count++;
                dfs(nbr,graph);
                
                low[src] = Math.min(low[src],low[nbr]);
                if(parent[src]==-1){
                    if(count>=2) ar[src] = true;
                }else{
                    if(low[nbr]>=disc[src]) ar[src] = true;
                }
            }
        }
    }
}
