import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class InversionM {
    public static void main(String[] args) throws FileNotFoundException {
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
            ans=mergesort(array,0,array.size()-1);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe Inversion count of array is " + ans);

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            ans=mergesort(array,0,array.size()-1);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe Inversion count of array is " + ans);

            Collections.reverse(array);

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            ans=mergesort(array,0,array.size()-1);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
            System.out.println("\tThe Inversion count of array is " + ans);

        }
    }
    public static void addData(ArrayList<Integer> arr, File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            arr.add(n);
        }
    }

    public static int mergesort(ArrayList<Integer> arr, int low, int high)
    {
        // Base case
        if (high == low) {
            return 0;
        }
        // finding midpoint
        int mid = (low + ((high - low) >> 1));
        int inversionCount = 0;
        inversionCount += mergesort(arr, low, mid); //left half
        inversionCount += mergesort(arr, mid + 1, high); //right half
        inversionCount += merge(arr, low, mid, high); // merge the two half runs

        return inversionCount;
    }
    public static int merge(ArrayList<Integer> arr, int low, int mid, int high)
    {
        int k = low, i = low, j = mid + 1;
        int[] aux = new int[arr.size()];
        int inversionCount = 0;

        while (i <= mid && j <= high)
        {
            if (arr.get(i) <= arr.get(j)) {
                aux[k++] = arr.get(i++);
            }
            else {
                aux[k++] = arr.get(j++);
                inversionCount += (mid - i + 1);
            }
        }

        while (i <= mid) {
            aux[k++] = arr.get(i++);
        }
        while (j <= high) {
            aux[k++] = arr.get(j++);
        }

        for (i = low; i <= high; i++) {
            arr.set(i,aux[i]);
        }
        return inversionCount;
    }
}