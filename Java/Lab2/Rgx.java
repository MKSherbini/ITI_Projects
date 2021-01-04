import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.StringTokenizer;

public class Rgx {
    public static void main(String[] args) {
        String test="This is a really long string. This is Java Java_Java Java99";
        System.out.println("Test str: "+test);
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(test);
        
        while(m.find()){
            var word = m.group(0);
            int cnt=0, idx = 0;
            while(idx<test.length()){
                idx = test.indexOf(word,idx);
                if(idx==-1){break;}
                else {cnt++;idx+=word.length();}
            }
            System.out.println("Word "+m.group(0)+" With Count: "+cnt);
            
            cnt=0;
            Matcher m2 = p.matcher(test);
            while(m2.find()){
                var word2 = m2.group(0);  
                if(word.equals(word2))cnt++;
            }
            System.out.println("Word "+m.group(0)+" With Unique Count: "+cnt);
        }
    }
}