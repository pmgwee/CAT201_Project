package com.example.cat201_project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketHistoryController implements Initializable {

    @FXML private Text backToMainPageText;
    @FXML private Button logOutBttn;
    @FXML private Button returnToMainPageBttn;
    @FXML private VBox ticketLayout;
    @FXML private Button viewBookedTicketBttn;
    @FXML private Button viewProfileBttn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TicketHistory> bookedTickets = null;
        try {
            bookedTickets = new ArrayList<>(getBookedTicketList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < bookedTickets.size() ;  i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ticketHistory.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                TicketCardController card = fxmlLoader.getController();
                card.setData(bookedTickets.get(i));
                ticketLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<TicketHistory> getBookedTicketList() throws IOException, ParseException {
        List<TicketHistory> list = new ArrayList<>();
        String movie,QR,time,date,seats;

        JSONObject ticketInfo = JsonClass.getJSONObject("paymentData.json");
        JSONArray array = (JSONArray) ticketInfo.get("orderInfo");
        JSONArray ticketArray = null;

        for(int i = 0 ; i < array.size();i++){

            // **********************************************************************
            JSONObject currentUser = JsonClass.getCurrentUserInfo();
            String userName = (String) currentUser.get("userID");
            JSONObject temp = (JSONObject) array.get(i);

            if(userName.equals(temp.get("UserID"))) {
                // **********************************************************************
                TicketHistory ticket = new TicketHistory();
                seats = "";
                movie = (String) temp.get("Movie");
                QR = (String) temp.get("QR");
                time = (String) temp.get("Time");
                date = (String) temp.get("Date");

                seats = (String) temp.get("Seats");

                ticket.setMovieName(movie);
                ticket.setTime(time);
                ticket.setDate(date);
                ticket.setSeat(seats);

                list.add(ticket);
            }
        }
        return list;
    }

    public void returnToViewTicketPage (ActionEvent e ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketHistory.fxml"));
        Stage stage = (Stage) viewBookedTicketBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToProfilePage (ActionEvent e ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
        Stage stage = (Stage) viewProfileBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToLogOutPage (ActionEvent e ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) logOutBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToHomeMovieScene (ActionEvent e ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Stage stage = (Stage) returnToMainPageBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }
}
