package com.example.cat201_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import java.io.*;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 957, 720);
        scene.getStylesheets().add(getClass().getResource("css/login.css").toExternalForm());
        stage.setTitle("HeongMadihubci CINEMA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException {

        launch();
    }
}
