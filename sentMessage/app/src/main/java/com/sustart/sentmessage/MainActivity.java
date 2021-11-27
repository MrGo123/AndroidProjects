package com.sustart.sentmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    作为 intent 存入消息的 key
    public static final String MESSAGE_STRING = "hello, I am a message.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etMessage = findViewById(R.id.message);
        Button btSend = findViewById(R.id.send_message);

        btSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = etMessage.getText().toString();
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//            }

            @Override
            public void onClick(View v) {
//                从编辑框获取字符串
                String message = etMessage.getText().toString();
//                创建联通两个 Activity 的 显式的 intent
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
//                将消息传递（key-value 形式）
                intent.putExtra(MESSAGE_STRING, message);
//                启动 Activity
                startActivity(intent);
            }
        });

    }
}