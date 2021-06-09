import java.util.*;

class Climb {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Recursion: "+countPaths(n));
        System.out.println("DP: "+CountDP(n,new int[n+1]));
        System.out.println("Tabulation: "+CountDPTab(n));
    }

    // Recursion
    public static int countPaths(int n){
        if (n==0) return 1;
        else if (n<0) return 0;
        return countPaths(n-1)+countPaths(n-2)+countPaths(n-3);
    }

    // DP
    public static int CountDP(int n,int[] store){
        if (n==0) return 1;
        else if (n<0) return 0;

        if (store[n]>0) return store[n];

        int ans1 = CountDP(n-1,store);
        int ans2 = CountDP(n-2,store);
        int ans3 = CountDP(n-3,store);
        store[n] = ans1+ans2+ans3;
        return store[n];
    }

    // Tabulation
    public static int CountDPTab(int n){
        int[] dp = new int[n+1];
        dp[0]=1;
        for (int i = 1; i <= n ; i++) {
            if (i==1){
                dp[i] = dp[i-1];
            }else if(i==2){
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
            }
        }
        return dp[n];
    }
}



