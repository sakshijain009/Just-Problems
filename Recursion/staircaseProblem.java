/*
STAIRCASE PROBLEM:
You are given a number n representing number of stairs in a staircase
You are standing at the bottom of staircase. You are allowed to climb 1 step, 2 steps or 3 steps in one move.
to get the list of all paths that can be used to climb the staircase up.       
 */

import java.util.*;

class Stair {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ArrayList<String> ans  = findStairs(n);
        System.out.println(ans);
    }

    public static ArrayList<String> findStairs(int n){
        if (n == 0){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }else if(n < 0){
            ArrayList<String> temp = new ArrayList<>();
            return temp;
        }

        ArrayList<String> p1 = findStairs(n-1);
        ArrayList<String> p2 = findStairs(n-2);
        ArrayList<String> p3 = findStairs(n-3);

        ArrayList<String> paths = new ArrayList<>();

        for (String path:p1){
            paths.add(1+path);
        }
        for (String path:p2){
            paths.add(2+path);
        }
        for (String path:p3){
            paths.add(3+path);
        }

        return paths;
    }

}
