package com.witty.wicked;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.witty.wicked.Utils.MyAdapter;

import java.util.ArrayList;

public class WickedActivity extends AppCompatActivity {
    private TextView mQuestion = null;
    private EditText mAnswer = null;
    private Button mSubmitButton = null;
    private ArrayList<String> mAnswerList = new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateUiAndSelectWickedAnswer();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wicked_main);
        initResources();
    }

    private void initResources() {
        mQuestion = findViewById(R.id.question);
        mAnswer = findViewById(R.id.answer);
        mSubmitButton = findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(buttonListener);
        recyclerView = (RecyclerView) findViewById(R.id.answers_list_layout);
        mAnswerList.add("ans1");
        mAnswerList.add("ans2");
        mAnswerList.add("ans3");
        mAnswerList.add("ans4");
        mAnswerList.add("ans5");
    }

    private void updateUiAndSelectWickedAnswer() {
        displayList(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mAnswerList.toArray(new String[mAnswerList.size()]),this);
        recyclerView.setAdapter(mAdapter);
    }

    private void displayList(boolean on) {
        if(on)  {
            recyclerView.setVisibility(View.VISIBLE);
            mAnswer.setVisibility(View.INVISIBLE);
            mSubmitButton.setVisibility(View.INVISIBLE);
        }
        else {
            recyclerView.setVisibility(View.INVISIBLE);
            mAnswer.setVisibility(View.VISIBLE);
            mSubmitButton.setVisibility(View.VISIBLE);
        }
    }

    public void selectedAnswer(TextView answerText, int mPosition) {
        //send text and position data to server.
        //check answers status and after everyone selected answer update the next question.
        updateNextQuestion();
        displayList(false);
    }

    private void updateNextQuestion() {
        mQuestion.setText(R.string.test);
    }
}
