/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

class Base{
public:
int x;    
};
class Base1:public  Base{

};
class Base2:public  Base{

};
class Derived:public Base2, public Base1{
    
};

int main()
{
    Derived d;
    d.Base2::x;
    d.Base1::x;
    
    printf("Hello World");

    return 0;
}
