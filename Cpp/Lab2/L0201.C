		#include <stdio.h>
#include <conio.h>

void main(){
 int a[5];
 int mx,mn,idx=0;
 clrscr();
for(idx=0;idx<5;idx++){
	scanf("%d",a+idx);
}
 mx=mn=a[0];
 for(idx=0;idx<5;idx++){
  if(a[idx]>mx)mx=a[idx];
  if(a[idx]<mx)mn=a[idx];

 }
 printf("Min:%d, Max:%d",mn,mx);

 getch();

}