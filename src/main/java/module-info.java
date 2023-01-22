module com.example.cat201_project {
    requires javafx.controls;
    requires core;
    requires javax.mail.api;
    requires qrgen;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires commons.io;
    requires json.simple;
    requires activation;


    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}