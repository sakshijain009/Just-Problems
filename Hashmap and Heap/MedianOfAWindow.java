import java.util.*;

class Median {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 4;
        System.out.println(medianSlidingWindow(nums, k));
    }

    public static List<Double> medianSlidingWindow(int[] nums, int k) {
        List<Double> result = new ArrayList<>();

        // Max heap (left half)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Min heap (right half)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // Add number to correct heap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance heaps
            balance(maxHeap, minHeap);

            // Remove the element that is out of the window
            if (i >= k) {
                int toRemove = nums[i - k];
                if (toRemove <= maxHeap.peek()) {
                    maxHeap.remove(toRemove); // O(n)
                } else {
                    minHeap.remove(toRemove); // O(n)
                }
                balance(maxHeap, minHeap);
            }

            // Add median to result when window is full
            if (i >= k - 1) {
                if (k % 2 == 0) {
                    result.add((maxHeap.peek() + minHeap.peek()) / 2.0);
                } else {
                    result.add((double) maxHeap.peek());
                }
            }
        }

        return result;
    }

    private static void balance(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        // Make sure maxHeap has at most one more element than minHeap
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
}
