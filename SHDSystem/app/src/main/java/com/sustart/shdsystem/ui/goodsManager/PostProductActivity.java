package com.sustart.shdsystem.ui.goodsManager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sustart.shdsystem.R;

/**
 * 发布新商品
 */
public class PostProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);
    }

//    todo 图片上传部分：上传时就要重命名图片名称，能够保证图片不重复。考虑用：用户名+时间戳 = 文件名称。
//    todo 再把该文件名称存到该商品的 image_name 中。

}