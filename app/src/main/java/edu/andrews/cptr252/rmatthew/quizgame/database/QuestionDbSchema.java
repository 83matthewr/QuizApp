package edu.andrews.cptr252.rmatthew.quizgame.database;

/**
 * Created by matthew on 3/28/18.
 */

public class QuestionDbSchema {
    public static final class QuestionTable {
        public static final String NAME = "ques";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String QUESTION = "question";
            public static final String ANSWER = "answer";
        }
    }
}
