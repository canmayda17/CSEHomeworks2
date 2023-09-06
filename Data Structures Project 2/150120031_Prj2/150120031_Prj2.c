#include <stdio.h>
#include <stdlib.h>

// Sukru Can Mayda - 150120031

int comparisonsNumber = 0;
int rotationNumber = 0;

typedef struct AVL_TREE {
	int key;
	int height;
	struct AVLTree *left;
	struct AVLTree *right;
}AVLTree;

typedef struct SPLAY_TREE {
	int key;
	int frequency;
	struct SplayTree *left;
	struct SplayTree *right;
}SplayTree;


AVLTree* avlCreateNode(int key){
	AVLTree *node = malloc(sizeof(AVLTree));
	node->key = key;
	node->height = 1;
	node->left = NULL;
	node->right = NULL;
	return node;
}

SplayTree* splayCreateNode(int key){
	SplayTree *node = malloc(sizeof(SplayTree));
	if (node == NULL){
		return 0;
	}
	
	node->key = key;
	node->frequency = 1;
	node->left = NULL;
	node->right = NULL;
	return node;
}

int max(int num1, int num2)
{
    return (num1 > num2 ) ? num1 : num2;
}

int getBalance(AVLTree *node){
	if (node == NULL) {
		return 0;
	}
	else{
		AVLTree *a = malloc(sizeof(AVLTree));
		AVLTree *b = malloc(sizeof(AVLTree));
		a = (AVLTree*)node->left;
		b = (AVLTree*)node->right;
		
		return (a->height - b->height);
	}
}


int updateHeight(AVLTree *node){
	if (node == NULL){
		return 0;
	}
	
	int leftHeight = updateHeight((AVLTree*)node->left);
	int rightHeight = updateHeight((AVLTree*)node->right);
	
	return (max(leftHeight, rightHeight) + 1);
}

int searchKey(AVLTree *node, int key){
	if (node == NULL){
		comparisonsNumber++;
		return 0;
	}
	
	if (key < node->key){
		comparisonsNumber++;
		return search(node->left, key);
	}
	else{
		comparisonsNumber++;
		if (key == node->key){
			return node->key;
		}
		else{
			return search(node->left, key);
		}
	}
}


AVLTree* avlRotateLeft(AVLTree *node){
	
	AVLTree *nodeR = malloc(sizeof(AVLTree));
	nodeR = (AVLTree*)node->right;
	AVLTree *nodeRtoL = malloc(sizeof(AVLTree));
	nodeRtoL = (AVLTree*)nodeR->left;
	
	nodeR->left = node;
	node->right = nodeRtoL;
	node->height = updateHeight(node);
	nodeR->height = updateHeight(nodeR);
	
	return nodeR;
}

AVLTree* avlRotateRight(AVLTree *node){
	AVLTree *nodeL = malloc(sizeof(AVLTree));
	AVLTree *nodeLtoR = malloc(sizeof(AVLTree));
	nodeL = (AVLTree*)node->left;
	nodeLtoR = (AVLTree*)nodeL->right;
	
	nodeL->right = node;
	node->left = nodeLtoR;
	node->height = updateHeight(node);
	nodeL->height = updateHeight(nodeL);
	
	return nodeL;
}


SplayTree* splayRotateLeft(SplayTree *node){
	SplayTree *nodeR = malloc(sizeof(AVLTree));
	nodeR = (SplayTree*)node->right;
	node->right = nodeR->left;
	nodeR->left = node;
	comparisonsNumber++;
	
	return nodeR;
}

SplayTree* splayRotateRight(SplayTree *node){
	SplayTree *nodeL = malloc(sizeof(SplayTree));
	nodeL = (SplayTree*)node->left;
	node->left = nodeL->right;
	nodeL->right = node;
	comparisonsNumber++;
	
	return nodeL;
}


// I couldn't find a algorithm for splaying. So this section is kind of garbage.
SplayTree* splay(SplayTree *node, int key){
	
	if (node == NULL){
		return NULL;
	}
	
	else{
		
		if(key == node->key){
			return node;
		}
		else if(key < node->key){
			if (node->left == NULL){
				return node;
			}
		}
		else if(node->key < key){
			if(node->right == NULL){
				return node;
			}
		}
	}
	
}

AVLTree* insertKey(AVLTree* node, int key){
	
	if (node == NULL){
		comparisonsNumber++;
		return(AVLTree*)(avlCreateNode);
	}
	if (key < node->key){
		comparisonsNumber++;
		node->left = insertKey(node->left, key);
	}
	else if (key > node->key){
		comparisonsNumber++;
		node->right = insertKey(node->right, key);
	}
	else{
		node->key++;
		return node;
	}
	
	node->height = updateHeight(node);
	
	if (getBalance(node) < -1 && key > node->right->key){ // right right
		rotationNumber++;
		return rotateLeft(node);
	}
	else if (getBalance(node)< -1 && key < node->right->key){ // right left
		rotationNumber = rotationNumber + 2; // double rotation
		node->right = rotateRight(node->right);
		return rotateLeft(node);
	}
	else if (getBalance(node) > 1 && key > node->left->key){ // left right
		rotationNumber = rotationNumber + 2;
		node->left = rotateLeft(node->left);
		return rotateRight(node);
	}
	else if (getBalance(node > 1 && key < node->left->key)){ // left left
	    rotationNumber++;
	    return rotateRight(node);
	}
	
	return node;
	
}

SplayTree* insertKey(SplayTree *node, int key){
	
	if (node == NULL){
		comparisonsNumber++;
		return splayCreateNode();
	}
	if (key < node->key){
		comparisonsNumber++;
		node->left = insertKey(node->left, key);
	}
	else if (key > node->key){
		comparisonsNumber++;
		node->right = insertKey(node->right, key);
	}
	else{
		node->frequency++;
		return node;
	}
	
	return node;
}







int main(int argc, char **argv) { 
    // Open the input text file
    FILE *fp = fopen(argv[1], "r");
    if (fp == NULL) {
        perror("Error! This file is empty.");
        return 1;
    }

    // Initialize the AVL tree
    AVLTree *avlNode = NULL;
    SplayTree *splayNode = NULL;

    int k = 0;
    while ((k = fgetc(fp)) != EOF) {
        avlNode = insertKey(avlNode, k);
    }
    
    // Print the total number of comparisons and rotations for the Splay tree
    printf("AVL tree comparisons=%d,\n Splay tree rotations=%d\n", comparisons, rotations);
    fclose(fp);
    
    FILE *fp = fopen(argv[1], "r");
    if (fp == NULL) {
        perror("Error opening file");
        return 1;
    }
    k = 0;
    
    while ((k = fgetc(fp)) != EOF) {
        splayNode = insertKey(splayNode, k);
        splayNode = splay(splayNode, k);
    }
    fclose(fp);

    // Print the total number of comparisons and rotations for the Splay tree
    printf("Splay tree comparisons=%d,\n Splay tree rotations=%d\n", comparisons, rotations);

    return 0;
}
