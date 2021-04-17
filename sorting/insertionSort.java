import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class insertionSort {
    public static void main(String[] args) throws FileNotFoundException {
        int fileNumber = 0;
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\files").listFiles();
        for (File file : files){
            double start, end;
            fileNumber++;
            ArrayList<Integer> array = new ArrayList<Integer>(2);

            System.out.println("\n\n________FILE NUMBER " + fileNumber + " UNDER PROCESS__________");
            addData(array, file);
            System.out.println("Size of n is: "+ array.size());

            //AVERAGE CASE
            System.out.println("Average Case:--->");
            start = System.nanoTime();
            InsertionSort(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            InsertionSort(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            InsertionSortR(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");
        }
    }

    public static void addData(ArrayList<Integer> arr, File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            arr.add(n);
        }
    }

    public static void InsertionSort(ArrayList<Integer> arr){
        int key;
        for (int i = 0; i < arr.size(); i++) {
            key=arr.get(i);
            int j=i-1;
            while (j>=0 && arr.get(j)>key){
                arr.set(j+1,arr.get(j));
                j--;
            }
            arr.set(j+1,key);
        }
    }

    public static void InsertionSortR(ArrayList<Integer> arr){
        int key;
        for (int i = 0; i < arr.size(); i++) {
            key=arr.get(i);
            int j=i-1;
            while (j>=0 && arr.get(j)<key){
                arr.set(j+1,arr.get(j));
                j--;
            }
            arr.set(j+1,key);
        }
    }
}