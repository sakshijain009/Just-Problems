import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class SelectionSort {
    public static void main(String[] args) throws FileNotFoundException {
        int fileNumber = 0;
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\files").listFiles();
        for (File file : files){
            double start, end;
            fileNumber++;
            ArrayList<Integer> array = new ArrayList<Integer>(2);
            System.out.println("\n\n_______________FILE NUMBER " + fileNumber + " UNDER PROCESS_________________");

            addData(array, file);
            System.out.println("Size of n is: "+ array.size());

            //AVERAGE CASE
            System.out.println("Average Case:--->");
            start = System.nanoTime();
            selectionSort(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            selectionSort(array);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            selectionSortDesc(array);
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

    public static void selectionSort(ArrayList<Integer> arr){
        int min,temp;
        for (int i = 0; i < arr.size()-1; i++) {
            min=i;
            for (int j = i+1; j < arr.size(); j++) {
                min=arr.get(j)<arr.get(min)?j:min;
            }
            if (min!=i){
                temp=arr.get(min);
                arr.set(min,arr.get(i));
                arr.set(i,temp);
            }
        }
    }

    public static void selectionSortDesc(ArrayList<Integer> arr){
        int max,temp;
        for (int i = 0; i < arr.size()-1; i++) {
            max=i;
            for (int j = i+1; j < arr.size(); j++) {
                max=arr.get(j)>arr.get(max)?j:max;
            }
            if (max!=i){
                temp=arr.get(max);
                arr.set(max,arr.get(i));
                arr.set(i,temp);
            }
        }
    }
}