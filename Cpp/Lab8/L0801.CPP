#include <iostream.h>
#include <conio.h>
#include <graphics.h>
#include <stdlib.h>
#include <stdio.h>
#include <dos.h>

class Point{

private:
	int x, y;
public:
	Point():x(0), y(0){}
	Point(int n):x(n), y(n){}
	Point(int x, int y):x(x), y(y){}
	void SetXY(int n){SetXY(n,n);}
	void SetXY(int x, int y){this->x=x, this->y=y;}
	int GetX(){return x;}
	int GetY(){return y;}
	void Print()const{
		cout<<"X: "<<x<<", Y: "<<y<<endl;
	}
	friend ostream& operator<< (ostream& o, const Point& p){
		p.Print();
		return o;
	}
};
class Shape{
public:
	virtual void Draw()=0;
};
class Line: public Shape{
private:
	Point s, e;
public:
	Line(Point s, Point e):s(s), e(e){}
	void Draw(){
		line(s.GetX(), s.GetY(), e.GetX(), e.GetY());
	}
};
class Circle: public Shape{
private:
	int r;
	Point c;
public:
	Circle(Point c, int r):c(c), r(r){}
	void Draw(){
		ellipse(c.GetX(), c.GetY(), 0, 360,
			r, r);
	}
};
class Rect: public Shape{
private:
	Point ltop, rbot;
public:
	Rect(Point lt, Point rb):ltop(lt), rbot(rb){}
	void Draw(){
		 rectangle(ltop.GetX(), ltop.GetY(), rbot.GetX(), rbot.GetY());
	}
};

class Pic: public Shape{
private:
	//int nl, nc, nk;
	//Circle* cs;
	//Line* ls;
	//Rect* rs;
	Shape** shapes;
	int ns;
public:
	//Pic(Line *ls=NULL, int nl=0, Circle *cs=NULL, int nc=0,
	//	Rect *rs=NULL, int nr=0){}
	Pic(Shape** shapes, int ns):shapes(shapes), ns(ns){}
	void Draw();
};
void Pic::Draw(){
	for(int i=0; i<ns; i++){
	   if(shapes[i]) shapes[i]->Draw();
	}
}
int main(){

   /* request auto detection */
   int gdriver = DETECT, gmode, errorcode;
   int xmax, ymax;

   /* initialize graphics and local variables */
   initgraph(&gdriver, &gmode, "D://BGI");

   /* read result of initialization */
   errorcode = graphresult();
   /* an error occurred */
   if (errorcode != grOk)
   {
	  printf("Graphics error: %s\n", grapherrormsg(errorcode));
	  printf("Press any key to halt:");
	  getch();
	  exit(1);
   }

   setcolor(getmaxcolor());
   xmax = getmaxx();
   ymax = getmaxy();

   Point c(xmax, ymax);
   int cx=xmax/2;
   int cy=ymax/2;

   const int ns=9;
   Shape *shapes[ns]={
		new Line(Point(cx,cy-ymax/4),Point(cx,cy+ ymax/4)),
		new Line(Point(cx,cy-ymax/4),Point(cx+xmax/5, cy)),
		new Line(Point(cx,cy-ymax/4),Point(cx-xmax/5, cy)),
		new Line(Point(cx,cy+ymax/4),Point(cx+xmax/5, ymax)),
		new Line(Point(cx,cy+ymax/4),Point(cx-xmax/5, ymax)),
	   //	new Line(Point(cx+xmax/18,ymax/7),Point(cx+xmax/18, ymax/7)),
		new Line(Point(cx+xmax/25,ymax/6),Point(cx-xmax/25, ymax/6)),
		new Circle(Point(cx,ymax/8),ymax/8),
		new Circle(Point(cx-xmax/20,ymax/8),ymax/80),
		new Circle(Point(cx+xmax/20,ymax/8),ymax/80),
   };

   //Pic(shapes,ns).Draw();
   int tx=cx-xmax/18;
   int ty=ymax/7;
   int inc=0;
   while(!kbhit()){
	cleardevice();
	Pic(shapes,ns).Draw();

	Line(Point(cx+xmax/18,ty),Point(cx+xmax/18, ty+inc)).Draw();
	Line(Point(tx,ty),Point(tx, ty+inc)).Draw();
	inc++;
	delay(100);

   }

   /* clean up */
   getch();
   closegraph();
   return 0;
}

