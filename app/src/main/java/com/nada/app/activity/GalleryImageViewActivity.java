package com.nada.app.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.nada.app.R;
import com.nada.app.utils.Webserviceurls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryImageViewActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    SubsamplingScaleImageView imageView;

    @BindView(R.id.ic_back)
    ImageView ic_back;

    String server_file_name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_gallery_image_view);
        ButterKnife.bind(this);

        server_file_name = Webserviceurls.GALLERY_BASE_URL+getIntent().getStringExtra("image");

//        Picasso.get()
//                .load(server_file_name)
//                .placeholder(R.drawable.ic_gallery_icon)
//                .error(R.drawable.ic_gallery_icon)
//                .into(imageView);

//        Glide.with(this)
//                .load(server_file_name)
//                .placeholder(R.drawable.ic_gallery_icon)
//                .error(R.drawable.ic_gallery_icon)
//                .dontAnimate()
//                .into(imageView);

        imageView.setImage(ImageSource.uri(server_file_name));

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
