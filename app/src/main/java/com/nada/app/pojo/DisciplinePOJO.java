package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisciplinePOJO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sports_id")
    @Expose
    private String sportsId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportsId() {
        return sportsId;
    }

    public void setSportsId(String sportsId) {
        this.sportsId = sportsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
