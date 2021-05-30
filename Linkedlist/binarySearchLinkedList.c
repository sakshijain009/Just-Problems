#include<stdio.h>
#include<stdlib.h>

struct node{
	int data;
	struct node* link;
};
struct node* root;

struct node* newNode(int d);
void add(int d);
void display();
int binarySearch();
int length();

int main(){
	int ch,flag=0,d;
	printf("****BINARY SEARCH USING LINKED LIST****\n");
	printf("1.Enter elements\n2.Binary Search\n3.Display\n");
	printf("4.Exit\n");
	while(1){
		printf("\nEnter choice: ");
		scanf("%d",&ch);
		switch(ch){
			case 1:
				printf("Enter elements: ");
				scanf("%d",&d);
				add(d);
				break;
			case 2:
				printf("Enter the element to be searched: ");
				scanf("%d",&d);
				flag=binarySearch(d,1,length());
				if(flag!=0){
					printf("Value Found at position %d\n",flag);
				}else{
					printf("Value NOT Found\n");
				}
				break;
			case 3:	
				display();
				break;
			case 4:
				exit(0);
			default:
				printf("Enter valid choice.\n");	
		}
	}
	return 0;
}

struct node* newNode(int d){
	struct node* temp;
	temp=(struct node*)malloc(sizeof(struct node));
	temp->data=d;
	temp->link=NULL;
	return temp;	
}

void add(int d){
	struct node* temp;
	temp=newNode(d);
	if(root==NULL){
		root=temp;
	}else{
		struct node* p;
		p=root;
		while(p->link!=NULL){
			p=p->link;
		}
		p->link=temp;
	}
}

void display(){
	struct node *temp;
	temp=root;
	if(temp==NULL){
		printf("The list is empty!\n");
	}else{
		printf("The list is: ");
		while(temp!=NULL){
			printf(" %d -->",temp->data);
			temp=temp->link;
		}
		printf("\n");
	}
}

int binarySearch(int d,int lb,int ub){
	if(lb>ub){
		return 0;
	}else{
		int mid=(ub+lb)/2,i=1;
		struct node *temp=root;
		while(i<mid){
			temp=temp->link;
			i++;
		}
		if(temp->data==d){
			return mid;
		}else if(temp->data>d){
			binarySearch(d,lb,mid-1);
		}else if(temp->data<d){
			binarySearch(d,mid+1,ub);	
		}
	}
	
}

int length(){
	int count=0;
	struct node *temp;
	temp=root;
	while(temp!=NULL){
		count++;
		temp=temp->link;
	}
	return count;
}
