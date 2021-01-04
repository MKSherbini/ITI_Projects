package Lab3;
import java.util.function.*;

public class test {
    static double Converter(Function<Double, Double > fun, double p){
        return fun.apply(p);
    }
    static double Converter(Function<double[], Double > fun, double[] p){
        return fun.apply(p);
    }
    public static void main(String[] args) {
        System.out.println( Converter((x)->(x*9.0/5.0 + 32),0.0) );
        System.out.println( Converter((a)->((-a[1]+Math.sqrt(a[1]*a[1]-4*a[0]*a[2]))/(2*a[0])),new double[]{1,2,0}) );
        System.out.println( Converter((a)->((-a[1]-Math.sqrt(a[1]*a[1]-4*a[0]*a[2]))/(2*a[0])),new double[]{1,2,0}) );
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}

// pass converter for c to f
// get roots pass equation
