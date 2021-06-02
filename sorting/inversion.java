import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Inversion {
    public static void main(String[] args)  throws FileNotFoundException {
        int fileNumber = 0;
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\filed").listFiles();
        for (File file : files){
            double start, end;
            fileNumber++;
            int ans;
            ArrayList<Integer> array = new ArrayList<Integer>(2);

            System.out.println("\n\n______FILE NUMBER " + fileNumber + " UNDER PROCESS________");
            addData(array, file);
            System.out.println("Size of n is: "+ array.size());

            //AVERAGE CASE
            System.out.println("Average Case:--->");
            start = System.nanoTime();
            ans = findInversionCount(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe inversion count of array is " + ans);

            Collections.sort(array);

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            ans = findInversionCount(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe inversion count of array is " + ans);

            Collections.reverse(array);

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            ans = findInversionCount(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe inversion count of array is " + ans);
        }
    }
    public static int findInversionCount(ArrayList<Integer> arr)
    {
        int inversionCount = 0;
        for (int i = 0; i < arr.size() - 1; i++)
        {
            for (int j = i + 1; j < arr.size(); j++)
            {
                if (arr.get(i) > arr.get(j)) {
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }
    public static void addData(ArrayList<Integer> arr, File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            arr.add(n);
        }
    }
}
