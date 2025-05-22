import java.util.*;

public class Main {

    public static int leastInterval(char[] tasks, int n) {
        // Map to count frequency of each task
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }

        // Max heap (priority queue) to store tasks by descending frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq =
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(taskCount.entrySet());

        int finalTime = 0;
        int cycle = n + 1;

        while (!pq.isEmpty()) {
            int time = 0;
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();

            // Process up to 'cycle' tasks in one round
            for (int i = 0; i < cycle; i++) {
                if (!pq.isEmpty()) {
                    Map.Entry<Character, Integer> current = pq.poll();
                    current.setValue(current.getValue() - 1); // Decrement count
                    tempList.add(current);
                    time++;
                }
            }

            // Push remaining tasks (with non-zero counts) back into the queue
            for (Map.Entry<Character, Integer> entry : tempList) {
                if (entry.getValue() > 0) {
                    pq.offer(entry);
                }
            }

            // If there are still tasks left, we need a full cycle of time
            // Otherwise, only use the actual time spent
            finalTime += !pq.isEmpty() ? cycle : time;
        }

        return finalTime;
    }

    public static void main(String[] args) {
        /*
         * Problem: Given an array of tasks represented by characters and a cooldown period n,
         * return the least number of time units required to finish all tasks with the cooldown constraint.
         */

        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        int n = 2;

        int result = leastInterval(tasks, n);
        System.out.println("Least interval needed: " + result);
    }
}
