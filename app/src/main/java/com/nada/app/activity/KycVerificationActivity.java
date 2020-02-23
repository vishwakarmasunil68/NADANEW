package com.nada.app.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nada.app.utils.ToastClass;
import com.nada.app.R;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KycVerificationActivity extends AppCompatActivity {

    @BindView(R.id.ll_photograph)
    LinearLayout ll_photograph;
    @BindView(R.id.ll_aadhar)
    LinearLayout ll_aadhar;
    @BindView(R.id.btn_start)
    Button btn_start;

    boolean aadhar_selected=false;
    boolean photograph_selected=false;


    boolean is_aadhar=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_kyc_verification);
        ButterKnife.bind(this);

        ll_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_aadhar=true;
                openPicker();
            }
        });

        ll_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_aadhar=false;
                openPicker();
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!photograph_selected){
                    ToastClass.showShortToast(getApplicationContext(),"Please Select Photograph");
                    return;
                }

                if(!aadhar_selected){
                    ToastClass.showShortToast(getApplicationContext(),"Please Select Aadhar Card");
                    return;
                }

                updateKycImages();
            }
        });
    }

    public void updateKycImages() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        new CountDownTimer(8000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                ToastClass.showShortToast(getApplicationContext(), "KYC Done");
            }
        }.start();

        try {
//            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//            reqEntity.addPart("user_id", new StringBody(UtilityFunction.getUserPOJO(getApplicationContext()).getUser_id()));
//            reqEntity.addPart(part_type, new FileBody(new File(image_path)));
//
////            showProgressBar();
//            new WebUploadService(reqEntity, getActivity(), null, new WebServicesCallBack() {
//                @Override
//                public void onGetMsg(String apicall, String response) {
//                    activityManager.dismissProgressBar();
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        if (jsonObject.optString("status").equals("1")) {
//                            JSONObject resultObject = jsonObject.optJSONObject("result");
//                            DevicePOJO devicePOJO = new Gson().fromJson(resultObject.toString(), DevicePOJO.class);
//                            DeviceEditFragment.this.devicePOJO = devicePOJO;
//                            for (DevicePOJO devicePOJO1 : Constants.devicePOJOS) {
//                                if (devicePOJO.getImei().equals(devicePOJO1.getImei())) {
//                                    devicePOJO1.getDeviceDetailPOJO().setIcon(devicePOJO.getDeviceDetailPOJO().getIcon());
//                                }
//                            }
//                        }
//                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, "UPDATE PROFILE PIC", false).execute(WebServicesUrls.UPDATE_OBJECT_IMAGE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            //Your Code
            if(mPaths.size()>0){
                if(new File(mPaths.get(0)).exists()){
                    if(is_aadhar){
                        aadhar_selected=true;
                    }else{
                        photograph_selected=true;
                    }
                }
            }
        }
    }

    public void openPicker() {
        new ImagePicker.Builder(KycVerificationActivity.this)
                .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.PNG)
                .scale(600, 600)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
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
