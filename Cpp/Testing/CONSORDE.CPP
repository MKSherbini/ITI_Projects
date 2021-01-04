#include <iostream.h>
#include <conio.h>

class Line{
public:
	int y, x;
	Line():x(1), y(x){}
};
int main(){
	clrscr();

    cout<<Line().y;

	getch();
	return 0;
}

