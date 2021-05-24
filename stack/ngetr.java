import java.io.IOException;
import java.util.*;

class Stackd {
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        Stack<Integer> ans = new Stack<>();
        Stack<Integer> val = new Stack<>();

        int[] arr = {3,5,1,9,12,3,65,100,2,5};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
        ans.push(-1);

        val.push(arr[arr.length-1]);
        for (int i = arr.length-2; i>=0 ; i--) {
            if (arr[i]<=val.peek()){
                ans.push(val.peek());
                val.push(arr[i]);
            }else {
                while (!val.empty() && val.peek() < arr[i]){
                    val.pop();
                }
                if (val.empty()) ans.push(-1);
                else ans.push(val.peek());
                val.push(arr[i]);
            }
        }
        System.out.print("Output: ");
        while (!ans.empty()){
            System.out.print(ans.pop() + "  ");
        }
    }
}
