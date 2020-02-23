package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nada.app.R;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.ToastClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends AppCompatActivity {


    @BindView(R.id.ll_login)
    LinearLayout ll_login;
    @BindView(R.id.et_otp)
    EditText et_otp;

    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);

        otp=getIntent().getStringExtra("otp");

//
//        ll_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//            }
//        });

        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_otp.getText().toString().equalsIgnoreCase(otp)){
                    Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN,true);
                    startActivity(new Intent(OtpActivity.this,MainActivity.class));
                    finishAffinity();
                }else{
                    ToastClass.showShortToast(getApplicationContext(),"Wrong otp");
                }
            }
        });
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
