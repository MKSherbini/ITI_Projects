import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.StringTokenizer;

public class Rgx {
    public static void main(String[] args) {
        // String test="This is a really long string. This is Java Java_Java Java99";
        String key="Jabla";
        System.out.println("Test str: "+key);
        Pattern p = Pattern.compile("^[jJ].*[aA]$");
        // Pattern p = Pattern.compile(key);
        // Matcher m = p.matcher(test);
        Matcher m = p.matcher(key);
        System.out.println(m.matches());
        // int cnt=0;
        // while(m.find()){
            // var word = m.group(0);
            // if(word.equals(key))
            // cnt++;
        // }
        // System.out.println("Word "+key+" With Unique Count: "+cnt);
    }
}