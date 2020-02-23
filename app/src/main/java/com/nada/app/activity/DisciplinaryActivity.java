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
import com.nada.app.R;
import com.nada.app.pojo.PanelPOJO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisciplinaryActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_disciplinary);
        ButterKnife.bind(this);
        attachGenericAdapter(rv_list);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        panelPOJOArrayList.add(new PanelPOJO("25/11/2019\n@3:00 P.M.",
                "Mr. Vineet Dhanda\nDr. Rana Chengappa\nMs. Poonam Chopra",
                "","Ms. Poonam Pandey\nMs. Seema"));

        panelPOJOArrayList.add(new PanelPOJO("26/11/2019\n@3:00 P.M.",
                "Mr. Vineet Dhanda\nDr. PSM Chandran\nMr. Jagbir Singh",
                "","Mr. Deepak"));

        panelPOJOArrayList.add(new PanelPOJO("27/11/2019\n@3:00 P.M.",
                "Ms. Charu Pragya\nDr. Sanjeev Kumar\nMr. Jagbir Singh",
                "","Mr. Sunil Kumar"));

        panelPOJOArrayList.add(new PanelPOJO("28/11/2019\n@3:00 P.M.",
                "Mr. Vineet Dhanda\nDr. PSM Chandran\nMr. Jagbir Singh",
                "","Mr. Mukul Sharma"));

        panelPOJOArrayList.add(new PanelPOJO("29/11/2019\n@3:00 P.M.",
                "Mr. Sunny Choudhary\nDr. R. Chengappa\nMs. Poonam Chopra",
                "","Mr. Ravi Shooting"));

        attachGenericAdapter(rv_list);

    }

    List<PanelPOJO> panelPOJOArrayList =new ArrayList<>();

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        DisciplinaryAdapter searchResultAdapter = new DisciplinaryAdapter(this, null, panelPOJOArrayList);
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
