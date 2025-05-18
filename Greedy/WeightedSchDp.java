import java.util.ArrayList;
import java.util.Arrays;

class Scheduling {
    public static void main(String[] args) {
        // start, finish, profit
        int[][] Job = {{3, 10, 20}, {1, 2, 50},{2, 100, 200}, {6, 19, 100} };
        InsertionSort(Job); //SORT ACCORDING TO FINISH TIME

        System.out.println("The max profit is : "+checkProfit(Job));

    }
    public static int checkProfit(int[][] Job){
        int n = Job.length;
        int[] store = new int[n];
        store[0]=Job[0][2];
        for (int i = 1; i < n; i++) {
            int current = Job[i][2];
            int out = findPrevJob(Job,i);
            if(out!=-1){
                current+=store[out];
            }
            store[i] = Math.max(current,store[i-1]);
        }

        return store[store.length-1];
    }

    public static int findPrevJob(int[][] Job,int i){
        for (int j = i-1; j>=0; j--) {
            if (Job[j][1]<= Job[i][0]) return j;
        }
        return -1;
    }

    public static void InsertionSort(int[][] job){
        int[] key,key1,key2;
        for (int i = 0; i < job.length; i++) {
            key = job[i];
            int j=i-1;
            while (j>=0 && job[j][1]>key[1]){
                job[j+1]=job[j];
                j--;
            }
            job[j+1]=key;
        }
    }
}