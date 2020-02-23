package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpMobileActivity extends AppCompatActivity {

    @BindView(R.id.ll_login)
    LinearLayout ll_login;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.tv_back_signin)
    TextView tv_back_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_otp_mobile);
        ButterKnife.bind(this);


        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(OtpMobileActivity.this, OtpActivity.class));
                callAPI();
            }
        });

        tv_back_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

//    public void callAPI() {
//        if(et_number.getText().toString().length()>=10) {
//
//            ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setMessage("Please Wait...");
//            progressDialog.setCancelable(true);
//            progressDialog.show();
//
//            AsyncHttpClient client = new AsyncHttpClient();
//
//
//            client.get(this, getMobileNumber(et_number.getText().toString()), new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
//                    if (progressDialog != null) {
//                        progressDialog.dismiss();
//                    }
//                    try {
//                        String responseString = new String(responseBody);
//                        startActivity(new Intent(OtpMobileActivity.this,OtpActivity.class));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                    if (progressDialog != null) {
//                        progressDialog.dismiss();
//                    }
//                }
//            });
//        }else{
//            Toast.makeText(getApplicationContext(),"Please Enter Correct Mobile Number",Toast.LENGTH_SHORT).show();
//        }
//    }

    public void callAPI() {
        if (et_number.getText().toString().length() == 10) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("phone", et_number.getText().toString());

            client.post(Webserviceurls.LOGIN_OTP_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    try {
                        String responseString = new String(responseBody);
                        Log.d(TagUtils.getTag(), "response:-" + responseString);
                        JSONObject jsonObject = new JSONObject(responseString);
                        if (jsonObject.optString("status").equalsIgnoreCase("1")) {
                            Pref.SetStringPref(getApplicationContext(), StringUtils.LOGIN_DATA,jsonObject.optJSONObject("result").toString());
                            Intent intent=new Intent(OtpMobileActivity.this, OtpActivity.class);
                            intent.putExtra("otp",jsonObject.optJSONObject("result").optString("otp"));
                            startActivity(intent);
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
            Toast.makeText(getApplicationContext(), "Please Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMobileNumber(String mobile) {
        return "\n" +
                "http://amyntas4sms.in/submitsms.jsp?user=Amyntas&key=fabcdb9ea8XX&mobile="+mobile+"&message=Nada%20App%20Verification%20code%20is8009&senderid=NADAFP&accusage=1";
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
