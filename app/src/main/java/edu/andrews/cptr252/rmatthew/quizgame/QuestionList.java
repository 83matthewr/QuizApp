package edu.andrews.cptr252.rmatthew.quizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import edu.andrews.cptr252.rmatthew.quizgame.database.QuestionCursorWrapper;
import edu.andrews.cptr252.rmatthew.quizgame.database.QuestionDbHelper;
import edu.andrews.cptr252.rmatthew.quizgame.database.QuestionDbSchema.QuestionTable;

/**
 * Class to manage the list of questions for QuizGame. Creates, deletes, and stores
 * an ArrayList of Question objects into a DB.
 */
public class QuestionList {
       private static QuestionList sOurInstance;
       private static final String TAG = "QuestionList";

    private Context mAppContext;
    private SQLiteDatabase mDataBase;

    /**
     * Function to return the ArrayList of questions.
     * @return ques
     */
    public ArrayList<Question> getQuestions() {
        ArrayList<Question> ques = new ArrayList<>();
        QuestionCursorWrapper cursor = queryQues(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ques.add(cursor.getQuestion());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return ques;
    }

    /**
     * Function to add a new question to database.
     * @param que
     */
    public void addQuestion(Question que) {
        ContentValues values = getContentValues(que);
        mDataBase.insert(QuestionTable.NAME, null, values);
    }

    /**
     * Function to update a question after being edited by user.
     * @param que
     */
    public void updateQuestion(Question que) {
        String uuidString = que.getId().toString();
        ContentValues values = getContentValues(que);

        mDataBase.update(QuestionTable.NAME, values, QuestionTable.Cols.UUID + " = ? ",
                new String[] { uuidString});
    }

    /**
     * Function that removes a question from the database.
     * @param que
     */
    public void deleteQuestion(Question que) {
        String uuidString = que.getId().toString();
        mDataBase.delete(QuestionTable.NAME,
                QuestionTable.Cols.UUID + " = ? ",
                new String[] { uuidString});
    }

    /**
     * Function to find a question by UUID and return it.
     * @param id
     * @return question
     */
    public Question getQuestion(UUID id) {
        QuestionCursorWrapper cursor = queryQues(QuestionTable.Cols.UUID + " = ? ",
                new String[] { id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getQuestion();
        } finally {
            cursor.close();
        }
    }

    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null){
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }

    /**
     * Function to pack question info into a ContentValues object and return
     * the values object.
     * @param que
     * @return values
     */
    public static ContentValues getContentValues(Question que) {
        ContentValues values = new ContentValues();
        values.put(QuestionTable.Cols.UUID, que.getId().toString());
        values.put(QuestionTable.Cols.QUESTION, que.getQuestion());
        values.put(QuestionTable.Cols.ANSWER, que.getAnswer() ? 1 : 0);

        return values;
    }

    /**
     * Build a query for Question DB.
     * @param whereClause defines the where clause of a SQL query * @param whereArgs
     * defines where arguments for a SQL query * @return Object defining a SQL query
     */
    private QuestionCursorWrapper queryQues(String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(
                QuestionTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new QuestionCursorWrapper(cursor);
    }

    private QuestionList(Context appContext) {
        mAppContext = appContext.getApplicationContext();
        mDataBase = new QuestionDbHelper(mAppContext).getWritableDatabase();
    }
}
