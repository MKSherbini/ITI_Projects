import java.lang.annotation.*;
import java.lang.reflect.*;



@Author(name="Sss")
public class Test2 {

        
    @Author(name="Prr")
    public static void print(){
        Test2 ob = new Test2();
        
        try{
            Class<?> c = ob.getClass();
            // for(var a:c.getMethods())
                // System.out.println(a);
            Method m = c.getMethod("print");
            Author anno = m.getAnnotation(Author.class);
            System.out.println("print by: "+anno.name());
            System.out.println("Test2 by: "+c.getAnnotation(Author.class).name());
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }


    public static void main(String[] args) {
        print();
        
    }

}
