#include<stdio.h>
#include<stdlib.h>

#define CAPACITY 5
int deque[CAPACITY];
int front=-1,rear=-1;

int isFull();
int isEmpty();
void enqueueFront(int);
void enqueueRear(int);
void peekFront();
void peekRear();
void dequeueFront();
void dequeueRear();

int main(){
	printf("*******DOUBLE ENDED QUEUE OPERATIONS********\n");
	printf("1.Enqueue Front\n2.Dequeue Front\n3.Enqueue Rear\n");
	printf("4.Dequeue Rear\n5.Peek Front\n6.Peek Rear\n7.Quit\n");
	int ch,item;
	while(1){
		printf("\nEnter your choice: ");
		scanf("%d",&ch);
		switch(ch){
			case 1:
				printf("Enter the element to be inserted in the front: ");
				scanf("%d",&item);
				enqueueFront(item);
				break;
			case 2:
				dequeueFront();
				break;
			case 3:
				printf("Enter the element to be inserted in the rear: ");
				scanf("%d",&item);
				enqueueRear(item);
				break;
			case 4:
				dequeueRear();
				break;
			case 5:
				peekFront();
				break;
			case 6:
				peekRear();
				break;
			case 7:
				exit(0);
			default:
				printf("Enter the correct choice!\n");
		}
	}
}
void enqueueFront(int data){
	if(isFull()){
		printf("The queue is full!\n");
		return;
	}else if(isEmpty()){
		front=rear=0;
	}else if(front==0){
		front=CAPACITY-1;
	}else{
		front--;
	}
	deque[front]=data;
	printf("The element has been inserted.\n");
}

void enqueueRear(int data){
	if(isFull()){
		printf("The queue is full!\n");
		return;
	}else if(isEmpty()){
		front=rear=0;
	}else if(rear==CAPACITY-1){
		rear=0;
	}else{
		rear++;
	}
	deque[rear]=data;
	printf("The element has been inserted.\n");
}

void dequeueFront(){
	if(isEmpty()){
		printf("The queue is empty!\n");
		return;
	}else if(front==rear){
		front=rear=-1;
	}else if(front==CAPACITY-1){
		front=0;
	}else{
		front++;
	}
	printf("The element has been deleted from the front.\n");
}

void dequeueRear(){
	if(isEmpty()){
		printf("The queue is empty!\n");
		return;
	}else if(front==rear){
		front=rear=-1;
	}else if(rear==0){
		rear=CAPACITY-1;
	}else{
		rear--;
	}
	printf("The element has been deleted from rear.\n");
}

int isFull(){
	if((front==0&&rear==CAPACITY-1)||(front==rear+1)){
		return 1;
	}else{
		return 0;
	}
}

int isEmpty(){
	if(front==-1&&rear==-1){
		return 1;
	}else{
		return 0;
	}
}

void peekFront(){
	if(isEmpty()){
		printf("The queue is empty!\n");
	}else{
		printf("The peeked element is: %d\n",deque[front]);
	}
}

void peekRear(){
	if(isEmpty()){
		printf("The queue is empty!\n");
	}else{
		printf("The peeked element is: %d\n",deque[rear]);
	}
}
