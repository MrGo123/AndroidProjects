package com.sustart.shdsystem.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sustart.shdsystem.R;
import com.sustart.shdsystem.common.Constant;
import com.sustart.shdsystem.databinding.FragmentHomeBinding;
import com.sustart.shdsystem.entity.Product;
import com.sustart.shdsystem.ui.home.detail.DetailActivity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private String TAG = "HomeFragment.class";
    private List<Product> productList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //  创建可循环视图 RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
//      初始化数据
        getProductListByHttp();
//        只要数据没加载出来就空转等待
        while (productList == null) {
//            Toast.makeText(getContext(), "努力加载中……", Toast.LENGTH_SHORT).show();
        }
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(productList, getContext());
        adapter.setOnItemClickLitener(new ProductCardRecyclerViewAdapter.OnItemClickLitener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(Product product, View view, int position) {
//                点击之后触发详情页，这个详情页可能用增加类型的fragment来做更好。不用跳出这个MainActivity。
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("detailProduct", product);
                startActivity(intent);
                System.out.println("打开详情页" + position);
            }
        });
        recyclerView.setAdapter(adapter);

        //  卡片间距
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        return view;
    }


    /**
     * 通过线程加载数据
     */
    private void getProductListByHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                创建请求url
                String requestParam = "product/queryAll";
//                发送用户id到后台，后台在Product数据库中根据手机号查找该sellerId字段，返回符合的所有Product。安卓端根据需要动态渲染
                String requestUrl = Constant.HOST_URL + requestParam;
                Request request = new Request.Builder().url(requestUrl).get().build();
                OkHttpClient client = new OkHttpClient();
                try {
                    client.newCall(request).enqueue(callback);
                } catch (NetworkOnMainThreadException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private okhttp3.Callback callback = new okhttp3.Callback() {
        @Override
        public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()) {
//                获取响应数据体
                String body = response.body().string();
                if (body != null) {
                    Log.e(TAG, "商品所有数据接口请求到数据：" + body);
//                解析json
                    Gson gson = new Gson();
                    Type jsonType = new TypeToken<List<Product>>() {
                    }.getType();
                    productList = gson.fromJson(body, jsonType);
                }
            }
        }

        @Override
        public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
            Log.e(TAG, "连接服务器失败! ");
            e.printStackTrace();
        }

    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        System.out.println("点击了控件");
    }
}