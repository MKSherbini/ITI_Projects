#include <stdio.h>
#include <conio.h>
#include <string.h>

void main(){

char fn[10],ln[10],full[20];
 clrscr();
full[0]='\0';
printf("Enter first name\n");
scanf("%s",fn);
printf("Enter last name\n");
scanf("%s",ln);
strcat(full,fn);
strcat(full," ");
strcat(full,ln);
printf("full name is: %s",full);

 getch();

}