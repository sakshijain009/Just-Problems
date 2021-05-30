//Calculating power using recursion

class Power {
    public static void main(String args[] ){
        System.out.println(power(2,5));
        System.out.println(powerLogarithmic(2,5));
    }

    //Power in linear time
    public static int power(int x, int n){
        if (n==0) return 1;
        return x * power(x,n-1);
    }
    
    //Power in logarithmic time
    public static int powerLogarithmic(int x, int n){
        if (n==0) return 1;
        int x1 = powerLogarithmic(x,n/2);
        int x2 = x1*x1;
        if (n%2==1) x2 = x2*x;
        return x2;
    }
}
