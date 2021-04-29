#include<stdio.h>
#include<stdlib.h>

struct node{
	struct node *up;
	struct node *down;
	struct node *left;
	struct node *right;
	int data;
};
struct node *root=NULL;
void arraytoLinkedList(int n,int a[n][n]);
struct node* create();
struct node* connRight(int n);
void printlist(int n);

int main(){
	int n,i,j;
	printf("Enter the order of matrix: ");
	scanf("%d",&n);
	int a[n][n];
	printf("Enter the matrix:\n");
	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			scanf("%d",&a[i][j]);
		}
	}
	arraytoLinkedList(n,a);
	printlist(n);
	return 0;
}

struct node* create(){
	struct node *temp;
	temp=(struct node*)malloc(sizeof(struct node));
	temp->down=NULL;
	temp->up=NULL;
	temp->left=NULL;
	temp->right=NULL;
	return temp;
}

void arraytoLinkedList(int n,int a[n][n]){
	int i,j;
	struct node *temp1,*temp,*p;
	temp=connRight(n);
	root=temp;
	for(i=0;i<n-1;i++){
		temp1=connRight(n);
		p=temp1;
		for(j=0;j<n;j++){
			temp->down=temp1;
			temp1->up=temp;
			temp=temp->right;
			temp1=temp1->right;
		}
		temp=p;
	}
	//Inputting data in linked list
	temp=root;
	for(i=0;i<n;i++){
		p=temp;
		for(j=0;j<n;j++){
			temp->data=a[i][j];
			temp=temp->right;
		}
		temp=p->down;
	}
}

//Makes Right connection along rows
struct node* connRight(int n){
	struct node *p,*q,*temp;
	int i;
	p=create();
	temp=p;
	for(i=0;i<n-1;i++){
		q=create();
		p->right=q;
		q->left=p;
		p=q;
	}
	return temp;
}

//Prints the 2D list
void printlist(int n){
	int i,j;
	struct node *p,*temp;
	temp=root;
	printf("THE MATRIX IN THE FORM OF LINKED LIST IS: \n");
	for(i=0;i<n;i++){
		p=temp;
		for(j=0;j<n;j++){
			printf("%d --> ",temp->data);
			temp=temp->right;
		}
		printf("\n");
		temp=p->down;
	}
}
