package com.example.appstudyenglish.model;

import java.util.List;

public class QuestionListening {
    private int numberListen;
    private String questionListen;
    private List<AnswerListen> answerListenList;

    public QuestionListening(int numberListen, String questionListen, List<AnswerListen> answerListenList) {
        this.numberListen = numberListen;
        this.questionListen = questionListen;
        this.answerListenList = answerListenList;
    }

    public QuestionListening() {
    }

    public int getNumberListen() {
        return numberListen;
    }

    public void setNumberListen(int numberListen) {
        this.numberListen = numberListen;
    }

    public String getQuestionListen() {
        return questionListen;
    }

    public void setQuestionListen(String questionListen) {
        this.questionListen = questionListen;
    }

    public List<AnswerListen> getAnswerListenList() {
        return answerListenList;
    }

    public void setAnswerListenList(List<AnswerListen> answerListenList) {
        this.answerListenList = answerListenList;
    }
}
