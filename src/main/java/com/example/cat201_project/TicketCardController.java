package com.example.cat201_project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.mail.MessagingException;
import java.io.IOException;

public class TicketCardController {

    @FXML private Label date;
    @FXML private Label movieName;
    @FXML private Label seat;
    @FXML private Label time;

    public void setData(TicketHistory tickets){
        date.setText(tickets.getDate());
        movieName.setText(tickets.getMovieName());
        seat.setText(tickets.getSeat());
        time.setText(tickets.getTime());
    }

}
