import java.io.IOException;
import java.util.*;

class Stack {
    public static void main(String[] args) throws IOException{
        System.out.println("Stack Operations");
        stack st = new stack(6);
        st.push(5);
        st.push(7);
        st.display();
        System.out.println("top = " + st.top());
        st.push(8);
        st.push(9);
        st.push(1);
        st.push(4);
        st.display();
        st.push(3);
        st.pop();
        st.display();
    }

    public static class stack{
        int[] data;
        int top;

        public stack(int capacity){
            data = new int[capacity];
            top = -1;
        }

        int size(){
            return top+1;
        }

        void display(){
            if (top == -1){
                System.out.println("Stack Underflow");
            }else{
                System.out.print("Stack elements (bottom to top) : ");
                for (int i = 0; i <=top ; i++) {
                    System.out.print(data[i]+ " ");
                }
                System.out.println();
            }
        }

        void push(int val){
            if (top==data.length -1){
                System.out.println("Stack Overflow");
            }else{
                top++;
                data[top]=val;
            }
        }

        int pop(){
            if (top == -1){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top--];
            }
        }

        int top(){
            if (top == -1){
                System.out.println("Stack Underflow");
                return -1;
            }else{
                return data[top];
            }
        }
    }
}
