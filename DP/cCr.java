class Solution{
    static int nCr(int n, int r)
    {
        // Function to find nCr
        
        // formula : nCr + nCr-1 = n+1Cr
      
        int[][] arr = new int[r+1][n+1];
        
        for(int i=0;i<=r;i++){
            for(int j=0;j<=n;j++){
                if(i==j || i==0) arr[i][j]=1;
            }
        }
        
        for(int i=1;i<=r;i++){
            for(int j=i;j<=n;j++){
               arr[i][j] = arr[i][j-1] + arr[i-1][j-1];
            }
        }
        
        return arr[r][n];
    }
}
