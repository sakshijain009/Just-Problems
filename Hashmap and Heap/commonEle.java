//Get Common Element from two arrays
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); //length of first array

        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) { //inputting first array
            int temp = s.nextInt();
            if (hm.containsKey(temp)){
                int freq = hm.get(temp);
                hm.put(temp,freq+1);
            }else{
                hm.put(temp,1);
            }
        }

        int n1 = s.nextInt(); //length of second array
        for (int i = 0; i < n1; i++){ //loop for checking second array elements
            int temp = s.nextInt();
            if (hm.containsKey(temp)){
                System.out.print(temp+" ");
                hm.remove(temp);
            }
        }
    }
}
