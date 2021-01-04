
public class ExceptThrow {
    public static void main(String[] args) {
        try{
            ExceptGen.bThrow(true);
            ExceptGen.bThrow2(true);
            ExceptGen.bThrow3(true);
        }catch(Exceptor e){
            System.out.println("Exceptor throwing");
        }final{
            System.out.println("Final here");
        }
    }
}


class ExceptGen{
    public static void bThrow(boolean b) throws Exceptor{
        if(b) throw new Exceptor();
    }
    public static void bThrow2(boolean b) throws Exceptor{
        if(b) throw new Exceptor();
    }
    public static void bThrow3(boolean b) throws Exceptor{
        if(b) throw new Exceptor();
    }
}


class Exceptor extends Exception{
    
}
