import java.util.*;

class Problem {
    public static void main(String args[] ) throws Exception {
        int[][] arr = new int[2][3];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        printWave(arr);
    }
    public static void printWave(int[][] arr){
        int m = arr.length;
        int n = arr[0].length;
        int flag=0,k=0;
        while (k<n){
            if (flag==0){
                for (int j = 0; j < m; j++,flag++) System.out.print(arr[j][k] + " ");
            }else{
                for (int j = m-1; j >= 0; j--,flag--) System.out.print(arr[j][k] + " ");
            }
            k++;
        }
    }

}



