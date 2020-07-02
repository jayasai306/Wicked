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
    private ArrayList<String> mAnswerList = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    Class Waiting {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.wicked_main);
        initResources();
    }

    private void initResources() {
        mQuestion = findViewById(R.id.question);
        mAnswer = findViewById(R.id.answer);
        mSubmitButton = findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(buttonListener);
        recyclerView = (RecyclerView) findViewById(R.id.answers_list_layout);
    }

    private void updateUiAndSelectWickedAnswer() {
        displayList();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter((String[]) mAnswerList.toArray(),this);
        recyclerView.setAdapter(mAdapter);
    }

    private void displayList() {

    }

    public void selectedAnswer(TextView answerText, int mPosition) {
        //send text and position data to server.
        //check answers status and after everyone selected answer update the next question.
        updateNextQuestion();
    }

    private void updateNextQuestion() {
        mQuestion.setText(R.string.test);
    }
}
