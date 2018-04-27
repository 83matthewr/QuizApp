package edu.andrews.cptr252.rmatthew.quizgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    private ArrayList<Question> mQuestions;
    private int mScore, mTotal;
    private String mResults;

    private TextView mQuestionView;
    private TextView mScoreView;
    private Button mTrueButton;
    private Button mFalseButton;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();
        mTotal = mQuestions.size();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_mode, container, false);

        mQuestionView = v.findViewById(R.id.questionGameView);
        mQuestionView.setText(mQuestions.get(1).getQuestion());

        mTrueButton = v.findViewById(R.id.trueGameButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();

                ResultsDialogFragment dialog = new ResultsDialogFragment();
                dialog.show(fm, "true");
            }
        });

        mFalseButton = v.findViewById(R.id.falseGameButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();

                ResultsDialogFragment dialog = new ResultsDialogFragment.newInstance(R.string.action_settings);
                dialog.show(fm, "false");
            }
        });

        return v;
    }

}
