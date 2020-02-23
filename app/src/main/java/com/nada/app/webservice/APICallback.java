package com.nada.app.webservice;

/**
 * Created by sunil on 29-12-2016.
 */

public interface APICallback {
    public void onGetMsg(String apicall, String response);
    public void onErrorMsg(String apicall, String error);
}
