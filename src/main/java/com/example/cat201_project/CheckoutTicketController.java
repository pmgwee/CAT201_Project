package com.example.cat201_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutTicketController implements Initializable {

    @FXML
    private Button ProfileButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button payButton;
    @FXML
    private Button backButton;
    @FXML
    private Button BookedTicket;

    @FXML
    private Text movieTitle;
    @FXML
    private Text movieDate;
    @FXML
    private Text movieTime;

    @FXML
    private Text movieTicket;
    @FXML
    private Text movieSeats;
    @FXML
    private Text movieTotal;

    @FXML
    private ImageView MoviePoster;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        String movie = PurchaseTicketController.OrderedMovie;
        String date = PurchaseTicketController.OrderedDate;
        String time = PurchaseTicketController.OrderedTime;
        String seats = "";
        String T =  PurchaseTicketController.OrderedTicket;
        String Tot = PurchaseTicketController.OrderedTotal;
        int numTicket = Integer.parseInt(PurchaseTicketController.OrderedTicket);
        for(int i = 0; i < numTicket; i++){
            seats = seats + PurchaseTicketController.OrderedSeats[i] + " ";
        }

        //Initialise movie details
        movieTitle.setText(movie);
        movieDate.setText(date);
        movieTime.setText(time);
        movieSeats.setText(seats);
        movieTotal.setText(Tot);
        movieTicket.setText(T);

        //Initialise movie poster
        Image image;
        try
        {
            String moviePosterSource = PurchaseTicketController.OrderedPoster;
            image = new Image(new FileInputStream(moviePosterSource));
            MoviePoster.setImage(image);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void handleProfileBttn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
        Stage stage = (Stage) ProfileButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleLogoutBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 957, 720));
        stage.show();
    }



    public void handleBackBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 957, 720));
        stage.show();
    }

    public void handleBookedTicketBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketHistory.fxml"));
        Stage stage = (Stage) BookedTicket.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handlePayBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment.fxml"));
        Stage stage = (Stage) payButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }


}
