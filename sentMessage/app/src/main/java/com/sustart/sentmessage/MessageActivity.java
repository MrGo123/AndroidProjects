package com.sustart.sentmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

//        获取展示文本框对象
        TextView tvMessage = findViewById(R.id.show_message);
//        通过 显式的 intent 根据 key 来获取 value
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_STRING);

        if (message != null) {
            if (tvMessage != null) {
                tvMessage.setText(message);
            }
        }
    }
}