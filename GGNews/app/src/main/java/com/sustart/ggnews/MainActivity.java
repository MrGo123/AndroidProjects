package com.sustart.ggnews;


import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private ListView lvNewsList;
    private List<News> newsData;
    private NewsAdapter adapter;
    private int page = 2;
    private int mCurrentColIndex = 2;

    private int[] mCols = new int[]{Constants.NEWS_COL5,
            Constants.NEWS_COL7, Constants.NEWS_COL8,
            Constants.NEWS_COL10, Constants.NEWS_COL11};

    private String TAG;

    private okhttp3.Callback callback = new okhttp3.Callback() {
        @Override
        public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
//            响应成功
            if (response.isSuccessful()) {
//                获取响应数据体
                String body = response.body().string();
                System.out.println(body);
//                渲染到ui线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type jsonType = new TypeToken<BaseResponse<List<News>>>() {
                        }.getType();
                        BaseResponse<List<News>> newsListResponse = gson.fromJson(body, jsonType);
                        for (News news : newsListResponse.getData()) {
                            adapter.add(news);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            } else { }
        }

        @Override
        public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e){
            Log.e(TAG, "连接服务器失败! ");
            e.printStackTrace();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        newsData = new ArrayList<>();
        adapter = new NewsAdapter(MainActivity.this, R.layout.list_item, newsData);
        lvNewsList.setAdapter(adapter);
        refreshData(page);
    }

    /**
     * 通过线程加载数据
     *
     * @param page
     */
    private void refreshData(final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                构造请求参数
                NewsRequest requestObj = new NewsRequest();
                requestObj.setCol(mCols[mCurrentColIndex]);
                requestObj.setNum(Constants.NEWS_NUM);
                requestObj.setPage(page);
                String urlParams = requestObj.toString();
//                创建请求url
                String requestUrl = Constants.GENERAL_NEWS_URL + urlParams;
                Log.e(TAG, requestUrl);
                Request request = new Request.Builder().url(requestUrl).get().build();
                OkHttpClient client = new OkHttpClient();
                try {
//                    发起请求
                    client.newCall(request).enqueue(callback);
                } catch (NetworkOnMainThreadException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取列表对象并绑定事件监听器、Intent
     */
    private void initView() {
        lvNewsList = findViewById(R.id.lv_news_list);
        lvNewsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this,
                                DetailsActivity.class);
                        News news = adapter.getItem(i);
                        intent.putExtra("title", news.getTitle());
                        intent.putExtra("ctime", news.getPublishTime());
                        intent.putExtra("source", news.getSource());
                        startActivity(intent);
                    }
                });
    }
}