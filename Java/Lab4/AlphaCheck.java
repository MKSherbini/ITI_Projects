import java.util.function.*;

public class AlphaCheck {
    static boolean stringAlphaCheck(String s, Predicate<Character> t){
        boolean b=true;
        for(int i=0;i<s.length();i++) b&=t.test(s.charAt(i));
        return b;
    }
    public static void main(String[] args) {
        if(args.length !=1) return;
        String s=args[0];
        // stringAlphaCheck(s, Character::isLetter);
        System.out.println("Str: "+s+" has onlyLetters? is "+stringAlphaCheck(s, Character::isLetter));
    }
}