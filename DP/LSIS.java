//Longest Sum Increasing Subsequence
//prerequisite - Longest Increasing Subsequence
import java.util.*;

class Testclass {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        int[] check = new int[n];

        //Each cell in check[] corresponds to the max SUM of subsequences that end with that number
        check[0] = arr[0];
        int overallMax = arr[0]; // Stores the max sum till that array element

        for (int i = 1; i < n; i++) {
            int max = arr[i],temp=0;
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){  //if current number is greater than jth element of array
                    temp = Math.max(temp,check[j]);
                }
            }
            max+=temp;
            check[i] = max;
            overallMax = Math.max(overallMax,check[i]);
        }
        System.out.println(overallMax);
    }
}
