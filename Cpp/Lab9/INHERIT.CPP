#include <iostream.h>
#include <conio.h>
#include <graphics.h>
#include <stdlib.h>
#include <stdio.h>
#include <dos.h>


class Shape{
protected:
	int dim1, dim2;
public:
	Shape(int x, int y):dim1(x), dim2(y){}
	void Area(){}
	void SetX(int x){dim1=x;}
	void SetY(int y){dim2=y;}
	int GetX(){return dim1;}
	int GetY(){return dim2;}
};

class Circle: protected Shape{

public:
	Circle(int r):Shape(r,r){}
	void SetDim(int r){dim1=dim2=r;}
	void Area(){
		 cout<<dim1*dim2*3.14<<endl;
	}
};
class Rect: public Shape{

public:
	Rect(int x, int y):Shape(x,y){}
	void Area(){
		cout<<dim1*dim2<<endl;
	}
};
class Square: protected Rect{
public:
	Square(int x):Rect(x,x){}
	void SetDim(int x){dim1=x;dim2=x;}
	void Area(){
		cout<<dim1*dim2<<endl;
	}
};
class Triangle: public Shape{
public:
	Triangle(int x, int y):Shape(x,y){dim1=x;dim2=y;}
	void Area(){
		cout<<dim1*dim2*0.5<<endl;
	}
};
int main(){
   clrscr();

   Circle c(7);
   Triangle t(20,10);
   t.Area();
   Rect r(2,5);
   r.Area();
   Square s(5);
   s.Area();

   s.SetX(4);
   s.Rect::SetY(5);
   c.SetY(3);
   getch();
   return 0;
}

