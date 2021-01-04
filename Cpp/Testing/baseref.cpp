/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <iostream>

class Base{
public:
    virtual void c(){
        std::cout<<("Hello World\n");
    }
};
class Derive: public Base{
public:
    void c() override{
        std::cout<<("Hello World2\n");
    }
};
int main()
{
    Base b;
    Derive d;
    Base& b2=d;
    Base* b3=&d;   
    // d.c();
    // b2.c();
    // b3->c();
    ((Base)d).c();
    ((Base*)&d)->c();

    return 0;
}
