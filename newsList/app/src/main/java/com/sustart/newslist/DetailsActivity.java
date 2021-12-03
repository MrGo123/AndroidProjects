package com.sustart.newslist;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    private String[] titles = null;
    private String[] authors = null;
    private static final String NEWS_TITLE = "news_title";
    private static final String NEWS_AUTHOR = "news_author";
    private List<Map<String, String>> dataList = new ArrayList<>();
    public List<News> newsList = new ArrayList<>();

    //    从xml文件中读取数据资源
    private void initData() {
        int length;
        titles = getResources().getStringArray(R.array.titles);
        authors = getResources().getStringArray(R.array.authors);
        TypedArray images = getResources().obtainTypedArray(R.array.images);
        if (titles.length > authors.length) {
            length = authors.length;
        } else {
            length = titles.length;
        }

        for (int i = 0; i < length; i++) {
            News news = new News();
            news.setmTitle(titles[i]);
            news.setmAuthor(titles[i]);
            news.setmImageId(images.getResourceId(i, 0));
            newsList.add(news);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        initData();
//
////        使用自定义的适配器
//        NewsAdapter newsAdapter = new NewsAdapter(DetailsActivity.this, R.layout.list_item, newsList);
//
//        ListView lvNewsList = findViewById(R.id.lv_news_list);
//        lvNewsList.setAdapter(newsAdapter);

//        实验9 数据持久化部分
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(DetailsActivity.this);
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(NewsContract.NewsEntry.TABLE_NAME, null, null, null, null, null, null);

        List<News> newsList = new ArrayList<>();

        int titleIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NAME_TITLE);
        int authorIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NAME_AUTHOR);
        int imageIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NAME_IMAGE);

        while (cursor.moveToNext()) {
            News news = new News();

            String title = cursor.getString(titleIndex);
            String author = cursor.getString(authorIndex);
            String image = cursor.getString(imageIndex);

//            Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/" + image));

            news.setmTitle(title);
            news.setmAuthor(author);
            news.setmImageId(Integer.parseInt(image));
            newsList.add(news);
        }

        NewsAdapter newsAdapter = new NewsAdapter(DetailsActivity.this, R.layout.list_item, newsList);
        ListView lvNewsList = findViewById(R.id.lv_news_list);
        lvNewsList.setAdapter(newsAdapter);
    }

    // 使用ArrayAdapter类
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        titles = getResources().getStringArray(R.array.titles);
//        authors = getResources().getStringArray(R.array.authors);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
//        ListView lvNewList = findViewById(R.id.lv_news_list);
//        lvNewList.setAdapter(adapter);
//
//    }


//    使用Map实现标题和作者时
//    private void initData() {
//        int length;
//        titles = getResources().getStringArray(R.array.titles);
//        authors = getResources().getStringArray(R.array.authors);
//
//        if (titles.length > authors.length) {
//            length = authors.length;
//        } else {
//            length = titles.length;
//        }
//
//        for (int i = 0; i < length; i++) {
//            Map map = new HashMap();
//            map.put(NEWS_TITLE, titles[i]);
//            map.put(NEWS_AUTHOR, authors[i]);
//            dataList.add(map);
//        }
//    }

//    使用SimpleAdapter
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initData();
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,
//                dataList, android.R.layout.simple_list_item_2,
//                new String[]{NEWS_TITLE, NEWS_AUTHOR},
//                new int[]{android.R.id.text1, android.R.id.text2});
//
//        ListView lvNewsList = findViewById(R.id.lv_news_list);
//        lvNewsList.setAdapter(simpleAdapter);
//    }
}