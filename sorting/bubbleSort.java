import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class BubbleSort {
    public static void main(String[] args) throws FileNotFoundException {
        int fileNumber = 0;
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\filed").listFiles();
        for (File file : files) {
            double start, end;
            fileNumber++;
            ArrayList<Integer> array = new ArrayList<Integer>(2);

            System.out.println("\n\n_______________FILE NUMBER " + fileNumber + " UNDER PROCESS_________________");
            addData(array, file);

            //AVERAGE CASE
            System.out.println("Average Case:--->");
            start = System.nanoTime();
            BubbleSort(array, 0);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            BubbleSort(array, 0);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            BubbleSort(array, 1);
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

    public static void BubbleSort(ArrayList<Integer> arr, int ch) {
        int temp;
        if (ch == 0) {
            for (int i = 0; i < arr.size() - 1; i++) {

                for (int j = 0; j < arr.size() - 1 - i; j++) {
                    if (arr.get(j) > arr.get(j + 1)) {
                        temp = arr.get(j);
                        arr.set(j, arr.get(j + 1));
                        arr.set(j + 1, temp);
                    }
                }

            }
        } else {
            for (int i = arr.size() - 1; i > 0; i--) {

                for (int j = arr.size() - 1; j > arr.size() - i - 1; j--) {
                    if (arr.get(j - 1) < arr.get(j)) {
                        temp = arr.get(j);
                        arr.set(j, arr.get(j - 1));
                        arr.set(j - 1, temp);
                    }
                }
            }
        }
    }
}