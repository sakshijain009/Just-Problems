//Given any binary number give the number of steps to reduce it to 1
//If it is odd add 1, if even divide by 2
//For input: 101110    Output: 8
//For input: 1    Output: 0
//For input: 11    Output: 3
//For input: 1010    Output: 6

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        
        long count = 0;
        char carry = '0';
        for (int i = st.length()-1; i >= 0 ; i--) {
            if (st.charAt(i)=='0' && carry=='0') count++;
            else if (st.charAt(i)=='0' && carry=='1') {
                count+=2;
            }else if(st.charAt(i)=='1' && carry=='0'){
                if(i!=0){
                    carry='1';
                    count+=2;
                }
            }else{
                count+=1;
            }
        }
        System.out.println(count);
    }
}

