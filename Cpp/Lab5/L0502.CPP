#include <stdio.h>
#include <conio.h>
#include <iostream.h>

class Complex{
private:
	float r, i;

public:
	Complex():r(0), i(0){
		//cout<<"Constructing\n";
	}
	~Complex(){
		//cout<<"Destructing\n";
		//Print();
	}
	Complex(float r):r(r){
	}
	Complex(float r, float i):r(r), i(i){
	}
	inline void Read(int idx){
		cout<<"Enter Complex"<<idx<<" real then imaginary part\n";
		cin>>r;
		cin>>i;
	}
	void Print(){
		cout<<r;
		if(i==0){
			cout<<"\n";
			return;
		}
		if(i>0) cout<<" + ";
		cout<<i<<"i\n";
	}
	inline Complex add(Complex o){
		return Complex(r+o.r, i+o.i);
	}
	inline Complex sub(Complex o){
		return Complex(r-o.r, i-o.i);
	}

};
Complex addC(Complex c1, Complex c2){
	return c1.add(c2);
}
Complex subC(Complex c1, Complex c2){
	return c1.sub(c2);
}

void main(void)
{
	clrscr();
	Complex r, r2;
	r.Read(0);
	r2.Read(1);
	r.add(r).Print();
	subC(r, r2).Print();
	getch();
}