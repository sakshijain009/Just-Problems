import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.*;

class Problem {
    public int[][] merge(int[][] intervals) {
        Pair[] pairs = new Pair[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            pairs[i] = new Pair(intervals[i][0],intervals[i][1]);
        }
        Stack<Pair> st =  new Stack<>();

        Arrays.sort(pairs);

        for (int i = 0; i < intervals.length; i++) {
            if (st.empty()) st.push(pairs[i]);
            else{
                if (st.peek().x <= pairs[i].y){
                    st.peek().x = st.peek().x < pairs[i].x?st.peek().x:pairs[i].x;
                }else st.push(pairs[i]);
            }
        }
        int[][] ans = new int[st.size()][2];
        int i=0;
        while (!st.empty()){
            ans[i][0] = st.peek().x;
            ans[i][1] = st.peek().y;
            st.pop();
            i++;
        }

        return ans;
    }

    public static class Pair implements Comparable<Pair>{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(Pair other){
            if (this.y != other.y){
                return other.y - this.y;
            }else {
                return this.y - other.y;
            }
        }
    }

    // Method 2
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];

        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                // No overlap, add interval
                merged.add(interval);
            } else {
                // Overlap, merge by updating end
                merged.get(merged.size() - 1)[1] = Math.max(
                    merged.get(merged.size() - 1)[1], interval[1]
                );
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}



