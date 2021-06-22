//COUNT BINARY STRINGS OF LENGTH N
//CONDITION: NO TWO 0's SHOULD BE TOGETHER, BUT 1's CAN BE
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] zero = new int[n+1]; //counts the strings ending with 0
        int[] ones = new int[n+1];  //counts the strings ending with 1
        //Both array follow the condition
        
        ones[1] = 1;
        zero[1] = 1;

        for (int i = 2; i < n+1; i++) {
            ones[i] = zero[i-1] + ones[i-1];
            zero[i] = ones[i-1];
        }
        System.out.println(zero[n]+ones[n]);
    }
}
