import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

class Crossword{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        char[][] crossword = new char[10][10];
        for (int i = 0; i < 10; i++) {
            String str = s.next();
            crossword[i]=str.toCharArray();
        }
        printC(crossword);

        ArrayList<String> words = new ArrayList<String>();
        words.add("DELHI");
        words.add("ICELAND");
        words.add("ANKARA");
        words.add("LONDON");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }

        System.out.println("\nOUTPUT:\n");
        solveCr(crossword,words,0);
    }

    public static void solveCr(char[][] crossword,ArrayList<String> words,int index){
        /* This prints the different ways crossword can be filled */
        if (index==words.size()){
            printC(crossword);
            return;
        }

        String current = words.get(index);
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword.length; j++) {
                if (crossword[i][j]=='-' || crossword[i][j]==current.charAt(0)){
                    if (canplaceH(crossword,current,i,j)==true){
                        boolean[] wePlaced = placeH(crossword,current,i,j); //Boolean array to use in backtracking
                        solveCr(crossword,words,index+1); //Recursive call
                        unplaceH(crossword,wePlaced,i,j); //Backtracking
                    }
                    if (canplaceV(crossword,current,i,j)==true){
                        boolean[] wePlaced = placeV(crossword,current,i,j); //Boolean array to use in backtracking
                        solveCr(crossword,words,index+1); //Recursive call
                        unplaceV(crossword,wePlaced,i,j); //Backtracking
                    }

                }
            }
        }
    }

    /* This function checks if the word can be placed horizontally from index i,j */
    public static boolean canplaceH(char[][] crossword,String current,int i,int j){
        //First two if conditions make sure the assumed part of puzzle is bounded with + symbol
        if (j-1>=0 && crossword[i][j-1]!='+') return false;
        if (j+current.length()<crossword.length && crossword[i][j+current.length()]!='+') return false;

        for (int x = 0; x < current.length(); x++) {
            if (x+j>=crossword.length) return false;
            if (crossword[i][x+j]=='-' || crossword[i][x+j]==current.charAt(x)){
                continue;
            }else {
                return false;
            }
        }

        return true;
    }

    /* This function checks if the word can be vertically from index i,j */
    public static boolean canplaceV(char[][] crossword,String current,int i,int j){
        //First two if conditions make sure the assumed part of puzzle is bounded with + symbol
        if (i-1>=0 && crossword[i-1][j]!='+') return false;
        if (i+current.length()<crossword.length && crossword[i+current.length()][j]!='+') return false;

        for (int x = 0; x < current.length(); x++) {
            if (x+i>=crossword.length) return false;
            if (crossword[i+x][j]=='-' || crossword[i+x][j]==current.charAt(x)){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    /* This function places the word horizontally from index i,j */
    public static boolean[] placeH(char[][] crossword,String current,int i,int j){
        boolean[] res=new boolean[current.length()];
        for (int k = 0; k < current.length(); k++) {
            if (crossword[i][j+k]=='-'){
                res[k] = true;
                crossword[i][j+k] = current.charAt(k);
            }
            else res[k] = false;
        }
        return res;
    }

    /* This function places the word vertically from index i,j */
    public static boolean[] placeV(char[][] crossword,String current,int i,int j){
        boolean[] res=new boolean[current.length()];
        for (int k = 0; k < current.length(); k++) {
            if (crossword[i+k][j]=='-'){
                res[k] = true;
                crossword[i+k][j] = current.charAt(k);
            }
            else res[k] = false;
        }
        return res;
    }

    /* This function is used to backtrack using the boolean array that was generated horizontally */
    public static void unplaceH(char[][] crossword,boolean[] arr,int i,int j){
        for (int k = 0; k < arr.length; k++) {
            if (arr[k]){  //If boolean value is true then we replace the word with -
                crossword[i][j+k]='-';
            }
        }
    }

    /* This function is used to backtrack using the boolean array that was generated vertically */
    public static void unplaceV(char[][] crossword,boolean[] arr,int i,int j){
        for (int k = 0; k < arr.length; k++) {
            if (arr[k]){  //If boolean value is true then we replace the word with -
                crossword[i+k][j]='-';
            }
        }
    }

    /* Prints the crossword puzzle */
    public static void printC(char[][] crossword){
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword.length; j++) {
                System.out.print(crossword[i][j]+" ");
            }
            System.out.println();
        }
    }
/*
Input:
+-++++++++
+-++++++++
+-++++++++
+-----++++
+-+++-++++
+-+++-++++
+++++-++++
++------++
+++++-++++
+++++-++++
*/

}