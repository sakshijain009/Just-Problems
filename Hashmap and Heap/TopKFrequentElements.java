import java.util.*;

public class Main {

    // Method to find the top K frequent elements
    public ArrayList<Integer> topKFrequent(int[] arr, int k) {
        
        // Min-heap to keep top K frequent elements (based on frequency and value)
        Queue<Pair> queue = new PriorityQueue<>(k);
        
        // HashMap to count frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Result list to store top K elements
        ArrayList<Integer> ans = new ArrayList<>();
        
        // Step 1: Count frequency of each number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use min-heap to maintain top K frequent elements
        for (int num : map.keySet()) {
            Pair current = new Pair(num, map.get(num));

            if (queue.size() < k) {
                // If heap is not full, simply add the current pair
                queue.add(current);
            } else {
                Pair temp = queue.peek();
                
                // If current has higher frequency or same freq but larger value, replace the root
                if ((map.get(num) > temp.freq) ||
                    (map.get(num) == temp.freq && num > temp.num)) {
                    queue.poll(); 
                    queue.add(current); 
                }
            }
        }

        // Step 3: Extract from heap to result list
        while (!queue.isEmpty()) {
            ans.add(queue.poll().num);
        }

        // Optional: Reverse to get result in descending order of importance
        Collections.reverse(ans);

        return ans;
    }

    class Pair implements Comparable<Pair> {
        int num;
        int freq;

        public Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.freq == o.freq) {
                // If frequency is same, keep the smaller number first
                // So that in the heap, larger numbers are retained
                return this.num - o.num;
            }
            // Otherwise, compare by frequency (min-heap)
            return this.freq - o.freq;
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        int[] input = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(obj.topKFrequent(input, k)); // Output: [2, 1]
    }
}
