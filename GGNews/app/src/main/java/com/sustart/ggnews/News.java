package com.sustart.ggnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {
    @Expose(serialize = false, deserialize = false)
    private String id;

    @SerializedName("title")
    private String Title;

    @SerializedName("description")
    private String source;

    @SerializedName("picUrl")
    private String picUrl;

    @SerializedName("url")
    private String contentUrl;

    @SerializedName("ctime")
    private String publishTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getSource() {
        return source;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }
}
