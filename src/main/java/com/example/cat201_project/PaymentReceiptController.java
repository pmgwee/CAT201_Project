package com.example.cat201_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

import java.io.*;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.URL;
import java.util.ResourceBundle;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class PaymentReceiptController implements Initializable {


    @FXML
    private Button home;


    @FXML
    private Text Ticket;
    @FXML
    private Text Total;
    @FXML
    private Text Movie;
    @FXML
    private Text Date;
    @FXML
    private Text Time;
    @FXML
    private Text Seats;

    @FXML
    private ImageView MoviePoster;



    private JSONArray orderData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        JSONObject orderInfo = JsonClass.getJSONObject("paymentData.json");
        orderData = (JSONArray) orderInfo.get("orderInfo");

        String T = (((JSONObject)orderData.get(orderData.size() - 1)).get("Ticket")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        //Initialise movie details from JSON file
        Ticket.setText(T);
        Total.setText(TOT);

        //Initialise movie poster using path from JSON file
        Image image;
        try
        {
            String moviePosterSource = (((JSONObject) orderData.get(orderData.size() - 1)).get("Poster")).toString();
            image = new Image(new FileInputStream(moviePosterSource));
            MoviePoster.setImage(image);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //Get order details from JSON file to be embedded on receipt
        String movie = (((JSONObject)orderData.get(orderData.size() - 1)).get("Movie")).toString();
        String date = (((JSONObject)orderData.get(orderData.size() - 1)).get("Date")).toString();
        String time = (((JSONObject)orderData.get(orderData.size() - 1)).get("Time")).toString();
        String seats = "";
        int numTicket = Integer.parseInt(PurchaseTicketController.OrderedTicket);
        for(int i = 0; i < numTicket; i++){
            seats = seats + PurchaseTicketController.OrderedSeats[i] + " ";
        }
        Movie.setText(movie);
        Date.setText(date);
        Time.setText(time);
        Seats.setText(seats);


    }


    public void handleHomeBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }



}
