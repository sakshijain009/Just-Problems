import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class MergeSort {
    public static void main(String[] args) throws FileNotFoundException{
        int fileNumber = 0;
        File[] files = new File("C:\\Users\\Sakshi Jain\\Desktop\\filing").listFiles();
        for (File file : files){
            double start, end;
            fileNumber++;
            ArrayList<Integer> array = new ArrayList<Integer>(2);

            System.out.println("\n\n______FILE NUMBER " + fileNumber + " UNDER PROCESS________");
            addData(array, file);
            System.out.println("Size of n is: "+ array.size());

            //AVERAGE CASE
            System.out.println("Average Case:--->");
            start = System.nanoTime();
            mergeSort(array, 0,array.size()-1);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //BEST CASE
            System.out.println("Best Case:--->");
            start = System.nanoTime();
            mergeSort(array, 0,array.size()-1);
            end = System.nanoTime();
            System.out.println("\tStart time: " + start + " nanoseconds");
            System.out.println("\tEnd time: " + start + " nanoseconds");
            System.out.println("\tDifference: " + (end - start) + " nanoseconds");

            //WORST CASE
            System.out.println("Worst Case:--->");
            start = System.nanoTime();
            mergeSortR(array, 0,array.size()-1);
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
    public static void mergeSort(ArrayList<Integer> arr,int lb,int ub){
        if(lb<ub){
            int mid =(lb+ub)/2;
            mergeSort(arr,lb,mid);
            mergeSort(arr,mid+1,ub);
            merge(arr,lb,mid,ub);
        }
    }
    public static void merge(ArrayList<Integer> arr,int lb,int mid,int ub){
        int i=lb;
        int j=mid+1;
        int k=lb;
        int[] b=new int[arr.size()];
        while (i<=mid && j<=ub){
            if (arr.get(i)<=arr.get(j)){
                b[k]=arr.get(i);
                i++;
                k++;
            }else {
                b[k]=arr.get(j);
                j++;
                k++;
            }
        }
        if (i>mid){
            while (j<=ub){
                b[k]=arr.get(j);
                j++;
                k++;
            }
        }else {
            while (i<=mid){
                b[k]=arr.get(i);
                i++;
                k++;
            }
        }
        for (k = lb; k <= ub; k++) {
            arr.set(k,b[k]);
        }
    }

    public static void mergeRev(ArrayList<Integer> arr,int lb,int mid,int ub){
        int i=lb;
        int j=mid+1;
        int k=lb;
        int[] b=new int[arr.size()];
        while (i<=mid && j<=ub){
            if (arr.get(i)>=arr.get(j)){
                b[k]=arr.get(i);
                i++;
                k++;
            }else {
                b[k]=arr.get(j);
                j++;
                k++;
            }
        }
        if (i>mid){
            while (j<=ub){
                b[k]=arr.get(j);
                j++;
                k++;
            }
        }else {
            while (i<=mid){
                b[k]=arr.get(i);
                i++;
                k++;
            }
        }
        for (k = lb; k <= ub; k++) {
            arr.set(k,b[k]);
        }
    }

    public static void mergeSortR(ArrayList<Integer> arr,int lb,int ub){
        if(lb<ub){
            int mid =(lb+ub)/2;
            mergeSortR(arr,lb,mid);
            mergeSortR(arr,mid+1,ub);
            mergeRev(arr,lb,mid,ub);
        }
    }
}