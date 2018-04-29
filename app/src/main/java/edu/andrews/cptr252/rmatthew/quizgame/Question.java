package edu.andrews.cptr252.rmatthew.quizgame;

import java.util.UUID;

/**
 * Model for quiz game. Question object stores a string question, a boolean answer,
 * and a UUID.
 */
public class Question {
    /** Holds question. */
    private String mQuestion;
    /** Holds answer to question. */
    private boolean mAnswer;
    /** Holds id for question. */
    private UUID mId;

    /**
     * Constructor with random UUID.
     */
    public Question () {
        this.mId = UUID.randomUUID();
    }

    /**
     * Constructor with specified UUID as param.
     * @param id
     */
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
