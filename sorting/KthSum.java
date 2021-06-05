import java.util.*;

class KSum {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n  = s.nextInt();
        long k = s.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextLong();
        }
        Arrays.sort(arr);

        long low = 2*arr[0];
        long high = 2*arr[n-1];
        while (low<high){
            long mid = (low+high)>>1;
            if(check(mid,arr,k)) low = mid+1;
            else high=mid;
        }
        System.out.println(low);
    }

    public static boolean check(long x,long[] arr, long k){
        long pairs = 0;
        int p = 0;
        for (int i = arr.length - 1; i >= 0 && (p<=i); i--)
        {
            while ((p < arr.length) && (arr[i] + arr[p]<= x)){
                pairs += (i-p+1);
                p++;
            }

        }
        return (pairs < k);
    }

}
