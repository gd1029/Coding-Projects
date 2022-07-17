
#include <stdio.h>
#include <string.h>

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
   printf("newFileName = new-%s\n",argv[1]);
   printf ("password length = %d\n",length);
  }
