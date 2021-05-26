import java.util.ArrayDeque;
import java.util.Queue;

class QueueS {
    public static class queueToStackAdapter{
        Queue<Integer> mainq;
        Queue<Integer> helperq;

        public queueToStackAdapter(){
            mainq = new ArrayDeque<>();
            helperq = new ArrayDeque<>();
        }

        int size(){
            return mainq.size();
        }

        void push(int val){
            while (mainq.size()>0){
                helperq.add(mainq.remove());
            }
            mainq.add(val);
            while (helperq.size()>0){
                mainq.add(helperq.remove());
            }
        }

        int pop(){
            if (size() == 0){
                System.out.println("Stack Underflow");
                return -1;
            }else {
                return mainq.remove();
            }
        }

        int top(){
            if (size() == 0){
                System.out.println("Stack Underflow");
                return -1;
            }else {
                return mainq.peek();
            }
        }

    }
}


