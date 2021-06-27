//Median value of a priority queue
import java.util.*;

class Testclass {
    public static class medianPriorityQueue{
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public medianPriorityQueue(){
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val){
            if(right.size()>0 && val>right.peek()){
                right.add(val);
            }else {
                left.add(val);
            }

            if (right.size()-left.size() == 2){
                left.add(right.remove());
            }else if (left.size() - right.size() == 2){
                right.add(left.remove());
            }
        }

        public int remove(){
            if (this.size()==0){
                System.out.println("Underflow");
                return -1;
            }
            if (left.size() >= right.size()) return left.remove();
            else return right.remove();
        }

        public int peek(){
            if (this.size()==0){
                System.out.println("Underflow");
                return -1;
            }
            if (left.size() >= right.size()) return left.peek();
            else return right.peek();
        }

        public int size(){
            return left.size() + right.size();
        }
    }
}
