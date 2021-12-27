package com.sustart.shdsystem.entity;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sustart.shdsystem.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息类
 */
public class Product implements Serializable {
    private static final String TAG = Product.class.getSimpleName();

    private Integer id;
    private String name;
    private Integer price;
    private String imageName;
    private String type;
    private String description;
    //    保存时间戳
    private Long publishTime;
    private Long dealTime;
    private String sellerId;
    private String buyerId;

    /**
     * 从 raw 的 json 文件中加载数据
     */
    public static List<Product> initProductList(Resources resources) {
// todo 这里要把本地请求改为通过okhttp的接口请求

        InputStream inputStream = resources.openRawResource(R.raw.products);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];


        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "从json文件中读取数据出错", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "关闭json文件字节流出错", exception);
            }
        }

        // 从 Json 转为 对象
        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<Product>>() {
        }.getType();

        return gson.fromJson(jsonProductsString, productListType);
    }


    public Product() {
    }

    public Product(Integer id, String name, Integer price, String imageName, String type, String description, Long publishTime, Long dealTime, String sellerId, String buyerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.type = type;
        this.description = description;
        this.publishTime = publishTime;
        this.dealTime = dealTime;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getDealTime() {
        return dealTime;
    }

    public void setDealTime(Long dealTime) {
        this.dealTime = dealTime;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
