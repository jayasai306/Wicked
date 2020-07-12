package com.witty.wicked;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

class JoinWicked extends AppCompatActivity {

    private EditText mTextCode = null;
    private EditText mName = null;
    private Button mJoinButton = null;
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),WickedActivity.class);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_wicked);
        initResources();
        Log.d("jaya","onCreate");
    }

    private void initResources() {
        mTextCode = findViewById(R.id.text_code);
        mJoinButton = findViewById(R.id.join_button);
        mJoinButton.setOnClickListener(buttonListener);
        mJoinButton.setEnabled(false);
        mName = findViewById(R.id.name);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mName.getText().toString().length() != 0 && mTextCode.getText().toString().length() != 0) {
                    mJoinButton.setEnabled(true);
                } else {
                    mJoinButton.setEnabled(false);
                }
            }
        });
        mTextCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mTextCode.getText().toString().length() != 0 && mName.getText().toString().length() != 0) {
                    mJoinButton.setEnabled(true);
                } else {
                    mJoinButton.setEnabled(false);
                }
            }
        });
    }


}
