package edu.andrews.cptr252.rmatthew.quizgame;

import android.support.v4.app.Fragment;

/**
 * Created by matthew on 2/14/18.
 */

public class QuestionListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}

