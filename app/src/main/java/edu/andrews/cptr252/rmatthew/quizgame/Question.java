package edu.andrews.cptr252.rmatthew.quizgame;

import java.util.UUID;

/**
 * Created by matthew on 4/18/18.
 */

public class Question {
    private String mQuestion;
    private boolean mAnswer;
    private UUID mId;

    public Question () {
        this.mId = UUID.randomUUID();
    }

    public Question (UUID id) {
        mId = id;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public UUID getId () {
        return mId;
    }

    public void setId (UUID id) {
        mId = id;
    }

    public String returnAnswerString() {
        if(this.getAnswer()) {
            return("True");
        }
        if (!this.getAnswer()) {
            return("False");
        }
        else
            return("fail");
    }
}
