package edu.andrews.cptr252.rmatthew.quizgame;

/**
 * Created by matthew on 4/28/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 */
public class ResultsFragment extends Fragment {
    private Button mOkayButton;
    private TextView mResultsView;

    /**
     * Empty public constructor.
     */
    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.results_view, container, false);
        String results = getActivity().getIntent().getStringExtra(GameFragment.EXTRA_RESULTS);

        mResultsView = v.findViewById(R.id.resultsView);
        mResultsView.setText(results);

        mOkayButton = (Button) v.findViewById(R.id.okayButton);
        mOkayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayMainActivity(); }

        });

        return v;
    }

    private void displayMainActivity () {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }
}
