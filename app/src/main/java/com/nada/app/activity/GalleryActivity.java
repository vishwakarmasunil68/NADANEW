package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.adapter.GalleryAdapter;
import com.nada.app.R;
import com.nada.app.pojo.GalleryPOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attachGenericAdapter(rv_list);
        getGallery();

    }

    List<GalleryPOJO> galleryPOJOS=new ArrayList<>();

    public void getGallery() {
        RequestParams requestParams = new RequestParams();

        requestParams.put("request_type", "api");

        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                galleryPOJOS.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("gallery_data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            GalleryPOJO galleryPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), GalleryPOJO.class);
                            galleryPOJOS.add(galleryPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                galleryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {

            }
        }, "GET_GALLERIES").requestAPICall(Webserviceurls.GET_GALLERIES, requestParams);
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
//        galleryPOJOS.add(new GalleryPOJO());
    }

    GalleryAdapter galleryAdapter;
    public void attachGenericAdapter(RecyclerView rv) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        galleryAdapter = new GalleryAdapter(this, null, galleryPOJOS);
        rv.setAdapter(galleryAdapter);
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
