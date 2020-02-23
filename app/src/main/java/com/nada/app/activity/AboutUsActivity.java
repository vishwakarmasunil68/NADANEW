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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nada.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.ll_vision)
    LinearLayout ll_vision;
    @BindView(R.id.ll_primary_function)
    LinearLayout ll_primary_function;
    @BindView(R.id.ll_governing_body)
    LinearLayout ll_governing_body;
    @BindView(R.id.ll_anti_appeal_panel)
    LinearLayout ll_anti_appeal_panel;
    @BindView(R.id.ll_doping_disciplinary)
    LinearLayout ll_doping_disciplinary;
    @BindView(R.id.ll_therapeutic)
    LinearLayout ll_therapeutic;
    @BindView(R.id.ll_who_is_who)
    LinearLayout ll_who_is_who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","vision");
                startActivity(intent);
            }
        });

        ll_primary_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","primary_function");
                startActivity(intent);
            }
        });

        ll_governing_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","governing_body");
                startActivity(intent);
            }
        });

        ll_anti_appeal_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","anti_doping_appeal_panel");
                startActivity(intent);
            }
        });

        ll_doping_disciplinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","anti_doping_disciplinary_panel");
                startActivity(intent);
            }
        });

        ll_therapeutic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","therapeutic");
                startActivity(intent);
            }
        });

        ll_who_is_who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsDataActivity.class);
                intent.putExtra("about_type","who_is_who");
                startActivity(intent);
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
