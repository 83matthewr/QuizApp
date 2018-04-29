package edu.andrews.cptr252.rmatthew.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Hosts fragment.
 */
public class ResultsActivity extends SingleFragmentActivity {

    /**
     * creates and returns ResultsFragment
     * @return ResultsFragment
     */
    @Override
    protected Fragment createFragment() {
        return new ResultsFragment();
    }
}
