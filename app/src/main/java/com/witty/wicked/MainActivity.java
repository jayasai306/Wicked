package com.witty.wicked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mStartGameButton = null;
    private Button mJoinGameButton = null;
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_start_game) {
                Intent i = new Intent(getApplicationContext(), StartWicked.class);
                startActivity(i);
            }else if (v.getId() == R.id.button_join_game) {
                Log.d("jaya","onclick");
                Intent i = new Intent(getApplicationContext(), JoinWicked.class);
                startActivity(i);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
    }

    private void initResources() {
        mStartGameButton = findViewById(R.id.button_start_game);
        mJoinGameButton = findViewById(R.id.button_join_game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStartGameButton.setOnClickListener(buttonListener);
        mJoinGameButton.setOnClickListener(buttonListener);
    }
}
