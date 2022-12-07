package com.example.appstudyenglish.model;

public class AnswerListen {
    private String answerListen;
    private  boolean isCorrectListen;

    public AnswerListen(String answerListen, boolean isCorrectListen) {
        this.answerListen = answerListen;
        this.isCorrectListen = isCorrectListen;
    }

    public AnswerListen() {
    }

    public String getAnswerListen() {
        return answerListen;
    }

    public void setAnswerListen(String answerListen) {
        this.answerListen = answerListen;
    }

    public boolean isCorrectListen() {
        return isCorrectListen;
    }

    public void setCorrectListen(boolean correctListen) {
        isCorrectListen = correctListen;
    }
}
