#include <stdio.h>
#include <stdlib.h>



struct linkedNumbers {
	
	int x;
	struct linkedNumbers *next;
	
};
typedef struct linkedNumbers node;

node *m1; // multiplicand
node *m2; // multiplier
node *result;
node *a;
node *b;
node *tempResult;
node *tempResult2;
node *tempResult3;
node *result;
int base;
int count = 0;
int digitOfMultiplicand;
int digitOfMultiplier;



void addBeginning(node **n, int x){
	
	node *newNode = malloc(sizeof (node));
	newNode -> x = x;
	newNode -> next = *n;
	*n = newNode;
	
}

void printNode(node *n){
	node *current_node = n;
	while ( current_node != NULL ) {
        printf("%d", current_node->x);
        current_node = current_node->next;
    }
}

void addLast(node **n, int x)
{
    
    node *newNode = malloc(sizeof(node));
    newNode->x = x;
    newNode->next = NULL;
    
    
    if(*n == NULL) {
         *n = newNode;
     }
      else
    {
        node *lastNode = *n;

        
        while(lastNode->next != NULL)
        {
            lastNode = lastNode->next;
        }

        
        lastNode->next = newNode;
    }

}


static void reverse(node **n)
{
    node *prev = NULL;
    node *current = *n;
    node *next = NULL;
    while (current != NULL) {
       
        next = current->next;
        current->next = prev;
 
        prev = current;
        current = next;
    }
    *n = prev;
}

int getCount(node *n)
{
    int count1 = 0; 
    node* current = n; 
    while (current != NULL) {
        count1++;
        current = current->next;
    }
    return count1;
}

void addTwoLists(node *n, node *r){
	
	int i;
	int total;
	int residual2 = 0;
	int countNumber = getCount(n);
	count++;
	node *temp1 = n;
	node *temp2 = r;
	node *temp3 = tempResult2;
	
	if (count != 1){
		r = tempResult3;
	}
	
	for (i = 0; i < count; i++){
		addBeginning(&n, 0);
		addLast(&r, 0);
	}
	
	countNumber = getCount(n);
	
	for (i = 0; i < countNumber; i++){
		total = (n->x + r->x) % base;
		
		total += residual2;
		total = total % base;
		
		
		addLast(&tempResult3, total);
		residual2 = (n->x + r->x) / base;
		n = n->next;
		r = r->next;
		
	}
}



void multiplication (node *n, node *r) {
	int i;
	int j;
	int k;
	int temp = 0;
    int tempp = 0;
    int temppp = 0;
	 
	
	node *tempResult3 = tempResult;
	node *tempMultiplicand = n;
	node *tempMultiplier = r;
	tempResult2 = tempResult;
	
	
    for (i = 0; i < digitOfMultiplier; i++) {
    	
    	for (j = 0; j < digitOfMultiplicand; j++){
    		
    		tempp = ((tempMultiplicand->x) * (tempMultiplier->x)) % base;
    			
    			addLast(&tempResult, tempp + temppp);	
    			
    
    		
    		temppp = ((tempMultiplicand->x) * (tempMultiplier->x)) / base;
    		
	
    		tempMultiplicand = tempMultiplicand->next;
    		
    		if (j == digitOfMultiplicand - 1) {
    		tempMultiplicand = n;
			}
			
		}
		if (i != 0) {
		
		
		for (k = 0; k < digitOfMultiplicand; k++){

			addLast(&tempResult2, tempResult->x);
			tempResult = tempResult->next;
			
		}
	}
		if (i != 0){
			addTwoLists(tempResult, tempResult2);
		}
			tempMultiplier = tempMultiplier->next;
			if (j == digitOfMultiplier - 1) {
    		tempMultiplier = r;
		    }	
	}
}
	


int main(int argc,char* argv[]) {
	char data;
	int x;
	
	if(argc==1)
        printf("Error message!");
    if(argc>=2)
    {
        FILE* input_file = fopen(argv[1], "r");
        
         while((data = fgetc(input_file)) !='\n') {
         	x = data - 48;
         	addBeginning(&a, x);
         	digitOfMultiplicand++;
		 }
		 
		 while((data = fgetc(input_file)) !='\n') {
         	x = data - 48;
         	addBeginning(&b, x);
         	digitOfMultiplier++;
		 }
		 
		 fscanf(input_file, "%d", &base);
		 fclose(input_file);
    }
	
	 
	m1 = (node*)malloc(sizeof (node));
	m1 -> x = 0;
	m1->next = NULL;
	a = m1;
	b = (node*)malloc(sizeof (node));
	b->x = 0;
	result = (node*)malloc(sizeof (node));
	result->x = 0;
	b->next= NULL;
	
	a = a->next;
	b = b->next;
	multiplication(a, b);
	reverse(&a);
	reverse(&b);

	printNode(a);
	printf("\n");
	printNode(b);
	printf("\n");
	reverse(&tempResult3);
	printNode(tempResult3);
	
	
	
	return 0;

}
