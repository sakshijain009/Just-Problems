#include<stdio.h>
#include<stdlib.h>
#include<math.h>

void maxHeapify(int A[],int n,int i);
void heapsort(int A[],int n);
void heapInsert(int A[],int *n,int data);
void heapDelete(int A[],int *n);
void printHeap(int A[],int n);
void buildHeap(int arr[], int n);

int main(){
	int n,i,arr[20];
	printf("Enter size: ");
	scanf("%d",&n);

	printf("Enter elements: ");
	for(i=1;i<=n;i++){
		scanf("%d",&arr[i]);
	}
	//Building Heap
	buildHeap(arr,n);
	printf("\nAfter building the heap is: ");
	printHeap(arr,n);
	
	//Inserting in Heap
	heapInsert(arr,&n,74);
	
	//Deleting in Heap
	heapDelete(arr,&n);
	
	//Sorting
	heapsort(arr,n);
}
void maxHeapify(int A[],int n,int i){
	int temp;
	int largest=i;
	int l=(2*i);
	int r=(2*i)+1;
	while(l<=n && A[l]>A[largest]){
		largest=l;
	}
	while(r<=n && A[r]>A[largest]){
		largest=r;
	}
	if(largest!=i){
		temp=A[i];
		A[i]=A[largest];
		A[largest]=temp;
		maxHeapify(A,n,largest);
	}
}

void heapsort(int A[],int n){
	int i,temp;
/*	for(i=n/2;i>=1;i--){
		maxHeapify(A,n,i);
	}*/
	for(i=n;i>1;i--){
		temp=A[1];
		A[1]=A[i];
		A[i]=temp;
	
		maxHeapify(A,i-1,1);
	}
	printf("Sorted heap is: ");
	printHeap(A,n);
}

void heapInsert(int A[],int *n,int data){
	int temp;
	*n=*n+1;
	A[*n]=data;
	int i=*n,parent;
	
	while(i>1){
		parent=floor(i/2);
		if(A[parent]<A[i]){
			temp=A[i];
			A[i]=A[parent];
			A[parent]=temp;
			i=parent;
		}
		else{
			return;
		}
	}
	printf("After insertion of %d heap is: ",data);
	printHeap(A,*n);
}

void heapDelete(int A[],int *n){
	if(*n<1){
		return;
	}
	int temp;
	temp=A[1];
	A[1]=A[*n];
	A[*n]=temp;

	*n=*n-1;
	int i=1;
	while(i<=floor(*n/2)){
		int r1=2*i;
		int r2=(2*i)+1;
		if(A[r1]>A[r2] && r1<=(*n)){
			temp=A[r1];
			A[r1]=A[i];
			A[i]=temp;
			i=r1;
		}else if(A[r1]<=A[r2] && r2<=(*n)){
			temp=A[r2];
			A[r2]=A[i];
			A[i]=temp;
			i=r2;
		}else{
			break;
		}
	}
	printf("After deletion heap is: ");
	printHeap(A,*n);
}

void printHeap(int A[],int n){
	int i;
	for(i=1;i<=n;i++){
		printf("%d ",A[i]);
	}
	printf("\n\n");
}

void buildHeap(int arr[], int n) 
{ 
    int startIdx = (n / 2); 
  	int i;
    for (i = startIdx; i >= 1; i--) { 
        maxHeapify(arr, n, i); 
    } 
}
