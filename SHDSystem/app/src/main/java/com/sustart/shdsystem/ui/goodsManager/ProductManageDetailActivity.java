package com.sustart.shdsystem.ui.goodsManager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sustart.shdsystem.R;

/**
 * 用户发布的一件商品的管理页，可修改该商品的基本信息
 */
public class ProductManageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage_detail);
//        todo 渲染该商品的数据用于修改+提交http修改
    }
}