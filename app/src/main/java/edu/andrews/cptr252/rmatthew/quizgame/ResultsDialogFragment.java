package edu.andrews.cptr252.rmatthew.quizgame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by matthew on 4/26/18.
 */

public class ResultsDialogFragment extends DialogFragment {
    public static final String EXTRA_STRING = "QuizGame.results";
    private String mResults;

    public static ResultsDialogFragment newInstance(String string) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_STRING, string);

        ResultsDialogFragment fragment = new ResultsDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mResults = (String)getArguments().getSerializable(EXTRA_STRING);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage(mResults)
                .setPositiveButton(R.string.okay_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
