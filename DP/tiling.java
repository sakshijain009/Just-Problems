//Tiling with Dominoes
// n =  length, where area becomes: 2xn
// Number of ways the area of 2xn can be tiled with 2x1 tiles
// n >= 2
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] count = new int[n+1];

        count[2] = 2;
        count[1] = 1;

        for (int i = 3; i <= n; i++) {
            count[i] = count[i-1] + count[i-2];
        }

        System.out.println(count[n]);
    }
}
