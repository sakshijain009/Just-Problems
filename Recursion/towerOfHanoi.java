//TOWER OF HANOI
import java.util.*;

class Power {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int d1 = s.nextInt();
        int d2 = s.nextInt();
        int d3 = s.nextInt();
        System.out.println("Tower of hanoi :");
        towerHanoi(n,d1,d2,d3);
    }

    public static void towerHanoi(int n,int d1,int d2,int d3){
        if (n==0) return;
        towerHanoi(n-1,d1,d3,d2);
        System.out.println(n + " ["+d1+" -> "+d2+"]");
        towerHanoi(n-1,d3,d2,d1);
    }

}
