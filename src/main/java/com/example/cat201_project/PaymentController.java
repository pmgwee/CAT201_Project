package com.example.cat201_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PaymentController implements  Initializable{

    @FXML
    private Button back;
    @FXML
    private Button pay;
    @FXML
    private Button cancel;
    @FXML
    private Button BookedTicket;
    @FXML
    private Button Profile;
    @FXML
    private Button logout;


    @FXML
    private TextField CardNumber;
    @FXML
    private TextField ExpiryMonth;
    @FXML
    private TextField ExpiryYear;
    @FXML
    private TextField CVV;

    @FXML
    private Text CardNumErrMessage;
    @FXML
    private Text CardMonthErrMessage;
    @FXML
    private Text CardYearErrMessage;
    @FXML
    private Text CVVErrMessage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        //Disable all error messages
        CardNumErrMessage.setVisible(false);
        CardMonthErrMessage.setVisible(false);
        CardYearErrMessage.setVisible(false);
        CVVErrMessage.setVisible(false);

//        String T = PurchaseTicketController.OrderedTicket;
//        String TOT = PurchaseTicketController.OrderedTotal;
//
//        //Initialise movie details
//        Ticket.setText(T);
//        Total.setText(TOT);
//
//        //Initialise movie poster
//        Image image;
//        try
//        {
//            String moviePosterSource = PurchaseTicketController.OrderedPoster;
//            image = new Image(new FileInputStream(moviePosterSource));
//            MoviePoster.setImage(image);
//
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
    }

    //Change back to Select Payment scene when user click Back button
    public void handleBackBttn() throws IOException, RuntimeException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleBookedTicketBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketHistory.fxml"));
        Stage stage = (Stage) BookedTicket.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleProfileBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
        Stage stage = (Stage) Profile.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleLogoutBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleCancelBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment.fxml"));
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void changeToReceiptScene() throws IOException, ParseException {
        String CN = CardNumber.getText();
        String EM = ExpiryMonth.getText();
        String EY = ExpiryYear.getText();
        String CV = CVV.getText();

        long cardNum = -1;
        int month = -1;
        int year = -1;
        int cvv = -1;

        //Parse the texts from text fields into numbers if text fields are not empty
        if(!CN.isEmpty()){
            cardNum = Long.parseLong(CN);
        }
        if(!EM.isEmpty()){
            month = Integer.parseInt(EM);
        }
        if(!EY.isEmpty()){
            year = Integer.parseInt(EY);
        }
        if(!CV.isEmpty()){
            cvv = Integer.parseInt(CV);
        }

        //Show error messages whenever user enters an invalid number or leave text field empty
        if(cardNum < 0){
            CardNumErrMessage.setVisible(true);
            CardMonthErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if (month < 1 || month > 12){
            CardMonthErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if(year < 2022){
            CardYearErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardMonthErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if(cvv < 0 || cvv > 999){
            CVVErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardMonthErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
        }
        else{ //Change to Receipt scene when user clicks Pay button
            updateMovieFile();
            System.out.println("Sucessfully updated Movie File");
            updateOrderInfoFile();
            System.out.println("Sucessfully updated Order Info File");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentReceipt.fxml"));
            Stage stage = (Stage) pay.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
    }

    public void updateMovieFile(){
        JSONParser parser = new JSONParser();
        JSONObject movieInfo = null;
        File inputFile = new File("src/main/resources/com/example/cat201_project/JSON_file/movieTimeSeat"+PurchaseTicketController.OrderedMovieCode+".json");

        int numShow = 0;
        JSONObject movInf = JsonClass.getJSONObject("movieTimeSeat"+PurchaseTicketController.OrderedMovieCode+".json");
        JSONArray movData = (JSONArray) movInf.get("movieTimeSeat"+(PurchaseTicketController.OrderedMovieCode));

        for(int i = 0; i < movData.size(); i++){
            if((((((JSONObject)movData.get(i)).get("movieTimeSlot")).toString()).equals(PurchaseTicketController.OrderedTime))){
                numShow = i;
            }
        }

        try{
            movieInfo = (JSONObject) parser.parse(new FileReader(inputFile));
            JSONObject obj = (JSONObject)((JSONArray)movieInfo.get("movieTimeSeat"+(PurchaseTicketController.OrderedMovieCode))).get(numShow);
            for(int i = 1; i <= 8; i++){//each row has 8 seats
                for(int j = 0; j < PurchaseTicketController.OrderedSeats.length; j++){
                    if(("A0"+i).equals(PurchaseTicketController.OrderedSeats[j])){
                        String movieseat = "movieSeatA0"+i;
                        obj.put(movieseat, false);
                    }
                }
            }
            for(int i = 1; i <= 8; i++){//each row has 8 seats
                for(int j = 0; j < PurchaseTicketController.OrderedSeats.length; j++){
                    if(("B0"+i).equals(PurchaseTicketController.OrderedSeats[j])){
                        String movieseat = "movieSeatB0"+i;
                        obj.put(movieseat, false);
                    }
                }
            }
            for(int i = 1; i <= 8; i++){//each row has 8 seats
                for(int j = 0; j < PurchaseTicketController.OrderedSeats.length; j++){
                    if(("C0"+i).equals(PurchaseTicketController.OrderedSeats[j])){
                        String movieseat = "movieSeatC0"+i;
                        obj.put(movieseat, false);
                    }
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/com/example/cat201_project/JSON_file/movieTimeSeat"+PurchaseTicketController.OrderedMovieCode+".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(movieInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderInfoFile() throws IOException, ParseException {
        JSONObject newOrderInfo = new JSONObject();
        String userID = (String) JsonClass.getCurrentUserInfo().get("userID");
        newOrderInfo.put("UserID",userID);
        newOrderInfo.put("Ticket",PurchaseTicketController.OrderedTicket);
        newOrderInfo.put("Movie",PurchaseTicketController.OrderedMovie);
        newOrderInfo.put("QR",null);
        newOrderInfo.put("Total",PurchaseTicketController.OrderedTotal);
        newOrderInfo.put("Poster",PurchaseTicketController.OrderedPoster);
        newOrderInfo.put("Time",PurchaseTicketController.OrderedTime);
        newOrderInfo.put("Date",PurchaseTicketController.OrderedDate);

        String seats = "";
        for(int i = 0; i < (PurchaseTicketController.OrderedSeats.length - 1); i++){
            seats = seats + PurchaseTicketController.OrderedSeats[i] + "  ";
        }
        seats = seats + PurchaseTicketController.OrderedSeats[PurchaseTicketController.OrderedSeats.length - 1];
        newOrderInfo.put("Seats", seats);

        File inputFile = new File("src/main/resources/com/example/cat201_project/JSON_file/paymentData.json");

        JSONParser parser = new JSONParser();
        JSONObject orderInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray orderInfoArray = (JSONArray) orderInfo.get("orderInfo");
        orderInfoArray.add(newOrderInfo);

        FileWriter writer = new FileWriter("src/main/resources/com/example/cat201_project/JSON_file/paymentData.json");
        writer.write(orderInfo.toString());
        writer.close();
    }
}
