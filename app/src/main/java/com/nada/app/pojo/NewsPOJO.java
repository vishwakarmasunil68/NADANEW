package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsPOJO {
    @SerializedName("news_id")
    @Expose
    private String newsId;
    @SerializedName("news_title")
    @Expose
    private String newsTitle;
    @SerializedName("news_description")
    @Expose
    private String newsDescription;
    @SerializedName("news_title_hi")
    @Expose
    private String newsTitleHi;
    @SerializedName("news_description_hi")
    @Expose
    private String newsDescriptionHi;
    @SerializedName("news_date")
    @Expose
    private String newsDate;
    @SerializedName("news_path")
    @Expose
    private String newsPath;
    @SerializedName("news_status")
    @Expose
    private String newsStatus;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;


    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsTitleHi() {
        return newsTitleHi;
    }

    public void setNewsTitleHi(String newsTitleHi) {
        this.newsTitleHi = newsTitleHi;
    }

    public String getNewsDescriptionHi() {
        return newsDescriptionHi;
    }

    public void setNewsDescriptionHi(String newsDescriptionHi) {
        this.newsDescriptionHi = newsDescriptionHi;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsPath() {
        return newsPath;
    }

    public void setNewsPath(String newsPath) {
        this.newsPath = newsPath;
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
