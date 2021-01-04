import java.util.StringTokenizer;

public class StrCnt {
    public static void main(String[] args) {
        if(args.length != 2)return;
        int cnt = 0, idx = 0;
        while(idx<args[0].length()){
            idx = args[0].indexOf(args[1],idx);
            if(idx==-1){break;}
            else {cnt++;idx+=args[1].length();}
            // System.out.println(idx);
            // System.out.println(cnt);
            // System.out.println(args[0]);
            // System.out.println(args[1]);
            // System.out.println("xxxxx");
        }
        System.out.println("Method1 cnt: "+cnt);
        
        cnt=0;
        for(int i=0;i<args[0].length()-args[1].length()+1;i++){
            if(args[0].substring(i,i+args[1].length()).equals(args[1])){
                cnt++;
            }
        }
        System.out.println("Method2 cnt: "+cnt); 

        
         
        var mod = args[0].replaceAll(args[1],"\f");
        var token = new StringTokenizer("//"+mod+"//","\f");
        System.out.println( "Method3 cnt: "+(token.countTokens()-1) );
        
        // System.out.println("Elements");
        // while(token.hasMoreElements()){
            // System.out.println(token.nextElement());
        // }
        
        // System.out.println("Tokens");
        // while(token.hasMoreTokens()){
            // System.out.println(token.nextToken(args[1]));
        // }
    }
}