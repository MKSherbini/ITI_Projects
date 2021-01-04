#include <stdio.h>
#include <conio.h>
#include <iostream.h>
#include <math.h>

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
	//explicit Complex(float r):r(r){}
	Complex(float r, float i):r(r), i(i){
	}
	operator float(){
		return sqrt(r*r + i*i);
	}
	Complex& operator=(const Complex& o){
		i=o.i;
		r=o.r;
		return *this;
	}
	Complex(const Complex& o){
		*this=o;
	}
	Complex operator+(const Complex& o){
		return Complex(r+o.r, i+o.i);
	}
	Complex operator-(const Complex& o){
		return Complex(r-o.r, i-o.i);
	}
	Complex operator+(const float& f)const{
		return Complex(r+f, i);
	}
	friend int operator==(const Complex& o1, const Complex& o2){
		return (o1.i==o2.i)&&(o1.r==o2.r);
	}
	friend ostream& operator<<(ostream& o, const Complex& c){
		c.Print();
		return o;
	}
	friend istream& operator>>(istream& i, Complex& o){
		o.Read();
		return i;
	}
	Complex& operator++(){
		r++;
		return *this;
	}
	Complex operator++(int){
		Complex t=*this;
		operator++();
		return t;
	}
	Complex& operator+=(const Complex& o){
		r+=o.r;
		i+=o.i;
		return *this;
	}
	friend Complex operator+(float f, const Complex& o){
		return o+f;
	}
	void SetAll(float r){this->r=r;}
	void SetAll(float r, float i){this->r=r;this->i=i;}
	inline void Read(int idx=0){
		cout<<"Enter Complex"<<idx<<" real then imaginary part\n";
		cin>>r;
		cin>>i;
	}
	void Print()const{
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
	Complex sr[2];
	cout<<"Enter 2 Complex\n";
	cin>>sr[0]>>sr[1];
	sr[0].Print();
	sr[1].Print();

	int sz;
	cout<<"Enter dynamic Size\n";
	cin>>sz;
	Complex *dr= new Complex[sz];
	cout<<"Enter dynamic Complex\n";
	for(int i=0;i<sz;i++)
	   cin>>dr[i];
	for(int i=0;i<sz;i++)
		dr[i].Print();



 //	cout<<"C1+C2 = "<<(r+r2)<<endl;
 //	cout<<"C1-C2 = "<<(r-r2)<<endl;
 //	cout<<"5+C2 = "<<(5+r2)<<endl;
 // cout<<"C1+5 = "<<(r+5)<<endl;
 // cout<<"C1==C2 = "<<(r==r2)<<endl;
 //	cout<<"C1+=C2 = "<<(r+=r2)<<endl;
 //	cout<<"C1++ = "<<r++<<endl;
 //	cout<<"++C1 = "<<++r<<endl;
 //	cout<<"(float)C1 = "<<(float)r<<endl;
	getch();
}