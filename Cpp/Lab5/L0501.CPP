#include <stdio.h>
#include <conio.h>


void swap(int x, int y){
	int t=x;
	x=y;
	y=t;
}

void swap2(int &x, int &y){
	int t=x;
	x=y;
	y=t;
}
void main(void)
{
	int x, y;
	clrscr();
	printf("Enter the 2 numbers\n");
	scanf("%d %d", &x, &y);
	swap(x, y);
	printf("Swap by val: X=%d, Y=%d\n", x, y);
	swap2(x, y);
	printf("Swap by addr: X=%d, Y=%d\n", x, y);

	getch();
}