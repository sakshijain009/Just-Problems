import java.util.Scanner;

class cutRod {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] cost = new int[n+1];
        cost[0]=0;
        for (int i = 1; i < n+1; i++) {
            cost[i] = s.nextInt();
        }
        int[] out= new int[n+1];
        for (int i = 0; i < n+1; i++) {
            out[i]=Integer.MIN_VALUE;
        }
        int ans = cutRod(cost,n,out);
        System.out.println(ans);
    }

    public static int cutRod(int[] cost,int n,int[] out){
        if (n==0) return 0;
        if (out[n]>0) return out[n];
        int q = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--) {
                q=Math.max(cost[n-i]+cutRod(cost,i,out),q);
        }
        out[n]=q;
        return q;
    }

}
