package edu.andrews.cptr252.rmatthew.quizgame;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    public static final String EXTRA_RESULTS =  "edu.andrews.cptr252.rmatthew.quizgame.results";

    private TextView mQuestionView;
    private TextView mScoreView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();
        mTotal = mQuestions.size();
    }

    private int mCurrentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_mode, container, false);

        mQuestionView = v.findViewById(R.id.questionGameView);
        mQuestionView.setText(mQuestions.get(mCurrentIndex).getQuestion());

        mScoreView = v.findViewById(R.id.scoreView);
        mScoreView.setText("0/" + mTotal);

        mTrueButton = v.findViewById(R.id.trueGameButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean b = isCorrect(true);
                if(b) {
                    if(mScore < mTotal)
                        mScore++;
                    showDialog("That's Correct!");
                }
                else {
                    showDialog("That's Incorrect!");
                }
                mCurrentIndex++;
                updateQuestion();
                updateScore();
            }
        });

        mFalseButton = v.findViewById(R.id.falseGameButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean b = isCorrect(false);
                if(b) {
                    if(mScore < mTotal)
                        mScore++;
                    showDialog("That's Correct!");
                }
                else {
                    showDialog("That's Incorrect!");
                }
                mCurrentIndex++;
                updateQuestion();
                updateScore();
            }
        });

        mNextButton = v.findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentIndex++;
                updateQuestion();
            }
        });

        mPreviousButton = v.findViewById(R.id.previousButton);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentIndex--;
                updateQuestion();
            }
        });

        return v;
    }

    /** Update game question */
    private void updateQuestion() {
        if(mCurrentIndex >= mTotal) {
            displayResultsActivity();
        }
        else {
            mQuestionView.setText(mQuestions.get(mCurrentIndex).getQuestion());
        }
    }

    private void updateScore() {
        mResults = (mScore + "/" + mTotal);
        mScoreView.setText(mResults);
    }

    private boolean isCorrect(boolean b) {
        if(mQuestions.get(mCurrentIndex).getAnswer() == b){
            return true;
        }
        else {
            return false;
        }
    }

    private void displayResultsActivity () {
        Intent i = new Intent(getActivity(), ResultsActivity.class);
        String results = ("You scored " + mScore + " out of " + mTotal + "!");
        i.putExtra(EXTRA_RESULTS, results);
        startActivity(i);
    }

    private void showDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}





















