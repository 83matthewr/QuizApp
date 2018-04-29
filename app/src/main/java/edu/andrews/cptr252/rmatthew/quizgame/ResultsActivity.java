package edu.andrews.cptr252.rmatthew.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by matthew on 4/28/18.
 */

public class ResultsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ResultsFragment();
    }
}
