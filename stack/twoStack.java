import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.*;

class Stacks {
    public static class TwoStack {
        int[] data;
        int top1;
        int top2;

        public TwoStack(int cap) {
            data = new int[cap];
            int top1 = -1;
            int top2 = data.length;
        }

        int size1() {
            return top1+1;
        }

        int size2() {
            return data.length - top2;
        }

        void push1(int val) {
            if(top1+1==top2){
                System.out.println("Stack Overflow");
            }else{
                top1++;
                data[top1]=val;
            }
        }

        void push2(int val) {
            if(top1+1==top2){
                System.out.println("Stack Overflow");
            }else{
                top2--;
                data[top2]=val;
            }
        }

        int pop1() {
            if (size1()==0){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top1--];
            }
        }

        int pop2() {
            if (size2()==0){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top2++];
            }
        }

        int top1() {
            if (size1()==0){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top1];
            }
        }

        int top2() {
            if (size2()==0){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top2];
            }
        }
    }
}



