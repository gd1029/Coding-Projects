// Example on using XOR operation
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

int my_strlen(char *string) //Function to calculate length of given string
{
    int i;
    for(i=0;string[i]!='\0';i++);
    return i;
}

int main(int argc, char *argv[]){
    char *password;
	int length;
	int a;
	
	password = argv[2];
	length = my_strlen(argv[2]);
	char src[50], dest[50];
    FILE *file,*newFile; 
    char block[length];
	strcpy(src,argv[1]);
	strcpy(dest,"new-");
	strcat(dest, src);
    int k, numBytes;

    // argv[1] is the starting address of the name of the input file
    // argv[2] is the starting address of the name of the output file
	file = fopen(argv[1], "rb");
	newFile = fopen(dest, "wb");
	//fprintf(newFile,"abcd %s\n",block);

    // repeatedly read 10 bytes from the input file
    // and XOR the 10 bytes with the value in array password
	

	numBytes = fread(block, 1, length, file);
		for (k=0; k<5; k++) {
		block[k] = block[k]^password[k];
      // write the result of XOR to the output file
      	printf("%hhx\n", block[k]);
			//fprintf(newFile,"abcd %s\n",block);
	}
    fclose(newFile);
	fclose(file);
}
