import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

class LinearSearch {
    public static void main(String args[]) throws FileNotFoundException {
        int num = 0;
        double b = 0,w=0;
        int[] times = new int[]{150,100,50,25,15,10,10,2,1,1};
        double[] btime=new double[10];
        double[] wtime=new double[10];
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\filed").listFiles();
        for (File file : files) {
            System.out.println("******* File " + (num+1) + " in process *********");
            for (int i = 0; i < times[num]; i++) {
                Vector<Integer> v=new Vector<Integer>();

                int first=getFirst(v,file);
                int last=getLast(v);
                double start = System.nanoTime();
                int res=linearSearch(v,first);
                double end = System.nanoTime();
                btime[num]=(end-start);


                start = System.nanoTime();
                res=linearSearch(v,-1);
                end = System.nanoTime();
                wtime[num]=(end-start);
            }
            for (int i = 0; i < 10; i++) {
                b+=btime[i];
                w+=wtime[i];
            }
            System.out.println("Best Case :-----------------");
            System.out.println("\tTime: "+(b/times[num]) + " nanoseconds");

            System.out.println("\nWorst Case :-----------------");
            System.out.println("\tTime: "+(w/times[num]) + " nanoseconds\n\n");
            num++;
        }
    }

    //----------------------------------------------------------------
    public static int linearSearch(Vector<Integer> vec, int val) throws FileNotFoundException {
        for (int j = 0; j < vec.size(); j++) {
            if (val == vec.elementAt(j)) {
                return j + 1;
            }
        }
        return -1;
    }
    //-------------------------------------------------------------------
    public static int getFirst(Vector<Integer> vec,File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while(scanner.hasNextInt())
        {
            int n=scanner.nextInt();
            vec.add(n);
        }
        return vec.firstElement();
    }
    public static int getLast(Vector<Integer> vec){
        return vec.lastElement();
    }
}