package com.nada.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestResultPOJO {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sportsmen_id")
    @Expose
    private String sportsmenId;
    @SerializedName("sample_test_id")
    @Expose
    private String sampleTestId;
    @SerializedName("sample_date")
    @Expose
    private String sampleDate;
    @SerializedName("sample_code")
    @Expose
    private String sampleCode;
    @SerializedName("sportsmen_booking_id")
    @Expose
    private String sportsmenBookingId;
    @SerializedName("sample_result")
    @Expose
    private String sampleResult;
    @SerializedName("sample_description")
    @Expose
    private String sampleDescription;
    @SerializedName("sample_file")
    @Expose
    private String sampleFile;
    @SerializedName("sample_result_file")
    @Expose
    private String sampleResultFile;
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
    @SerializedName("sample_status")
    @Expose
    private String sampleStatus;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_dob")
    @Expose
    private String userDob;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportsmenId() {
        return sportsmenId;
    }

    public void setSportsmenId(String sportsmenId) {
        this.sportsmenId = sportsmenId;
    }

    public String getSampleTestId() {
        return sampleTestId;
    }

    public void setSampleTestId(String sampleTestId) {
        this.sampleTestId = sampleTestId;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getSportsmenBookingId() {
        return sportsmenBookingId;
    }

    public void setSportsmenBookingId(String sportsmenBookingId) {
        this.sportsmenBookingId = sportsmenBookingId;
    }

    public String getSampleResult() {
        return sampleResult;
    }

    public void setSampleResult(String sampleResult) {
        this.sampleResult = sampleResult;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public String getSampleFile() {
        return sampleFile;
    }

    public void setSampleFile(String sampleFile) {
        this.sampleFile = sampleFile;
    }

    public String getSampleResultFile() {
        return sampleResultFile;
    }

    public void setSampleResultFile(String sampleResultFile) {
        this.sampleResultFile = sampleResultFile;
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

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
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

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
