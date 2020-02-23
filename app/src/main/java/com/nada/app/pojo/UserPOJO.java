package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPOJO {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_dob")
    @Expose
    private Object userDob;
    @SerializedName("user_role")
    @Expose
    private String userRole;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_sports")
    @Expose
    private Object userSports;
    @SerializedName("user_bloodgroup")
    @Expose
    private Object userBloodgroup;
    @SerializedName("user_weight")
    @Expose
    private Object userWeight;
    @SerializedName("user_height")
    @Expose
    private Object userHeight;
    @SerializedName("user_center")
    @Expose
    private String userCenter;
    @SerializedName("user_address")
    @Expose
    private Object userAddress;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("user_state")
    @Expose
    private String userState;
    @SerializedName("user_country")
    @Expose
    private String userCountry;
    @SerializedName("user_pincode")
    @Expose
    private Object userPincode;
    @SerializedName("user_dicipline")
    @Expose
    private Object userDicipline;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Object getUserDob() {
        return userDob;
    }

    public void setUserDob(Object userDob) {
        this.userDob = userDob;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Object getUserSports() {
        return userSports;
    }

    public void setUserSports(Object userSports) {
        this.userSports = userSports;
    }

    public Object getUserBloodgroup() {
        return userBloodgroup;
    }

    public void setUserBloodgroup(Object userBloodgroup) {
        this.userBloodgroup = userBloodgroup;
    }

    public Object getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(Object userWeight) {
        this.userWeight = userWeight;
    }

    public Object getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(Object userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(String userCenter) {
        this.userCenter = userCenter;
    }

    public Object getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Object userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public Object getUserPincode() {
        return userPincode;
    }

    public void setUserPincode(Object userPincode) {
        this.userPincode = userPincode;
    }

    public Object getUserDicipline() {
        return userDicipline;
    }

    public void setUserDicipline(Object userDicipline) {
        this.userDicipline = userDicipline;
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
