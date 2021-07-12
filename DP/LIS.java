//Longest Increasing Subsequence
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

        //Each cell in check[] corresponds to the number of subsequences that end with that number
        check[0] = 1;
        int overallMax = 1; // Stores the max length subsequence till that array element

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){  //if current number is greater than jth element of array
                    max = Math.max(max,check[j]);
                }
            }
            check[i] = max + 1; //if ith element is smaller than all prev ele then 1 is stored (as max was 0 initially) else some other number is stored
            overallMax = Math.max(overallMax,check[i]);
        }
        System.out.println(overallMax);
    }
}
