import java.util.*;

class Problem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        boolean[][] visited = new boolean[n][m];

        floodfill(arr,0,0,"",visited);
    }

    public static void floodfill(int[][] maze,int row,int col,String psf,boolean[][] visited){
        if (row<0||col<0||row==maze.length||col==maze[0].length||maze[row][col]==1||visited[row][col]==true){
            return;
        }
        if (row==maze.length-1 && col==maze[0].length-1){
            System.out.println(psf);
            return;
        }

        visited[row][col] = true; //Making the visited cells true
        floodfill(maze,row-1,col,psf+"t",visited);
        floodfill(maze,row,col-1,psf+"l",visited);
        floodfill(maze,row+1,col,psf+"d",visited);
        floodfill(maze,row,col+1,psf+"r",visited);
        visited[row][col] = false; //While tracing back making it false to make way for other paths
    }
}



