import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        int k = s.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //by default highest priority of smallest element
        for (int i = 0; i < n; i++) {
            if (i<k){
                pq.add(arr[i]); //We only keep k elements in priority queue
            }else{
                if (arr[i] > pq.peek()){ //if array element is larger than the smallest element in queue
                    pq.remove(); //we remove that smallest element
                    pq.add(arr[i]); //and add the new array element
                }
            }
        }

        while (pq.size()>0){
            System.out.println(pq.remove());
        }
    }
}
