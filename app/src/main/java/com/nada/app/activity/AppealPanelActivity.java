package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nada.app.adapter.DisciplinaryAdapter;
import com.nada.app.pojo.PanelPOJO;
import com.nada.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppealPanelActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_appeal_panel);
        ButterKnife.bind(this);
        attachGenericAdapter(rv_list);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        panelPOJOS.add(new PanelPOJO("13/01/2020\n@2:30 P.M.",
                "Ms. Vibha Dutta Makhija\nDr. Harsh Mahajan\nMr. Vinay Lamba",
                "","Mr. Dharam Raj Yadav\nMr. Chunni Lal\nMr. Arshdeep Singh\nMr. Puneet Kumar"));

        panelPOJOS.add(new PanelPOJO("14/01/2020\n@3:00 P.M.",
                "Mr. Nalin Kohli\nDr. Navin Dang\nMs. Indu Puri",
                "","Ms. Tamanna Singal\nMr. Naveen Dagar"));

        attachGenericAdapter(rv_list);

    }

    List<PanelPOJO> panelPOJOS=new ArrayList<>();

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        DisciplinaryAdapter searchResultAdapter = new DisciplinaryAdapter(this, null, panelPOJOS);
        rv.setAdapter(searchResultAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
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
