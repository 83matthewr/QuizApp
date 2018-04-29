package edu.andrews.cptr252.rmatthew.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.UUID;

/**
 * A fragment that creates or edits a question.
 */
public class QuestionEditorFragment extends Fragment {
    public static final String TAG = "QuestionEditorFragment";
    public static final String EXTRA_QUESTION_ID = "edu.andrews.cptr252.rlsummerscales.quizgame.que_id";
    private static final String DIALOG_RESULTS = "results";
    private Question mQuestion;
    private EditText mQuestionField;
    private TextView mQuestionView;
    private TextView mAnswerView;
    private CheckBox mTrueBox;
    private CheckBox mFalseBox;

    public QuestionEditorFragment() {
    }

    /** Creates fragment and takes arguments from the intent extras.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID queId = (UUID)getArguments().getSerializable(EXTRA_QUESTION_ID);
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(queId);
    }

    /** returns a new instance of the fragment with the question id */
    public static QuestionEditorFragment newInstance(UUID queId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION_ID, queId);
        QuestionEditorFragment fragment = new QuestionEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /** Updates question on pause. */
    @Override
    public void onPause() {
        super.onPause();
        QuestionList.getInstance(getActivity()).updateQuestion(mQuestion);
    }

    /**
     * Creates view for fragment and inflates widgets and controls listeners to implement
     * the fragment interface. Returns fragment view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_question_editor, container, false);

       mQuestionField = (EditText) v.findViewById(R.id.question_editor);
       mQuestionField.setText(mQuestion.getQuestion());
       mQuestionField.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               mQuestion.setQuestion(s.toString());
               Log.d(TAG, "Question changed to " + mQuestion.getQuestion());
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

        mTrueBox = v.findViewById(R.id.trueBox);
        mTrueBox.setChecked(mQuestion.getAnswer() == true);
        mTrueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mTrueBox.isChecked()) {
                    mFalseBox.setChecked(false);
                    mQuestion.setAnswer(true);
                    Log.d(TAG, "Set answer to true");
                }
            }
        });

        mFalseBox = v.findViewById(R.id.falseBox);
        mFalseBox.setChecked(mQuestion.getAnswer() == false);
        mFalseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mFalseBox.isChecked()) {
                    mTrueBox.setChecked(false);
                    mQuestion.setAnswer(false);
                    Log.d(TAG, "Set answer to false");
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }
}














