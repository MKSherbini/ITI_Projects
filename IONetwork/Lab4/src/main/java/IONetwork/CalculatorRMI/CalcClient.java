package IONetwork.CalculatorRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalcClient {
    public static void main(String[] args) {
        if (!(args.length == 3 && args[0].matches("\\d")
                && args[1].matches("[+-/*]")
                && args[2].matches("\\d"))) {
            System.out.println("Wrong equation format");
            return;
        }

        int a = Integer.parseInt(args[0]);
        char op = args[1].charAt(0);
        int b = Integer.parseInt(args[2]);
        try {
            Registry reg = LocateRegistry.getRegistry(1111);
            CalcInt calc = (CalcInt) reg.lookup("CalcInt");

            switch (op) {
                case '+':
                    System.out.println("calc.Add(a, b) = " + calc.Add(a, b));
                    break;
                case '-':
                    System.out.println("calc.Subtract(a,b) = " + calc.Subtract(a, b));
                    break;
                case '*':
                    System.out.println("calc.Multiply(a,b) = " + calc.Multiply(a, b));
                    break;
                case '/':
                    if (b != 0)
                        System.out.println("calc.Divide(a,b) = " + calc.Divide(a, b));
                    else
                        System.out.println("Error div by zero");
                    break;
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
