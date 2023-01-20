package com.example.cat201_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.cat201_project.HomePage.class.getResource("signUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/signUp.css").toExternalForm());
        stage.setTitle("CINEMA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();


    }
}
