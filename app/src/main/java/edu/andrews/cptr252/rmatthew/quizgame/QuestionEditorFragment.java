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
 * A placeholder fragment containing a simple view.
 */
public class QuestionEditorFragment extends Fragment {
    public static final String TAG = "QuestionEditorFragment";
    public static final String EXTRA_QUESTION_ID = "edu.andrews.cptr252.rlsummerscales.quizgame.que_id";
    private static final String DIALOG_RESULTS = "results";
    private Question mQuestion;
    private EditText mQuestionField;
    private TextView mQuestionView;
    private TextView mAnswerView;
    private Button mTrueButton;
    private Button mFalseButton;

    public QuestionEditorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID queId = (UUID)getArguments().getSerializable(EXTRA_QUESTION_ID);
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(queId);
    }

    public static QuestionEditorFragment newInstance(UUID queId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION_ID, queId);
        QuestionEditorFragment fragment = new QuestionEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        QuestionList.getInstance(getActivity()).updateQuestion(mQuestion);
    }

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


        mTrueButton = v.findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean b = true;
                mQuestion.setAnswer(b);
                Log.d(TAG, "Set answer to true");
            }
        });

        mFalseButton = v.findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean b = false;
                mQuestion.setAnswer(b);
                Log.d(TAG, "Set answer to false");
            }
        });
        return v;
    }
}














