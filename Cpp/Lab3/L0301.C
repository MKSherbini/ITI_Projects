#include <stdio.h>
#include <conio.h>

typedef struct{
	int code;
	char name[50];
	double netSalary;
} Emp;

void disp(Emp e){
	clrscr();
	gotoxy(1,1);
	printf("Emp data:\n");
	printf("Code: %d\n", e.code);
	printf("Name: %s\n", e.name);
	printf("Sal: %lf\n", e.netSalary);
	getch();
}
void read(Emp* e){
	double d;
	clrscr();
	gotoxy(1,1);
	printf("Enter Emp Code\n");
	scanf("%d",&(e->code));
	printf("Enter Emp Name\n");
	scanf("%s",(e->name));
	printf("Enter Emp Sal\n");
	scanf("%lf",&d);
	e->netSalary = d;

}
int menu(void){
	int basex=30, basey=10, stepx=0, stepy=2;
	int cmd;
	clrscr();
	gotoxy(basex,basey);
	printf("1-Enter Employee\n");
	gotoxy(basex + stepx, basey + stepy);
	printf("2-Display Employee\n");
	gotoxy(basex + 2*stepx, basey + 2*stepy);
	printf("3-Exit\n");

	scanf("%d", &cmd);
	return cmd;
}

void main(void){
	Emp e={-1,"No Name",-1.0};
	int exit=0;
	while(!exit){

		int cmd = menu();

		switch(cmd){
			case 1:
				read(&e);
				break;
			case 2:
				disp(e);
				break;
			case 3:
				exit=1;
				break;
			default:
				break;

		}
	}
}