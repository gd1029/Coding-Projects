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

int main(int argc, char *argv[])
	
{
  int length;
  int i;
  length = my_strlen(argv[2]); 
   
   int j = 0;
	int thing = 0;
   //long arg = strtol(argv[2], NULL, 10);
   	for(j = 0; j<strlen(argv[2]); j++) {
           if (isdigit(argv[2][j])!=0) {
			   		thing = 1;
           	} else {
                   	thing = 0;				
				}
    }
    printf("newFileName = new-%s\n",argv[1]);
    printf ("password length = %d\n",length);
	if (length < 8)
	{
 	   printf("The password needs to have at least 8 characters.\n");
	
		 if (thing == 0) {
   			 printf("The password needs to contain at least one digit.\n");
		 }
	 }
	 else if (thing == 0) {
		 printf("The password needs to contain at least one digit.\n");
	 }
	  
}

	

