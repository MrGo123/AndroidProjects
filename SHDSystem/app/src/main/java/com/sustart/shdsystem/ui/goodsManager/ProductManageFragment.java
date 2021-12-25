package com.sustart.shdsystem.ui.goodsManager;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sustart.shdsystem.R;
import com.sustart.shdsystem.databinding.FragmentProductManageBinding;
import com.sustart.shdsystem.entity.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductManageFragment extends Fragment {
    private ListView productsListView;
    private List<Product> productDataList;
    private ProductManageAdapter adapter;
    private View view;
    private String TAG = "ProductManageFragment.class";

    private FragmentProductManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductManageBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        initView();
        initData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private okhttp3.Callback callback = new okhttp3.Callback() {
        @Override
        public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
//            响应成功
            if (response.isSuccessful()) {
//                获取响应数据体
                String body = response.body().string();
                System.out.println(body);
//                渲染到ui线程
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Gson gson = new Gson();
//                        Type jsonType = new TypeToken<BaseResponse<List<Product>>>() {
//                        }.getType();
//                        BaseResponse<List<Product>> newsListResponse = gson.fromJson(body, jsonType);
//                        for (Product news : newsListResponse.getData()) {
//                            adapter.add(news);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                });
            }
        }

        @Override
        public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
            Log.e(TAG, "连接服务器失败! ");
            e.printStackTrace();
        }

    };

    private void initData() {
        productDataList = new ArrayList<>();
        adapter = new ProductManageAdapter(getContext(), R.layout.product_list_item, productDataList);
        productsListView.setAdapter(adapter);
        refreshData(1);
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
                String urlParams = "url参数列表";
//                创建请求url
                String requestUrl = "服务器域名" + urlParams;
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
        productsListView = view.findViewById(R.id.lv_product_manage_list);
        productsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int i, long l) {
//                        Intent intent = new Intent(this,
//                                DetailsActivity.class);
//                        Product product = adapter.getItem(i);
//                        intent.putExtra("productId", product.getId());
//                        startActivity(intent);
                    }
                });
    }
}