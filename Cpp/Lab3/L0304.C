#include <stdio.h>
#include <conio.h>

double pow(double base, int p){
 //	printf("%lf %d\n", base, p);
	if(p==1)return base;
	return base*pow(base, p-1);
}
void main()
{
	int x,y;
	double rs;
	clrscr();
	printf("Enter Base then power\n");
	scanf("%d %d", &x, &y);
	if(y<0) rs = pow(1.0/x, -y);
	if(y>0) rs = pow(x, y);
	if(y==0) rs = 1;
	if(x==0 && y==0){
		printf("Infinity");
	}else{
		printf("Power is: %lf", rs );
	}
    getch();
}