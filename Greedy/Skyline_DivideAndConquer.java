import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class skyLineMerge {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int[][] x1=new int[n][3];

        for (int i = 0; i < n; i++) {
            x1[i][0]=s.nextInt(); //left
            x1[i][1]=s.nextInt(); //right
            x1[i][2]=s.nextInt(); //height
        }

        List<int[]> sky=SkylineSort(x1,0,n-1);

        printSky(sky);
    }

    public static List<int[]> SkylineSort(int[][] arr,int low,int high){
        if(low == high){
            List<int[]> sky=new ArrayList<>();
            sky.add(0, new int[]{arr[low][0], arr[low][2]});
            sky.add(1, new int[]{arr[low][1],0});
            return sky;
        }
        if(high<low) {
            List<int[]> sky=new ArrayList<>();
            return sky;
        }
        int mid=low + (high-low)/2;

        List<int[]> sky1,sky2,res = new ArrayList<>();
        sky1 = SkylineSort(arr,low,mid);
        sky2 = SkylineSort(arr,mid+1,high);
        res = mergeSky(sky1,sky2);

        return res;
    }


    public static List<int[]> mergeSky(List<int[]> sky1,List<int[]> sky2){
        int h1=0,h2=0;
        int i=0,j=0,k=0;
        List<int[]> res =  new ArrayList<>();
        while (i<sky1.size() && j<sky2.size()){
            if (sky1.get(i)[0]<sky2.get(j)[0]){
                h1=sky1.get(i)[1];
                res.add(k++,new int[]{sky1.get(i)[0],Math.max(h2,sky1.get(i)[1])});
                ++i;
            }else if(sky1.get(i)[0]==sky2.get(j)[0]){
                h1=sky1.get(i)[1];
                h2=sky2.get(j)[1];
                res.add(k++,new int[]{sky1.get(i)[0],Math.max(h2,h1)});
                ++i;
                ++j;
            }else{
                h2=sky2.get(j)[1];
                res.add(k++,new int[]{sky2.get(j)[0],Math.max(h1,sky2.get(j)[1])});
                ++j;
            }
        }
        while (i<sky1.size()){
            res.add(k++,new int[]{sky1.get(i)[0],sky1.get(i)[1]});
            ++i;
        }
        while (j<sky2.size()){
            res.add(k++,new int[]{sky2.get(j)[0],sky2.get(j)[1]});
            ++j;
        }

        List<int[]> ans =  new ArrayList<>();
        for( i=0;i<res.size();) {
            int top=res.get(i)[1];
            ans.add(new int[] {res.get(i)[0],res.get(i)[1]});
            while(i<res.size() && top==res.get(i)[1] ) {
                i++;
            }
        }

        return ans;
    }


    public static void printSky(List<int[]> sky){
        int i=0;
        while (i<sky.size()){
            System.out.println("( "+sky.get(i)[0]+", "+sky.get(i)[1]+" )");
            i++;
        }
    }
}