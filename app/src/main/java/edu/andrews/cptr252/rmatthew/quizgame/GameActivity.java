package edu.andrews.cptr252.rmatthew.quizgame;

import android.support.v4.app.Fragment;

/**
 * Hosts fragment.
 */
public class GameActivity extends SingleFragmentActivity {

    /**
     * Creates and returns GameFragment
     * @return GameFragment
     */
    @Override
    protected Fragment createFragment() {
        return new GameFragment();
    }
}
