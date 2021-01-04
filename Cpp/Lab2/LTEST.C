#include <stdio.h>
#include <conio.h>

void main()
{
	int n;
	int scalex=5;
	int scaley=1;
	int cnt = 1;
	int total;
	int row = 0, col;
	clrscr();
	scanf("%d", &n);
	total = n * n;
	col = n / 2;
	scalex=75/n;
	scaley=25/n;
	gotoxy((col)*scalex+1,(row)*scaley+1);
	printf("%d",cnt);

	while (cnt < total)
	{
		if (cnt % n == 0)
		{
			row++;
			row %= n;
		}
		else
		{
			row--;
			col++;
			col %= n;
			row += n;
			row %= n;
		}
		cnt++;
		gotoxy((col)*scalex+1,(row)*scaley+1);
		printf("%d",cnt);
	}
	getch();
}