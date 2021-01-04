#include <stdio.h>
#include <conio.h>


int SafeScanf(){
	int x;
	while(scanf("%d", &x)!=1)
		while(getchar() != '\n');
	return x;
}

void main(void)
{
	int x, y;
	int **arr;
	int i, j;
	int basex=20, basey=5, stepx=5, stepy=5;
	int * sums;
	double * aves;
	clrscr();
	printf("Enter the 2 numbers: x, y\n");
	x = SafeScanf();
	y = SafeScanf();
	arr = (int**)malloc(y*sizeof(int*));

	for(i=0;i<y;i++)
		arr[i]=(int*)malloc(x*sizeof(int));

	sums = (int*) malloc(y*sizeof(int));
	aves = (double*) malloc(x*sizeof(double));

	for(j=0;j<y;j++)
	for(i=0;i<x;i++)
	{
			gotoxy(basex+i*stepx, basey+j*stepy);
			arr[j][i]=SafeScanf();
	}

	for(j=0;j<y;j++)
	{
		int t=0;
		for(i=0;i<x;i++)
		   t+=arr[j][i];
		sums[j]=t;
	}
	for(i=0;i<x;i++){
		double t=0;
		for(j=0;j<y;j++)
		   t+=arr[j][i];
		aves[i]=t/y;
	}

	for(j=0;j<y;j++){
		gotoxy(basex+stepx*x, basey+stepy*j);
		printf("%d",sums[j]);
	}
	for(i=0;i<x;i++){
		gotoxy(basex+stepx*i, basey+stepy*y);
		printf("%1.1lf", aves[i]);
	}


	getch();
}