#include<stdio.h>
#include<stdlib.h>

#define CAPACITY 5
int queue[CAPACITY],front=-1,rear=-1;

void enqueue(int);
int isFull();
int isEmpty();
void peek();
void dequeue();

int main(){
	printf("*******QUEUE OPERATIONS********\n");
	printf("1.Enqueue\n2.Dequeue\n3.Peek\n4.Quit\n");
	int ch,item;
	while(1){
		printf("\nEnter your choice: ");
		scanf("%d",&ch);
		switch(ch){
			case 1:
				printf("Enter the value you want to enter: ");
				scanf("%d",&item);
				enqueue(item);
				break;
			case 2:
				dequeue();
				break;
			case 3:
				peek();
				break;
			case 4:
				exit(0);
			default:
				printf("Enter the correct choice!\n");
		}
	}

}

void enqueue(int data){
	if(isFull()){
		printf("The queue is full!\n");
		return;
	}else if(isEmpty()){
		front=0;
		rear=0;
	}else{
		rear++;
	}
	queue[rear]=data;
	printf("Your data has been inserted.\n");
}

void dequeue(){
	if(isEmpty()){
		printf("The queue is empty!.\n");
		return;
	}else if(front==rear&&front!=-1){
		front=-1;
		rear=-1;
	}else{
		front++;
	}
	
	printf("The data has been removed.\n");
}

int isEmpty(){
	if(front==-1&&rear==-1){
		return 1;
	}else{
		return 0;
	}
}

int isFull(){
	if(rear==CAPACITY-1){
		return 1;
	}else{
		return 0;
	}
}

void peek(){
	if(isEmpty()){
		printf("The queue is empty.\n");
	}else{
		printf("The peeked element is: %d\n",queue[front]);
	}
}
