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
}



