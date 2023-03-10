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

public class HomeController implements Initializable
{
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

    @FXML private AnchorPane OpeningNowPane;
    @FXML private ImageView comingSoonMovieImg1;
    @FXML private ImageView comingSoonMovieImg2;
    @FXML private Text comingSoonMovieText1;
    @FXML private Text comingSoonMovieText2;
    @FXML private Text comingSoonMovieDate1;
    @FXML private Text comingSoonMovieDate2;

    private JSONArray movieData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        JSONObject movieInfo = getJSONObject("movieData.json");
        movieData = (JSONArray)movieInfo.get("movieInfo");

        noMovieErrorMsg.setVisible(false);
        ComingSoonPane.setVisible(false);

        for(int i = 0; i < movieData.size(); i++)
        {
            Image image = null;
            try
            {
                String moviePhotoSource = (((JSONObject)movieData.get(i)).get("moviePhoto")).toString();
                image = new Image(new FileInputStream(moviePhotoSource));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            switch (i)
            {
                case 0 -> popularMovieImage.setImage(image);
                case 1 -> movieImage1.setImage(image);
                case 2 -> movieImage2.setImage(image);
                case 3 -> movieImage3.setImage(image);
                case 4 -> movieImage4.setImage(image);
                case 5 -> movieImage5.setImage(image);
                case 6 -> movieImage6.setImage(image);
                case 7 -> movieImage7.setImage(image);
                case 8 -> movieImage8.setImage(image);
                case 9 -> movieImage9.setImage(image);
                case 10 -> movieImage10.setImage(image);
            }
        }
    }

    public void searchFunction (MouseEvent event) throws IOException
    {
        for(int i = 0; i < movieData.size(); i++){
            //Get the input and its lower case input from user
            String userInputSearch = searchTextField.getText();
            String userInputSearchLower = userInputSearch.toLowerCase();
            //search the array to get the movie name
            String movieName = (((JSONObject)movieData.get(i)).get("movieName")).toString();
            String movieNameLower = movieName.toLowerCase();

            //if the user input movie name is same as the movie name in array
            //Change scene to display the movie info
            if(userInputSearch.equals(movieName) || userInputSearchLower.equals(movieNameLower)){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
                Stage stage = (Stage) searchButton.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
                MovieController controller = fxmlLoader.getController();
                controller.initialize(i);
                stage.show();
                break;
            }

            //if the input of movie name is not found
            //display error message
            else {
                noMovieErrorMsg.setVisible(true);
                FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), noMovieErrorMsg);
                fadeMessage.setFromValue(1);
                fadeMessage.setToValue(0);
                fadeMessage.play();
            }
        }
    }

    private static JSONObject getJSONObject(String fileName)
    {
        try
        {
            FileReader reader = new FileReader("src/main/resources/com/example/cat201_project/JSON_file/" + fileName);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void ComingSoonButton(ActionEvent event) {
        ComingSoonPane.setVisible(true);
        OpeningNowPane.setVisible(false);


        for(int i = 14; i < movieData.size(); i++)
        {
            Image image = null;
            try
            {
                String moviePhotoSource = (((JSONObject)movieData.get(i)).get("moviePhoto")).toString();
                image = new Image(new FileInputStream(moviePhotoSource));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            switch (i)
            {
                case 14 -> comingSoonMovieImg1.setImage(image);
                case 15 -> comingSoonMovieImg2.setImage(image);
            }
        }

        for(int i = 14; i < movieData.size(); i++){
            String movieTittle = (((JSONObject)movieData.get(i)).get("movieName")).toString();
            String movieReleaseDate = (((JSONObject)movieData.get(i)).get("movieDescription")).toString();

            switch (i)
            {
                case 14 -> {
                    comingSoonMovieText1.setText(movieTittle);
                    comingSoonMovieDate1.setText(movieReleaseDate);
                }
                case 15 -> {
                    comingSoonMovieText2.setText(movieTittle);
                    comingSoonMovieDate2.setText(movieReleaseDate);
                }
            }
        }




    }

    public void nowShowingButton(ActionEvent event) {
        ComingSoonPane.setVisible(false);
        OpeningNowPane.setVisible(true);
    }

    public void ChangetoMovieInfoScene0(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieInfoButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(0);
        stage.show();
    }

    public void ChangetoMovieInfoScene1(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton1.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(1);
        stage.show();
    }

    public void ChangetoMovieInfoScene2(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton2.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(2);
        stage.show();
    }

    public void ChangetoMovieInfoScene3(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton3.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(3);
        stage.show();
    }

    public void ChangetoMovieInfoScene4(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton4.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(4);
        stage.show();
    }

    public void ChangetoMovieInfoScene5(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton5.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(5);
        stage.show();
    }

    public void ChangetoMovieInfoScene6(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton6.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(6);
        stage.show();
    }

    public void ChangetoMovieInfoScene7(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton7.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(7);
        stage.show();
    }

    public void ChangetoMovieInfoScene8(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton8.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(8);
        stage.show();
    }

    public void ChangetoMovieInfoScene9(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton9.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(9);
        stage.show();
    }

    public void ChangetoMovieInfoScene10(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie.fxml"));
        Stage stage = (Stage) movieButton10.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieController controller = fxmlLoader.getController();
        controller.initialize(10);
        stage.show();
    }

    public void bookNowButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("purchaseTicket.fxml"));
        Stage stage = (Stage) bookNowButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        PurchaseTicketController controller = fxmlLoader.getController();
        controller.initialize(0);
        stage.show();
    }

    public void bookedTicketButtonClicked(ActionEvent event) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketHistory.fxml"));
        Stage stage = (Stage) bookedTicketButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();

    }

    public void logOutButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 957, 720));
        stage.show();
    }

    public void profileButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }



}
