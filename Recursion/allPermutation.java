import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Permutation
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        printPer(str,"");
    }

    public static void printPer(String str,String rem){
        if (str.length() == 0){
            System.out.println(rem);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String lpart = str.substring(0,i);
            String rpart = str.substring(i+1);
            String res = lpart+rpart;
            printPer(res,rem+ch);
        }
    }

}
