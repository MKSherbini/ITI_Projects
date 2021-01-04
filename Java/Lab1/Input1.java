
public class Input1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No args");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
    }
}