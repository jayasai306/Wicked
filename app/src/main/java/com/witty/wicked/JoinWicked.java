package com.witty.wicked;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.witty.wicked.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;

class JoinWicked extends AppCompatActivity {

    private EditText mTextCode = null;
    private String mName = null;
    private Button mJoinButton = null;
    private TextView mCode = null;
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utils.ismJoinGame()) {
                mJoinButton.setVisibility(View.INVISIBLE);
                JSONObject nameJson = new JSONObject();
                try {
                    nameJson.put("InsertTable","InsertTable");
                    nameJson.put("name",mName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WickedService.sendToServer(nameJson.toString());
            } else {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("StartGame","StartGame");
                    jsonObject.put("name",mName);
                    jsonObject.put("code", Utils.getmCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WickedService.sendToServer(jsonObject.toString());
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_wicked);
        Utils.setJoinHandler(mHandler);
        initResources();
        initUI();
        Log.d("jaya","onCreate");
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Intent i = new Intent(getApplicationContext(), WickedActivity.class);
                    startActivity(i);
            }
        }
    };
    private void initUI() {
        if (Utils.ismStartGame()) {
            mTextCode.setVisibility(View.INVISIBLE);
            mCode.setVisibility(View.VISIBLE);
            if (Utils.getmCode() != null) {
                mCode.setText(Utils.getmCode());
            }
        } else {
            mTextCode.setVisibility(View.VISIBLE);
            mCode.setVisibility(View.INVISIBLE);
            mTextCode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(mTextCode.getText().toString().length() != 0) {
                        mJoinButton.setEnabled(true);
                    } else {
                        mJoinButton.setEnabled(false);
                    }
                }
            });
            if (mTextCode.getText().toString().isEmpty() /*|| mName.getText().toString().isEmpty()*/) {
                mJoinButton.setEnabled(false);
            } else {
                mJoinButton.setEnabled(true);
            }
        }
    }

    private void initResources() {
        mTextCode = findViewById(R.id.edit_text_code);
        mCode = findViewById(R.id.text_code);
        mJoinButton = findViewById(R.id.join_button);
        mJoinButton.setOnClickListener(buttonListener);
        mName = getIntent().getStringExtra("name");

    }


}
