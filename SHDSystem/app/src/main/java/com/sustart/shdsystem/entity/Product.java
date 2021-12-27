package com.sustart.shdsystem.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商品信息类
 */
public class Product implements Serializable {
    private static final String TAG = Product.class.getSimpleName();

    private Integer id;
    private String name;
    private Integer price;
    private String imageUrl;
    private String type;
    private String description;
    //    保存时间戳
    private Timestamp publishTime;
    private Timestamp dealTime;
    private String sellerId;
    private String buyerId;

    public Product() {
    }

    public Product(Integer id, String name, Integer price, String imageUrl, String type, String description, Timestamp publishTime, Timestamp dealTime, String sellerId, String buyerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
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
