package edu.andrews.cptr252.rmatthew.quizgame;

import android.support.v4.app.Fragment;

/**
 * Created by matthew on 4/18/18.
 */

public class GameActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GameFragment();
    }
}
