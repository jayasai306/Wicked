package com.witty.wicked;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.witty.wicked.Utils.MyAdapter;
import com.witty.wicked.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class WickedActivity extends AppCompatActivity {
    private TextView mQuestion = null;
    private EditText mAnswer = null;
    private Button mSubmitButton = null;
    private ArrayList<String> mQuestionList = null;
    private ArrayList<String> mAnswerList = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mWaitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String SERVER = "ws://10.0.2.2:1337/";
    private int mIndex = -1;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            JSONObject startJson = new JSONObject();
            try {
                startJson.put("SendAnswer",mAnswer.getText().toString());
                startJson.put("name", Utils.getmName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            WickedService.sendToServer(startJson.toString());
            //updateUiAndSelectWickedAnswer();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wicked_main);
        Utils.setmWickedHandler(mHandler);
        initResources();
        updateNextQuestion();
    }


    private void initResources() {
        mQuestionList = Utils.getQuestionList();
        mQuestion = findViewById(R.id.question);
        mAnswer = findViewById(R.id.answer);
        mSubmitButton = findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(buttonListener);
        recyclerView = (RecyclerView) findViewById(R.id.answers_list_layout);
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
        recyclerView.setVisibility(View.INVISIBLE);
        mWaitAdapter = new WaitAdapter(Utils.getNames(),this);
        recyclerView.setAdapter(mWaitAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void updateNextQuestion() {
        mIndex++;
        mQuestion.setText(mQuestionList.get(mIndex));
    }

    /*public void updateQuestions(String[] questions) {
        mQuestionList.clear();
        for (int i=0;i<questions.length;i++) {
            mQuestionList.add(questions[i]);
        }
    }*/

    public void updateAnswers(ArrayList arrayList) {
        mAnswerList.clear();
        mAnswerList = arrayList;
        updateUiAndSelectWickedAnswer();
    }

    public void startNextQuestion() {
        updateNextQuestion();
        displayList(false);
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    updateAnswers(Utils.getAnswerList());
            }
        }
    };
}
