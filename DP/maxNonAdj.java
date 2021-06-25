//MAX SUM OF ELEMENTS OF ARRAY WHICH ARE NOT ADJACENT
//Maximum Sum Non Adjacent Elements
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        int absent = 0; //element is not present
        int present = arr[0]; //element is present
        for (int i = 1; i < n; i++) {
            int new_present = absent + arr[i]; //if element is present, prev element should be absent
            int new_absent = Math.max(absent,present); //if absent, take max of prev element's absent and present
            present = new_present;
            absent = new_absent;
        }
        System.out.println(Math.max(present,absent));
    }
}
