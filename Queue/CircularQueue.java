class CirQueue {

    public static class queue{
        int[] data;
        int front;
        int rear;
        int size;

        public queue(int cap) {
            data = new int[cap];
            front= -1;
            rear = -1;
            size = 0;
        }

        int size() {
            return size;
        }

        void display() {
            if (isEmpty()){
                System.out.println("\nQueue Underflow");
                return;
            }
            System.out.print("\nQUEUE ELEMENTS: ");
            int i = front;
            while (i!=rear){
                System.out.print(data[i] + " | ");
                i = (i+1)%data.length;
            }
            System.out.print(data[rear]);
        }

        void enqueue(int val) {
            if (isFull()){
                System.out.println("\nQueue Overflow");
            }else if (isEmpty()){
                front++;
                rear++;
                size++;
                data[front]=val;
            }else {
                rear = (rear+1)%data.length;
                data[rear]=val;
                size++;
            }
        }

        int dequeue() {
            if (isEmpty()){
                System.out.println("\nQueue Underflow");
                return -1;
            }else if(front == rear){
                int ans = data[front];
                front = -1;
                rear = -1;
                size--;
                return ans;
            }else {
                int ans = data[front];
                front++;
                size--;
                return ans;
            }
        }

        int peek() {
            if (isEmpty()){
                System.out.println("\nQueue Underflow");
                return -1;
            }else {
                return data[front];
            }
        }

        boolean isEmpty(){
            if (front==-1 && rear==-1){
                return true;
            }else{
                return false;
            }
        }

        boolean isFull(){
            if ((rear+1)%data.length == front){
                return true;
            }else {
                return false;
            }

        }
    }
}


