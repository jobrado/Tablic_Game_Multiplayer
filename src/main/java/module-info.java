module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.naming;
    requires java.xml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controller to javafx.fxml;

    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.rmi to java.rmi;
}