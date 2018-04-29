package edu.andrews.cptr252.rmatthew.quizgame;

import android.support.v4.app.Fragment;

/**
 * Hosts a fragment.
 */
public class QuestionListActivity extends SingleFragmentActivity{

    /**
     * Creates and returns fragment.
     * @return QuestionListFragment
     */
    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}

