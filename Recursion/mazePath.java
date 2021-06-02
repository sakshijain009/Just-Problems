//GET MAZE PATHS
import java.util.*;

class Maze {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();
        ArrayList<String> ans  = findMatrixPath(0,0,m,n);
        System.out.println(ans);
    }

    public static ArrayList<String> findMatrixPath(int x,int y,int m,int n){
        if (x==m-1 && y==n-1){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }

        ArrayList<String> paths = new ArrayList<>();
        if (x < m-1){
            ArrayList<String> ver = findMatrixPath(x+1,y,m,n);
            for (String path:ver){
                paths.add('v'+path);
            }
        }
        if (y < n-1){
            ArrayList<String> hor = findMatrixPath(x,y+1,m,n);
            for (String path:hor){
                paths.add('h'+path);
            }
        }

        return paths;
    }
}
