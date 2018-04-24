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
 * Created by matthew on 2/14/18.
 */

public class QuestionList {
       private static QuestionList sOurInstance;
       private static final String TAG = "QuestionList";

    private Context mAppContext;
    private SQLiteDatabase mDataBase;

    /*
    public File getPhotoFile(Bug bug) {
        File externalFilesDir = mAppContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, bug.getPhotoFilename());
    } */

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


    public void addQuestion(Question que) {
        ContentValues values = getContentValues(que);
        mDataBase.insert(QuestionTable.NAME, null, values);
    }

    public void updateQuestion(Question que) {
        String uuidString = que.getId().toString();
        ContentValues values = getContentValues(que);

        mDataBase.update(QuestionTable.NAME, values, QuestionTable.Cols.UUID + " = ? ",
                new String[] { uuidString});
    }

    public void deleteQuestion(Question que) {
        String uuidString = que.getId().toString();
        mDataBase.delete(QuestionTable.NAME,
                QuestionTable.Cols.UUID + " = ? ",
                new String[] { uuidString});
    }

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

    public static ContentValues getContentValues(Question que) {
        ContentValues values = new ContentValues();
        values.put(QuestionTable.Cols.UUID, que.getId().toString());
        values.put(QuestionTable.Cols.QUESTION, que.getQuestion());
        values.put(QuestionTable.Cols.ANSWER, que.getAnswer());

        return values;
    }

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
