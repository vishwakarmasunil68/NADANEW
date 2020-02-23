package com.nada.app.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.ll_login)
    LinearLayout ll_login;
    @BindView(R.id.tv_signup)
    TextView tv_signup;
    @BindView(R.id.tv_signin_otp)
    TextView tv_signin_otp;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        Crashlytics.getInstance().crash();
        if(Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN,false)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finishAffinity();
        }

        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                callAPI();
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        tv_signin_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, OtpMobileActivity.class));
            }
        });
    }

    public void callAPI() {
        if (et_email.getText().toString().length() != 0 && et_password.getText().toString().length() != 0) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("email", et_email.getText().toString());
            params.put("password", et_password.getText().toString());
            params.put("request_type", "api");
            params.put("login", "yes");
            params.put("token", Pref.GetStringPref(getApplicationContext(),Pref.FCM_REGISTRATION_TOKEN,""));

            client.post(Webserviceurls.LOGIN_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    try {
                        String responseString = new String(responseBody);
                        Log.d(TagUtils.getTag(), "response:-" + responseString);
                        JSONObject jsonObject = new JSONObject(responseString);
                        if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                            Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN,true);
                            Pref.SetStringPref(getApplicationContext(),StringUtils.LOGIN_DATA,jsonObject.optJSONObject("data").toString());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finishAffinity();
                        } else {
                            ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    ToastClass.showShortToast(getApplicationContext(), "Network error");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Email and Password Correctly", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.ic_header);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}
