package com.example.appstudyenglish.model;

public class Message {
    private String title;
    private String time;

    public Message (){

    }

    public Message(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
