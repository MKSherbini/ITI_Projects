

public class Input2 {
    static int Stoi(String s) {
        int ret = 0;
        for (int i = s.length() - 1, mul = 1; i >= 0; i--, mul *= 10) {
			if((s.charAt(i) - '0') <=9 && (s.charAt(i) - '0') >= 0){
				ret += (s.charAt(i) - '0') * mul;
			}
		}
        return ret;
    }
    public static void main(String[] args) {
        if (args.length != 2)
            return;
        int n = Stoi(args[0]);
        for (int i = 0; i < n; i++) {
            System.out.println(args[1]);
        }
    }
}