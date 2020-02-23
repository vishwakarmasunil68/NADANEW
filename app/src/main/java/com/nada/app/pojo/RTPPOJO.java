package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RTPPOJO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("rtp_status")
    @Expose
    private String rtpStatus;
    @SerializedName("depute_on_off")
    @Expose
    private String deputeOnOff;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("depute_on")
    @Expose
    private String deputeOn;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("sports_name")
    @Expose
    private String sportsName;
    @SerializedName("user_dicipline")
    @Expose
    private String userDicipline;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRtpStatus() {
        return rtpStatus;
    }

    public void setRtpStatus(String rtpStatus) {
        this.rtpStatus = rtpStatus;
    }

    public String getDeputeOnOff() {
        return deputeOnOff;
    }

    public void setDeputeOnOff(String deputeOnOff) {
        this.deputeOnOff = deputeOnOff;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getDeputeOn() {
        return deputeOn;
    }

    public void setDeputeOn(String deputeOn) {
        this.deputeOn = deputeOn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public String getUserDicipline() {
        return userDicipline;
    }

    public void setUserDicipline(String userDicipline) {
        this.userDicipline = userDicipline;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
