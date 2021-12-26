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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sustart.shdsystem.R;
import com.sustart.shdsystem.SHDSystemApplication;
import com.sustart.shdsystem.common.Constant;
import com.sustart.shdsystem.databinding.FragmentProductManageBinding;
import com.sustart.shdsystem.entity.BaseResponse;
import com.sustart.shdsystem.entity.Product;

import java.io.IOException;
import java.lang.reflect.Type;
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

    private String body;

    private SHDSystemApplication application;

    private FragmentProductManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductManageBinding.inflate(inflater, container, false);
        view = binding.getRoot();
// 通过application获取当前登录的的用户信息
        application = (SHDSystemApplication) getContext().getApplicationContext();
        System.out.println(application.loginUser.toString());

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
                body = response.body().string();
                System.out.println(body);
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
        refreshData();
    }

    /**
     * 通过线程加载数据
     */
    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                创建请求url
                String requestParam = "接口" + application.loginUser.getPhone();
//                发送用户手机号到后台，后台在Product数据库中根据手机号查找该sellerId字段，返回符合的所有Product。安卓端根据需要动态渲染
                String requestUrl = Constant.HOST_URL + requestParam;
                Request request = new Request.Builder().url(requestUrl).get().build();
                OkHttpClient client = new OkHttpClient();

                try {
//                    发起请求
                    client.newCall(request).enqueue(callback);
                } catch (NetworkOnMainThreadException ex) {
                    ex.printStackTrace();
                }
// 返回的数据不为空，渲染到列表上
                if(body != null){
                    Gson gson = new Gson();
                    Type jsonType = new TypeToken<BaseResponse<List<Product>>>() {
                    }.getType();
                    BaseResponse<List<Product>> productListResponse = gson.fromJson(body, jsonType);
                    for (Product product : productListResponse.getData()) {
                        adapter.add(product);
                    }
                    adapter.notifyDataSetChanged();
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
//                        todo 点击一件自己发布的商品后打开该商品的管理页
//                        Intent intent = new Intent(this,
//                                DetailActivity.class);
//                        Product product = adapter.getItem(i);
//                        intent.putExtra("productId", product.getId());
//                        startActivity(intent);
                    }
                });
    }
}