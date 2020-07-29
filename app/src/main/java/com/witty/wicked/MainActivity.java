package com.witty.wicked;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.witty.wicked.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button mStartGameButton = null;
    private Button mJoinGameButton = null;
    private EditText mName = null;
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_start_game) {
                JSONObject nameJson = new JSONObject();
                try {
                    nameJson.put("CreateTable","CreateTable");
                    nameJson.put("name",mName.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WickedService.sendToServer(nameJson.toString());
            }else if (v.getId() == R.id.button_join_game) {
                Log.d("jaya","onclick");
                Utils.setmJoinGame(true);
                Intent i = new Intent(getApplicationContext(), JoinWicked.class);
                i.putExtra("name",mName.getText().toString());
                startActivity(i);
            }
        }
    };

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Utils.setmStartGame(true);
                    Intent i = new Intent(getApplicationContext(), JoinWicked.class);
                    i.putExtra("name",mName.getText().toString());
                    startActivity(i);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
        startService(new Intent(this,WickedService.class));
    }

    private void initResources() {
        mStartGameButton = findViewById(R.id.button_start_game);
        mJoinGameButton = findViewById(R.id.button_join_game);
        mName = findViewById(R.id.name);
        Utils.setmMainHandler(mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStartGameButton.setOnClickListener(buttonListener);
        mJoinGameButton.setOnClickListener(buttonListener);
    }
}
