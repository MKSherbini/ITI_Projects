#include <stdio.h>
#include <conio.h>

typedef struct{
	int code;
	char name[50];
	double netSalary;
	int deduct;
	int bonus;

} Emp;

Emp * g_emps;
int g_totalEmps;
int g_eIdx=0;

void disp(int i, Emp e){
	int basex=2, basey=2, stepx=30, stepy=2;

	clrscr();

	gotoxy(1, 1);
	printf("Emp%d", i);
	gotoxy(basex, basey);
	printf("Emp Name: %s", e.name);

	gotoxy(basex+stepx, basey);
	printf("Enter Emp Code: %d", e.code);

	gotoxy(basex, basey+stepy);
	printf("Enter Emp Sal: %lf", e.netSalary);

	gotoxy(basex+stepx, basey+stepy);
	printf("Enter Emp Bonus: %d", e.bonus);

	gotoxy(basex, basey+2*stepy);
	printf("Enter Emp deduct: %d", e.deduct);

	getch();
}
int SafeScanf(){
	int x;
	while(scanf("%d", &x)!=1)
		while(getchar() != '\n');
	return x;
}
void read(int i, Emp* e){
	double d;
	int basex=2, basey=2, stepx=30, stepy=2;

	clrscr();

	gotoxy(1, 1);
	printf("Enter Emp%d", i);

	gotoxy(basex, basey);
	printf("Enter Emp Name");

	gotoxy(basex+stepx, basey);
	printf("Enter Emp Code");

	gotoxy(basex, basey+stepy);
	printf("Enter Emp Sal");

	gotoxy(basex+stepx, basey+stepy);
	printf("Enter Emp Bonus");

	gotoxy(basex, basey+2*stepy);
	printf("Enter Emp deduct");


	gotoxy(basex+15, basey);
	scanf("%s",(e->name));

	gotoxy(basex+stepx+15, basey);
	e->code = SafeScanf();

	gotoxy(basex+14, basey+stepy);
	scanf("%lf",&d);
	e->netSalary = d;

	gotoxy(basex+stepx+16, basey+stepy);
	e->bonus = SafeScanf();

	gotoxy(basex+17, basey+2*stepy);
	e->deduct = SafeScanf();

}

int menu(void){
	int basex=30, basey=10, stepx=0, stepy=2;
	int cmd;
	clrscr();
	gotoxy(basex,basey);
	printf("1-Add Employee\n");
	gotoxy(basex + stepx, basey + stepy);
	printf("2-Display Employees\n");
	gotoxy(basex + 2*stepx, basey + 2*stepy);
	printf("3-Delete Last\n");
	gotoxy(basex + 3*stepx, basey + 3*stepy);
	printf("4-Update By Idx\n");

	gotoxy(basex + 4*stepx, basey + 4*stepy);
	printf("5-Exit\n");

	scanf("%d", &cmd);
	return cmd;
}
void update(){
	int idx;
	clrscr();
	idx = SafeScanf();
	if(idx<g_eIdx)
		read(idx, &g_emps[idx]);

}

void main(void){

	int exit=0, idx=0;
	clrscr();
	printf("Enter Max Emps\n");
	scanf("%d", &g_totalEmps);
	g_emps = (Emp*) malloc(g_totalEmps*sizeof(Emp));
	while(!exit){

		int cmd = menu();

		switch(cmd){
			case 1:
				if(g_eIdx<g_totalEmps){
					read(g_eIdx, &g_emps[g_eIdx]);
					g_eIdx++;
					}
				break;
			case 2:
				for(idx=0;idx<g_eIdx;idx++)
					disp(idx, g_emps[idx]);
				break;
			case 3:
				if(g_eIdx>0)
					g_eIdx--;
				break;
			case 4:
				update();
				break;
			case 5:
				exit=1;
				break;
			default:
				break;

		}
	}
}