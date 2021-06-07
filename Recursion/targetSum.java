import java.util.*;

class Problem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        int target = s.nextInt();
        printTargetSets(arr,0,"",0,target);

    }

    public static void printTargetSets(int[] arr,int idx, String set,int sos, int target){
        if (idx==arr.length){
            if (sos==target){
                System.out.println(set);
            }
            return;
        }

        printTargetSets(arr,idx+1,set+arr[idx]+" ",sos+arr[idx],target);
        printTargetSets(arr,idx+1,set,sos,target);
    }

}



