package edu.andrews.cptr252.rmatthew.quizgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Hosts Question Editor Fragment. Also sets up a viewpager and options menu.
 */
public class QuestionEditorActivity extends FragmentActivity {
    private ViewPager mViewPager;

    private ArrayList<Question> mQuestions;

    /**
     * Creates a viewpager and launches a fragment for the question that was selected.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mQuestions = QuestionList.getInstance(this).getQuestions();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Question que = mQuestions.get(position);
                return QuestionEditorFragment.newInstance(que.getId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

        UUID queId = (UUID) getIntent().getSerializableExtra(QuestionEditorFragment.EXTRA_QUESTION_ID);

        for(int i = 0; i< mQuestions.size(); i++) {
            if(mQuestions.get(i).getId().equals(queId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v,  int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                Question question = mQuestions.get(position);
                if(question.getQuestion() != null) {
                    setTitle(question.getQuestion());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * Inflates an options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_game, menu);
        return true;
    }

    /**
     * Returns if a question was selected.
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
