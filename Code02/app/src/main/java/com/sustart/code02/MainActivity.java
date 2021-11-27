package com.sustart.code02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String COUNT_VALUE = "count_value";
    private int count = 0;
    TextView tvCount;

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        count = saveInstanceState.getInt(COUNT_VALUE);
        if(tvCount != null){
            tvCount.setText(Integer.toString(count));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(COUNT_VALUE, count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        1. 单击showToast之后的效果
        Button btnShowToast = findViewById(R.id.btnShowToast);
//        监听单击事件
        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                   在哪里显示什么，显示多久
                Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
            }
        });

//        2. 单击count之后的效果
        final TextView tvCount = findViewById(R.id.tvCount);
        Button btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setText(Integer.toString(++count));
            }
        });
    }
}