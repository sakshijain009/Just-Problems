//PASCALS TRIANGLE
//          1
//        1   1
//     1    2    1
//  1    3     3    1
// And so on.....
//According to the rows given, we have to generate that many rows of pascal triangle
import java.io.IOException;
import java.util.*;

class Pascal
{
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int numRows = s.nextInt();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>(); //Generates new row

        for(int i=0;i<numRows;i++){
            temp.add(0,1);
            for(int j=1;j<temp.size()-1;j++){
                temp.set(j,temp.get(j)+temp.get(j+1));
            }
            ans.add(new ArrayList<>(temp)); //New row added to the main arraylist
        }

        System.out.println(ans);
    }
}
