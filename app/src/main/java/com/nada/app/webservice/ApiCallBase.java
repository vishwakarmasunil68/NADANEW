package com.nada.app.webservice;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class ApiCallBase {

    Activity activity;
    APICallback apiCallback;
    String msg;

    public ApiCallBase(Activity activity, APICallback apiCallback, String msg) {
        this.activity = activity;
        this.apiCallback = apiCallback;
        this.msg = msg;
    }

    public void requestAPICall(String url, RequestParams requestParams) {

        Log.d(TagUtils.getTag(), "url:-" + url);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.addHeader("API-KEY", "0ccdc8d9ab1659b139b690498f03873c");

        asyncHttpClient.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String responseString = new String(responseBody);
                    Log.d(TagUtils.getTag(), msg + ":-" + responseString);
                    if (responseString != null && responseString.length() > 0) {
                        if (apiCallback != null) {
//                            WebServicesCallBack mcallback = (WebServicesCallBack) object;
                            apiCallback.onGetMsg(msg, responseString);
                        } else {
                            ToastClass.showShortToast(activity, "Something went wrong");
                        }
                    } else {
                        ToastClass.showShortToast(activity, "No Internet");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TagUtils.getTag(), "call failed");
//                Log.d(TagUtils.getTag(), msg+":- status_code:-" + statusCode+",error:-"+(new String(responseBody)));
            }
        });
    }

    public void makeApiCall(String url, JSONObject jsonObject) {

        Log.d(TagUtils.getTag(), "url:-" + url);
        Log.d(TagUtils.getTag(), "params:-" + jsonObject.toString());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (Exception e) {

        }

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.addHeader("API-KEY", "0ccdc8d9ab1659b139b690498f03873c");

        asyncHttpClient.post(activity, url, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String responseString = new String(responseBody);
                    Log.d(TagUtils.getTag(), msg + ":-" + responseString);
                    if (responseString != null && responseString.length() > 0) {
                        if (apiCallback != null) {
//                            WebServicesCallBack mcallback = (WebServicesCallBack) object;
                            apiCallback.onGetMsg(msg, responseString);
                        } else {
                            ToastClass.showShortToast(activity, "Something went wrong");
                        }
                    } else {
                        ToastClass.showShortToast(activity, "No Internet");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TagUtils.getTag(), "call failed");
//                Log.d(TagUtils.getTag(), msg+":- status_code:-" + statusCode+",error:-"+(new String(responseBody)));
            }
        });
    }
}
