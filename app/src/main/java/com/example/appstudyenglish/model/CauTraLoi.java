package com.example.appstudyenglish.model;

public class CauTraLoi {
    private String title;
    private int answer;

    public CauTraLoi(){

    }

    public CauTraLoi(String title, int answer) {
        this.title = title;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
