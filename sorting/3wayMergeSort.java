import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class MergeSortThree {
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
        if(ub-lb<1){
            return;
        }
            int mida =lb + ((ub - lb) / 3);;
            int midb= lb + 2 * ((ub - lb) / 3) + 1;;
            mergeSort(arr,lb,mida);
            mergeSort(arr,mida+1,midb);
            mergeSort(arr,midb+1,ub);
            merge(arr,lb,mida,midb,ub);
    }

    public static void mergeSortR(ArrayList<Integer> arr,int lb,int ub){
        if(ub-lb<1){
            return;
        }
        int mida =lb + ((ub - lb) / 3);;
        int midb= lb + 2 * ((ub - lb) / 3) + 1;;
        mergeSortR(arr,lb,mida);
        mergeSortR(arr,mida+1,midb);
        mergeSortR(arr,midb+1,ub);
        mergeR(arr,lb,mida,midb,ub);
    }

    public static void merge(ArrayList<Integer> arr,int lb,int mida,int midb,int ub){
        int i=lb;
        int j=mida+1;
        int k=midb+1;
        int l=lb;
        int[] b=new int[arr.size()];
        while (i<=mida && j<=midb && k<=ub){
            if (arr.get(i)<=arr.get(j) && arr.get(i)<=arr.get(k)){
                b[l++]=arr.get(i++);
            }else if(arr.get(j)<=arr.get(i) && arr.get(j)<=arr.get(k)){
                b[l++]=arr.get(j++);
            }else {
                b[l++]=arr.get(k++);
            }
        }
        //***********************************************
        if (i>mida){
            while (j<=midb && k<=ub){
                if (arr.get(j)<=arr.get(k)){
                    b[l++]=arr.get(j++);
                }else{
                    b[l++]=arr.get(k++);
                }
            }
        }else if(j>midb){
            while (i<=mida && k<=ub){
                if (arr.get(i)<=arr.get(k)){
                    b[l++]=arr.get(i++);
                }else{
                    b[l++]=arr.get(k++);
                }
            }
        }else{
            while (i<=mida && j<=midb){
                if (arr.get(i)<=arr.get(j)){
                    b[l++]=arr.get(i++);
                }else{
                    b[l++]=arr.get(j++);
                }
            }
        }
        //***************************************

        while (k<=ub) b[l++]=arr.get(k++);
        while (j<=midb) b[l++]=arr.get(j++);
        while (i<=mida) b[l++]=arr.get(i++);
        //****************************************
        for (k = lb; k <= ub; k++) {
            arr.set(k,b[k]);
        }
    }

    public static void mergeR(ArrayList<Integer> arr,int lb,int mida,int midb,int ub){
        int i=lb;
        int j=mida+1;
        int k=midb+1;
        int l=lb;
        int[] b=new int[arr.size()];
        while (i<=mida && j<=midb && k<=ub){
            if (arr.get(i)>=arr.get(j) && arr.get(i)>=arr.get(k)){
                b[l++]=arr.get(i++);
            }else if(arr.get(j)>=arr.get(i) && arr.get(j)>=arr.get(k)){
                b[l++]=arr.get(j++);
            }else {
                b[l++]=arr.get(k++);
            }
        }
        //***********************************************
        if (i>mida){
            while (j<=midb && k<=ub){
                if (arr.get(j)>=arr.get(k)){
                    b[l++]=arr.get(j++);
                }else{
                    b[l++]=arr.get(k++);
                }
            }
        }else if(j>midb){
            while (i<=mida && k<=ub){
                if (arr.get(i)>=arr.get(k)){
                    b[l++]=arr.get(i++);
                }else{
                    b[l++]=arr.get(k++);
                }
            }
        }else{
            while (i<=mida && j<=midb){
                if (arr.get(i)>=arr.get(j)){
                    b[l++]=arr.get(i++);
                }else{
                    b[l++]=arr.get(j++);
                }
            }
        }
        //***************************************

        while (k<=ub) b[l++]=arr.get(k++);
        while (j<=midb) b[l++]=arr.get(j++);
        while (i<=mida) b[l++]=arr.get(i++);
        //****************************************
        for (k = lb; k <= ub; k++) {
            arr.set(k,b[k]);
        }
    }

}