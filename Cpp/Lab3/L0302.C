#include <stdio.h>
#include <conio.h>

typedef struct
{
	int code;
	char name[50];
	int netSalary;
} Emp;

void disp(int i, Emp e)
{
	printf("Emp%d data:\n", i);
	printf("Code: %d\n", e.code);
	printf("Name: %s\n", e.name);
	printf("Sal: %d\n", e.netSalary);
}
void main()
{
	int i;
	Emp e[5];
	clrscr();
	for(i=0;i<5;i++){
		printf("Enter Emp%d Code\n", i);
		scanf("%d",&e[i].code);
		printf("Enter Emp%d Name\n", i);
		scanf("%s",e[i].name);
		printf("Enter Emp%d Sal\n". i);
		scanf("%d",&e[i].netSalary);
	}
	for(i=0;i<5;i++){
		disp(i,e[i]);
	}
    getch();
}