//Highest frequency of character
import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (hm.containsKey(str.charAt(i))){
                int freq = hm.get(str.charAt(i));
                hm.put(str.charAt(i),freq+1);
            }else{
                hm.put(str.charAt(i),1);
            }
        }

        int max = Integer.MIN_VALUE;
        char ans = '0';
        for (Character ch: hm.keySet()) {
            if (hm.get(ch)>max){
                max = hm.get(ch);
                ans = ch;
            }
        }

        System.out.println("Max freq: "+max+" of "+ans);

    }
}
