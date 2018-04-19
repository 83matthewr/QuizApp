package edu.andrews.cptr252.rmatthew.quizgame;

/**
 * Created by matthew on 4/18/18.
 */

public class Question {
    private String mQuestion;
    private boolean mAnswer;

    public Question (String quest, boolean ans) {
        mQuestion = quest;
        mAnswer = ans;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        this.mAnswer = answer;
    }

    public String getQuestion() {

        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }
}
