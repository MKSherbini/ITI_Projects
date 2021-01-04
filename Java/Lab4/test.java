import java.util.*;
public class test {
    
    static void tester(List<? super Shape> arr){
        // for(var o:arr)System.out.println(o);
        arr.add(new Circle());
        System.out.println(".size(): "+arr.size());
        for(var o:arr) o.draw();
    }
    public static void main(String[] args) {
        // tester(new Shape[]{new Rect(), new Circle()});
        List<Rect> rects= new ArrayList<Rect>();
        List<Circle> circs= new ArrayList<Circle>();
        // rects.add(new Rect());
        circs.add(new Circle());
        tester(rects);
        tester(circs);
        // tester(Arrays.asList(new Rect()));
        
  }
}

abstract class Shape{
    abstract void draw();
}
class Rect extends Shape{
  void draw(){
       System.out.println("Rect drawing");
  }  
}

class Circle extends Shape{
  void draw(){
       System.out.println("Circle drawing");
  }  
}