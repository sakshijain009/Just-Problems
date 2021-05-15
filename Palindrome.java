import java.util.Scanner;

class palindrome{
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        char[] arr=longestPalSub(str.toCharArray(),0,str.length()-1);
        System.out.println("==========RECURSION=============");
        System.out.print("The Longest Palindromic Subsequence using recursion is: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"");
        }


        System.out.println("\n\n=========DYNAMMIC PROGRAMMING============");
        LLength(str,str.length());
        System.out.println("\n");
        LLengthBU(str,str.length());
        System.out.println("\n");

        System.out.println("=========NAIVE ITERATIVE============");
        System.out.println("The Longest Palindromic Subsequence using Iterative is: "+naive_iterative(str));
    }

    //Naive Recursive Solution=============================================
    public static char[] longestPalSub(char[] c,int l,int h){
        if (l==h) {
            return new char[]{c[l]};
        }

        if (l + 1 == h && c[h] == c[l]) {
            return new char[]{c[l],c[l]};
        }

        if (c[h] == c[l]) {
            return addds(longestPalSub(c,l+1,h-1),c[l]);
        }

        return max(longestPalSub(c,l,h-1),longestPalSub(c,l+1,h));
    }

    public static char[] max(char[] a,char[] b) {
        if (a.length>b.length) return a;
        return b;
    }
    public static char[] addds(char c[],char ch){
        char[] arr = new char[c.length+2];
        arr[0]=ch;
        arr[c.length+1]=ch;
        for (int i = 0; i < c.length; i++) {
            arr[1+i]=c[i];
        }
        return arr;
    }

  //USING DP Top Down============================================

    public static void LLength(String X, int n)
    {
        int[][] T = new int[n][n];
        for (int i = 0; i < n; i++) T[i][i]=1;
        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n-cl+1; i++) {
                int j= i+cl-1;
                boolean b = X.charAt(i) == X.charAt(j);
                if (b && cl == 2)
                    T[i][j] = 2;
                else if (b)
                    T[i][j] = T[i+1][j-1] + 2;
                else
                    T[i][j] = max(T[i][j-1], T[i+1][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(T[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("Longest Palindromic Subsequence Using Top Down: ");
        System.out.print(longestPalindromeSub(X,0,X.length()-1,T));

    }

    public static String longestPalindromeSub(String X,int n1,int n2,int[][] T)
    {

        if (n1==n2) return X.substring(n1,n1+1);
        if (X.charAt(n1) == X.charAt(n2)){
            return X.charAt(n1)+longestPalindromeSub(X,n1+1,n2-1,T)+X.charAt(n1);
        }
        if (T[n1 + 1][n2] > T[n1][n2 - 1]) {
            return longestPalindromeSub(X,n1+1, n2, T);
        }

        return longestPalindromeSub(X,n1, n2-1, T);
    }

    private static int max(int i, int i1) {
        return i>i1?i:i1;
    }

    //USING DP Bottom up============================================

    public static void LLengthBU(String X, int n)
    {
        int[][] T = new int[n][n];
        for (int i = 0; i < n; i++) T[i][i]=1;
        for (int i = n-2; i >=0; i--) {
            for (int cl = 2; cl < n-i+1; cl++) {
                int j=i+cl-1;
                boolean b = X.charAt(i) == X.charAt(j);
                if (b && cl == 2)
                    T[i][j] = 2;
                else if (b)
                    T[i][j] = T[i+1][j-1] + 2;
                else
                    T[i][j] = max(T[i][j-1], T[i+1][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(T[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("Longest Palindromic Subsequence Using Bottom Up: ");
        System.out.print(longestPalindromeSub(X,0,X.length()-1,T));

    }
    // Naive Iterative Solution=============================================
    static boolean check_palindrome(String s) {
        boolean flag=true;
        int n=s.length();
        for(int i=0;i<(n/2);i++) {
            if(s.charAt(i)!=s.charAt(n-i-1)) {
                flag=false;
                break;
            }
        }

        return flag;
    }
    static String naive_iterative(String s) {
        String ans="";
        int n=s.length();
        long limit=1<<n ;
        for(int i=0;i<limit;i++) {
            String temp="";
            for(int bit=n-1;bit>=0;bit--) {
                if((i & (1<<bit)) >0) {
                    temp+=s.substring(bit,bit+1);
                }
            }
            if(check_palindrome(temp)) {
                if(temp.length()>ans.length()) {
                    ans=temp;
                }
            }
        }
        return ans;
    }


}
