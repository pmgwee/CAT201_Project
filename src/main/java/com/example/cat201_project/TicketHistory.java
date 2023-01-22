package com.example.cat201_project;
public class TicketHistory {
    private String movieName;
    private String date;
    private String time;
    private String seat;

    public void printData(){
        System.out.println(movieName);
        System.out.println(date);
        System.out.println(time);
        System.out.println(seat);

    }


    public String getMovieName() {
        return movieName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSeat() {
        return seat;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

}
