public class ComplexTest {
    public static void main(String[] args) {
        var c = new Complex<Double, Integer>(1.0,2);
        var c2= new Complex<Double, Integer>(3.0,2);
        System.out.println("C1: "+c);
        System.out.println("C2: "+c2);
        // System.out.println("Add: "+ ComplexUtils.add(c, c2));
        Complex<Double, Integer> r=c.add(c2);
        System.out.println("Add: "+ r);
        // System.out.println("Add: "+ c.add(c2));
        System.out.println("Sub: "+ c.sub(c2));
        System.out.println("Mul: "+ c.mul(c2));
        
    }
}

class Complex <TR extends Number, TI extends Number> {
    TR re;
    TI im;
    
    public Complex(TR r, TI i){
        re=r;
        im=i;
    }
    public Complex(){
        // re=TR(0);
        // im=TI(0);
    }
    
    public Complex add(Complex<TR, TI> o){
        return new Complex(re.doubleValue()+o.re.doubleValue(),
                           im.doubleValue()+o.im.doubleValue());
    }
    public Complex sub(Complex<TR, TI> o){
        return new Complex(re.doubleValue()-o.re.doubleValue(),
                           im.doubleValue()-o.im.doubleValue());
    }
    public Complex mul(Complex<TR, TI> o){
        return new Complex(re.doubleValue()*o.re.doubleValue()+im.doubleValue()*o.im.doubleValue(),
                           im.doubleValue()*o.re.doubleValue()+re.doubleValue()*o.im.doubleValue());
    }
    public String toString(){
        return new String("Re: "+re+", Img: "+im);
    }
}
// class ComplexUtils{
    
    // static public <TR extends Number, TI extends Number> Complex add(Complex<TR, TI> c, Complex<TR, TI> o){
        // return new Complex<TR, RI>(c.re.doubleValue()+o.re.doubleValue(),
                           // c.im.doubleValue()+o.im.doubleValue());
    // }
// }