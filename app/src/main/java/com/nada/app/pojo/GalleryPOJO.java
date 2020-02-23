package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryPOJO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("actual_file_name")
    @Expose
    private String actualFileName;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("added_by")
    @Expose
    private String addedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getActualFileName() {
        return actualFileName;
    }

    public void setActualFileName(String actualFileName) {
        this.actualFileName = actualFileName;
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
}
