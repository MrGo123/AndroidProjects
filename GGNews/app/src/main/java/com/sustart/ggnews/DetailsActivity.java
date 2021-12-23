package com.sustart.ggnews;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title = findViewById(R.id.article_title);
        TextView subhead = findViewById(R.id.article_subheading);
        TextView text = findViewById(R.id.article_text);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        subhead.setText(intent.getStringExtra("ctime"));
        text.setText(intent.getStringExtra("source"));
    }
}