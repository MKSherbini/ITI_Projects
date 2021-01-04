#include <iostream.h>
#include <conio.h>


class Stack{

private:
	int top;
	int size;
	int *ptr;
	static int cnt;

public:
	int isFull();
	int isEmpty();
	static int getCounter();
	Stack();
	Stack& operator=(const Stack& o){
		cnt++;
		top=o.top;
		size=o.size;
		if(o.size>0) delete[] ptr;
		ptr= new int[size];
		for(int i=0;i<size;i++)ptr[i]=o.ptr[i];
		return *this;
	}
	Stack(const Stack& o){
		cout<<"Copy constructor\n";
		cnt++;
		top=o.top;
		size=o.size;
		ptr= new int[size];
		for(int i=0;i<size;i++)ptr[i]=o.ptr[i];
	}
	Stack(int n);
	~Stack();
	int push(int n);
	int pop(int &n);
	friend void viewContent(Stack s);
};
int Stack::getCounter(){
cout<<"Stack cnt is: "<<cnt<<endl;
return cnt;
}
Stack::Stack(){
top=0;
size=10;
ptr = new int[size];
cnt++;
cout<<"Constructor with 1 param\n";
}
Stack::Stack(int n){
top=0;
size=n;
ptr = new int[size];
cnt++;
cout<<"Constructor with one param\n";
}
Stack::~Stack(){
delete[] ptr;
size=0;
cnt--;
cout<<"Destructor\n";
}
int Stack::isFull(){return top==size;}
int Stack::isEmpty(){return top==0;}
int Stack::push(int n){
if(isFull())
	return 0;
ptr[top] = n;
top++;
return 1;
}
int Stack::cnt = 0;
int Stack::pop(int &n){
 if(isEmpty())
	return 0;
 top--;
 n=ptr[top];
 return 1;
 }
void viewContent(Stack s){
	cout<<"Stack content\n";
	for(int i=0;i<s.top;i++){
		cout<<"\n Element no ("<<i+1<<") +"<<s.ptr[i];
	cout<<"\n";
	}
}

int main()
{
	clrscr();
	int num;
	Stack s1(5);
	s1.getCounter();
	{
	 Stack s2;
	 s2.getCounter();
	}
	s1.getCounter();
	viewContent(s1);
	s1.getCounter();
	s1.push(5);
	s1.push(14);
	s1.push(20);
	viewContent(s1);
	if(s1.pop(num)){
		cout<<"Popped "<<num<<endl;
	}


	getch();
   return 0;
}