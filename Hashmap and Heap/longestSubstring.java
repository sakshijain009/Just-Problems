//Longest substring without repeating characters
//EG: vdvf : 3
//    abcacba : 3
//    bbbbb: 1
import java.util.*;

class Testclass {
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        int gmax = 0,max=0;
        HashMap<Character,Integer> hp = new HashMap<>();

        for(int i=0;i<s.length();i++){
            if(!hp.containsKey(s.charAt(i))){
                hp.put(s.charAt(i),i);
                max++;
            }else{
                int temp = hp.get(s.charAt(i));
                hp = new HashMap<>();
                max = i-temp;
                int j=0;
                while(j<max){
                    hp.put(s.charAt(i-j),i-j);
                    j++;
                }
            }

            gmax = Math.max(gmax,max);
        }

        System.out.println(gmax);
    }
}
