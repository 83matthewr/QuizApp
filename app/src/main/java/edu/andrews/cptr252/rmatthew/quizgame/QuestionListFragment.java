package edu.andrews.cptr252.rmatthew.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Displays a list of questions to be selected, edited, created, and deleted.
 */
public class QuestionListFragment extends ListFragment {
    private QuestionAdapter mAdapter;
    private static final String TAG = "QuestionListFragment";
    private ArrayList<Question> mQuestions;

    /**
     * Updates the question list.
     */
    public void updateUI() {
        QuestionList questionList = QuestionList.getInstance(getActivity());
        ArrayList<Question> ques = questionList.getQuestions();

        if (mAdapter == null) {
            mAdapter = new QuestionAdapter(ques);
            setListAdapter(mAdapter);
        } else {
            mAdapter.setQuestions(ques);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Sets up the view of the fragment and sets up widgets and listeners for fragment.
     * Return view of fragment.
     * @param inflater
     * @param parent
     * @param savedInstanceState
     * @return v
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = v.findViewById(android.R.id.list);

        updateUI();

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.question_list_item_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_delete_question:
                        QuestionList questionList = QuestionList.getInstance(getActivity());

                        for (int i = mAdapter.getCount() - 1; i >= 0; i--) {
                            if (getListView().isItemChecked(i)) {
                                questionList.deleteQuestion(mAdapter.getItem(i));
                            }
                        }

                        actionMode.finish();
                        updateUI();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
        return v;
    }

    /**
     * Starts Question Editor to add a new question.
     */
    private void addQuestion() {
        Question que = new Question();
        QuestionList.getInstance(getActivity()).addQuestion(que);

        Intent i = new Intent(getActivity(), QuestionEditorActivity.class);
        i.putExtra(QuestionEditorFragment.EXTRA_QUESTION_ID, que.getId());
        startActivityForResult(i, 0);
    }

    /**
     * Creates an options menu.
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_question_list, menu);
    }

    /**
     * Allows user to select questions in list.
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_question:
                addQuestion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Class to get the view for a question.
     */
    private class QuestionAdapter extends ArrayAdapter<Question> {

        public QuestionAdapter(ArrayList<Question> ques) {
            super(getActivity(), 0, ques);
        }

        public void setQuestions(ArrayList<Question> ques) {
            clear();
            addAll(ques);
        }

        /**
         * Creates the view for each question in question list.
         * @param position
         * @param convertView
         * @param parent
         * @return convertView
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //if we weren't given a view inflate one
            if(null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_question, null);
            }

            Question question = getItem(position);

            TextView questionView = convertView.findViewById(R.id.questionView);
            questionView.setText(question.getQuestion());

            TextView answerView = convertView.findViewById(R.id.answerView);
            answerView.setText(question.returnAnswerString());

            return convertView;
        }
    } //end QuestionAdapter

    public QuestionListFragment() {
        // Required empty public constructor
    }

    /**
     * Sets up the fragment, gets questions, and creates adapter for the questions.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.question);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();
        setHasOptionsMenu(true);

        //Use custom BugAdapter for generating views for each bug
        mAdapter = new QuestionAdapter(mQuestions);
        setListAdapter(mAdapter);
    }

    /**
     * Starts Question Editor for question that was clicked.
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Question que = (Question)(getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), QuestionEditorActivity.class);

        i.putExtra(QuestionEditorFragment.EXTRA_QUESTION_ID, que.getId());
        startActivity(i);
    }

    /**
     * Resumes activity and updates question list.
     */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
















