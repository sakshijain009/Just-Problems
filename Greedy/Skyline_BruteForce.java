import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class skyLine {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int[][] sky=new int[n][3];
        List<int[]> result= new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sky[i][0]=s.nextInt();
            sky[i][1]=s.nextInt();
            sky[i][2]=s.nextInt();
        }

        result=computeSkyline(sky,n);
        printSky(result);
    }

    public static List<int[]> computeSkyline(int[][] sky,int n){
        List<int[]> result= new ArrayList<>();
        int[][] temp = new int[2][2];
        result.add(0,new int[] {sky[0][0],sky[0][2]});
        result.add(1,new int[] {sky[0][1],0});

        for (int i = 1; i < n; i++) {
            //Adding building
            temp[0][0]=sky[i][0];
            temp[0][1]=sky[i][2];
            temp[1][0]=sky[i][1];
            temp[1][1]=0;
            List<int[]> store= new ArrayList<>();

            int h1=0,h2=0,k=0,j=0,l=0;
            while (j < result.size() && l<2) {
                if (temp[l][0]>result.get(j)[0]){
                    h1=result.get(j)[1];
                    store.add(k++,new int[]{result.get(j)[0],Math.max(h2,h1)});
                    j++;
                }else if(temp[l][0]==result.get(j)[0]){
                    h1=result.get(j)[1];
                    h2=temp[l][1];
                    store.add(k++,new int[]{result.get(j)[0],Math.max(h2,h1)});
                    ++l;
                    ++j;
                }else{
                    h2=temp[l][1];
                    store.add(k++,new int[]{temp[l][0],Math.max(h2,h1)});
                    ++l;
                }
            }
            while (j < result.size()){
                store.add(k++,new int[]{result.get(j)[0],result.get(j)[1]});
                j++;
            }
            while (l < 2){
                store.add(k++,new int[]{temp[l][0],temp[l][1]});
                l++;
            }

            result.clear();
            for (int m = 0; m < store.size(); ) {
                int h=store.get(m)[1];
                result.add(new int[]{store.get(m)[0],store.get(m)[1]});
                while (m<store.size() && h==store.get(m)[1]){
                    m++;
                }
            }
        }
        return result;
    }
    public static void printSky(List<int[]> sky){
        int i=0;
        while (i<sky.size()){
            System.out.println("( "+sky.get(i)[0]+", "+sky.get(i)[1]+" )");
            i++;
        }
    }

}