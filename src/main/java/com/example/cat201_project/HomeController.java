package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HomeController {
    @FXML private Button searchButton;
    @FXML private TextField searchTextField;
    @FXML private Text noMovieErrorMsg;

    @FXML private Button bookedTicketButton;
    @FXML private Button profileButton;
    @FXML private Button logOutButton;

    @FXML private ImageView popularMovieImage;
    @FXML private Button bookNowButton;
    @FXML private Button movieInfoButton;

    @FXML private Button comingSoonButton;
    @FXML private Button nowShowingButton;
    @FXML private AnchorPane scrollPaneForMovie;

    @FXML private ImageView movieImage1;
    @FXML private ImageView movieImage2;
    @FXML private ImageView movieImage3;
    @FXML private ImageView movieImage4;
    @FXML private ImageView movieImage5;
    @FXML private ImageView movieImage6;
    @FXML private ImageView movieImage7;
    @FXML private ImageView movieImage8;
    @FXML private ImageView movieImage9;
    @FXML private ImageView movieImage10;

    @FXML private Button movieButton1;
    @FXML private Button movieButton2;
    @FXML private Button movieButton3;
    @FXML private Button movieButton4;
    @FXML private Button movieButton5;
    @FXML private Button movieButton6;
    @FXML private Button movieButton7;
    @FXML private Button movieButton8;
    @FXML private Button movieButton9;
    @FXML private Button movieButton10;

    @FXML private AnchorPane ComingSoonPane;
    @FXML private ImageView comingSoonMovieImg1;
    @FXML private ImageView comingSoonMovieImg2;
    @FXML private Text comingSoonMovieText1;
    @FXML private Text comingSoonMovieText2;
    @FXML private Text comingSoonMovieDate1;
    @FXML private Text comingSoonMovieDate2;
}
