package edu.andrews.cptr252.rmatthew.quizgame.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edu.andrews.cptr252.rmatthew.quizgame.Question;
import edu.andrews.cptr252.rmatthew.quizgame.database.QuestionDbSchema.QuestionTable;

/**
 * Created by matthew on 3/28/18.
 */

public class QuestionCursorWrapper extends CursorWrapper {
    public QuestionCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Question getQuestion() {
        String uuidString = getString(getColumnIndex(QuestionTable.Cols.UUID));
        String question = getString(getColumnIndex(QuestionTable.Cols.QUESTION));
        int answer = getInt(getColumnIndex(QuestionTable.Cols.ANSWER));

        Question que = new Question(UUID.fromString(uuidString));
        que.setQuestion(question);
        que.setAnswer(answer != 0);
        return que;
    }
}
