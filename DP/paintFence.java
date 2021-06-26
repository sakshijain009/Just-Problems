//Paint Fence Problem (LEETCODE)
// n =  number of fences
// k = number of colors
// Number of ways fences can be colored such that not more than 2 consecutive fence have the same color
// HERE: Constraint --- n>=2
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();

        long same = k; //Number of ways last two were same
        long notsame = k * (k-1); //Number of ways last two were not same
        long total = same + notsame; //Total ways for that ith fence

        for (int i = 3; i <= n; i++) {
            same = notsame;
            notsame = total * (long)(k-1);
            total = same + notsame;
        }

        System.out.println(total);
    }
}
