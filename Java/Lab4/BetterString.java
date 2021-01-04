import java.util.function.*;
public class BetterString {
    
    static String betterString(String s1, String s2, BiPredicate<String, String> con) {
        if(con.test(s1, s2))
            return s1;
        else 
            return s2;
    }
    public static void main(String[] args) {
        String string1 = "aaaa";
        String string2 = "bbbbb";
        String longer = betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
        String first = betterString(string1, string2, (s1, s2) -> true);
        System.out.println("Str1: "+string1);
        System.out.println("Str2: "+string2);
        System.out.println("Longer: "+longer);
        System.out.println("First: "+first);
    }
    
}