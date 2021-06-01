//ALL SUBSEQUENCE OF A STRING
import java.util.*;

class Subseq {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        String st = s.next();
        ArrayList<String> ans = getSubsequence(st);
        System.out.println(ans);
        ArrayList<String> iter = naive_iterative(st);
        System.out.println(iter);
    }

    public static ArrayList<String> getSubsequence(String str){
        if (str.length()==0){
            ArrayList<String> baseCase = new ArrayList<>();
            baseCase.add("");
            return  baseCase;
        }
        char c = str.charAt(0);
        String rest = str.substring(1);
        ArrayList<String> res = getSubsequence(rest);

        ArrayList<String> nres = new ArrayList<>();
        for (String x:res){
            nres.add(x);
            nres.add(c+x);
        }
        return nres;
    }

     public static ArrayList<String> naive_iterative(String s) {
         ArrayList<String> ans = new ArrayList<>();
        int n=s.length();
        long limit=1<<n ;
        for(int i=0;i<limit;i++) {
            String temp = "";
            for(int bit=0;bit<=n-1;bit++) {
                if((i & (1<<bit)) >0) {
                    temp+=s.substring(bit,bit+1);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

}
