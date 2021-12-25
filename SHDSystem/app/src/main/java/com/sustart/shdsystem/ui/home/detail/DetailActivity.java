package com.sustart.shdsystem.ui.home.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sustart.shdsystem.R;
import com.sustart.shdsystem.entity.Product;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Product detailProduct = (Product) intent.getSerializableExtra("detailProduct");

//        渲染信息，最好通过adapter来绑定信息

        TextView productName = findViewById(R.id.detail_product_name);
        productName.setText(detailProduct.getName()+"商品编号="+detailProduct.getId());
        TextView productPrice = findViewById(R.id.detail_product_price);
        productPrice.setText(String.valueOf(detailProduct.getPrice()));
        TextView productDesc = findViewById(R.id.detail_product_desc);
        productDesc.setText(detailProduct.getDescription());


        // 点击购买按钮后转支付页
        ImageButton buyButton = findViewById(R.id.detail_buy_btn);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailActivity.this, PurchaseActivity.class);
                intent1.putExtra("buyProduct", detailProduct);
                startActivity(intent1);
            }
        });
    }

}