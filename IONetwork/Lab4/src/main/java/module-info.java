module IONetwork {
    requires javafx.controls;
    requires java.rmi;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;

    exports IONetwork;
    exports IONetwork.HelloRMI;
    exports IONetwork.CalculatorRMI;
    exports IONetwork.DBRMI;
}