package com.nada.app.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.adapter.RTPAdapter;
import com.nada.app.fragmentcontroller.ActivityManager;
import com.nada.app.pojo.RTPPOJO;
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

public class RTPActivity extends ActivityManager {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_rtp);
        ButterKnife.bind(this);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addPOJOS();

        attachGenericAdapter(rv_list);
        getRTPUsers();
    }

    public void addPOJOS() {
//        rtps.add(new RTP("1","Swapna Barman","Female","Athletics","Heptathlon"));
//        rtps.add(new RTP("2","Purnima Hembram","Female","Athletics","Heptathlon"));
//        rtps.add(new RTP("3","Boota Singh","Male","Athletics","Decathlon"));
//        rtps.add(new RTP("4","Abhishek N Shetty","Male","Athletics","Decathlon"));
//        rtps.add(new RTP("5","Arpinder Singh","Male","Athletics","Triple Jump"));
//        rtps.add(new RTP("6","Neena V.","Female","Athletics","Long Jump"));
//        rtps.add(new RTP("7","Praveen Chitravel","Male","Athletics","Triple Jump"));
//        rtps.add(new RTP("8","Mohd. Zuber","Male","Athletics","Triple Jump"));
//        rtps.add(new RTP("9","Mohd. Salahuddin","Male","Athletics","Triple Jump"));
//        rtps.add(new RTP("10","Tejaswin Shankar","Male","Athletics","High Jump"));
//        rtps.add(new RTP("11","Silambarasan M","Male","Athletics","Long Jump"));
//        rtps.add(new RTP("12","M Sreeshankar","Male","Athletics","Long Jump"));
//        rtps.add(new RTP("13","Sanjivani Baburao Jadhav","Female","Athletics","10,000 Race"));
//        rtps.add(new RTP("14","Gavit Murali Kuma","Male","Athletics","10000m"));
//        rtps.add(new RTP("15","Sudha Singh","Female","Athletics","3000m"));
//        rtps.add(new RTP("16","Suriya L","Female","Athletics","3000m"));
//        rtps.add(new RTP("17","Avinash Sable","Male","Athletics","3000m"));
//        rtps.add(new RTP("18","Shankar Lal Swami","Male","Athletics","3000m"));
//        rtps.add(new RTP("19","Parul Chaudhary","Female","Athletics","5000m"));
//        rtps.add(new RTP("20","Chinta Yadav","Female","Athletics","3000m"));
//        rtps.add(new RTP("21","Chitra P.U","Female","Athletics","1500m"));
//        rtps.add(new RTP("22","Jinson Johnson","Male","Athletics","800m Run"));
//        rtps.add(new RTP("23","Manjit Singh","Male","Athletics","800m Run"));
//        rtps.add(new RTP("24","Lili Das","Female","Athletics","800m Run"));
//        rtps.add(new RTP("25","Shipra Sarkar","Female","Athletics","800m"));
//        rtps.add(new RTP("26","Mohammed Afsal","Male","Athletics","800m"));
//        rtps.add(new RTP("27","Ajay Kumar Saroj","Male","Athletics","1500m"));
//        rtps.add(new RTP("28","Arokia Rajiv","Male","Athletics","400m"));
//        rtps.add(new RTP("29","Kunhu Muhammed","Male","Athletics","400m"));
//        rtps.add(new RTP("30","Hima Das","Female","Athletics","400m"));
//        rtps.add(new RTP("31","Ayyaswamy Dharun","Male","Athletics","400m Hurdles"));
//        rtps.add(new RTP("32","Muhammed Anas","Male","Athletics","400m"));
//        rtps.add(new RTP("33","Jabir M.P","Male","Athletics","400m Hurdles"));
//        rtps.add(new RTP("34","Poovamma Raju Machettira","Female","Athletics","400m"));
//        rtps.add(new RTP("35","Dutee Chand","Female","Athletics","100m Race"));
//        rtps.add(new RTP("36","Jithin Paul","Male","Athletics","400m"));
//        rtps.add(new RTP("37","Shivpal Singh","Male","Athletics","Javelin Throw"));
//        rtps.add(new RTP("38","Seema Punia","Female","Athletics","Discuss Throw"));
//        rtps.add(new RTP("39","Tejinder Pal Singh Toor","Male","Athletics","Shot Put"));
//        rtps.add(new RTP("40","Navjeet Kaur Dhillon","Female","Athletics","Discus Throw"));
//        rtps.add(new RTP("41","Gagandeep Singh","Male","Athletics","Discus Throw"));
//        rtps.add(new RTP("42","Annu Rani","Female","Athletics","Javelin Throw"));
//        rtps.add(new RTP("43","Davinder Singh Kang","Male","Athletics","Javelin Throw"));
//        rtps.add(new RTP("44","Inderjeet Singh","Male","Athletics","Shot put"));
//        rtps.add(new RTP("45","Deepak Lather","Male","Weightlifting","69 Kg"));
//        rtps.add(new RTP("46","Rv Rahul","Male","Weightlifting","85 Kg"));
//        rtps.add(new RTP("47","Pradeep Singh","Male","Weightlifting","94 Kg"));
//        rtps.add(new RTP("48","Kh. Sanjita Chanu","Female","Weightlifting","53 Kg"));
//        rtps.add(new RTP("49","Punam Yadav","Female","Weightlifting","69 Kg"));
//        rtps.add(new RTP("50","Ajay Singh","Male","Weightlifting","81kg"));
//        rtps.add(new RTP("51","S. Mirabai Chanu","Female","Weightlifting","49kg"));
//        rtps.add(new RTP("52","Soroikhaibam Bindyarani Devi","Female","Weightlifting","55 Kg"));
//        rtps.add(new RTP("53","Jeremy Lalrinnunga","Male","Weightlifting","67kg"));
//        rtps.add(new RTP("54","Amit Panghal","Male","Boxing","49 Kg"));
//        rtps.add(new RTP("55","Manish Kaushik","Male","Boxing","60 Kg"));
//        rtps.add(new RTP("56","Mohd. Hussamuddin","male","Boxing","56 kg"));
//        rtps.add(new RTP("57","Gaurav Solanki","Male","Boxing","52 Kg"));
//        rtps.add(new RTP("58","Simranjit Kaur","Female","Boxing","60 Kg"));
//        rtps.add(new RTP("59","Ashish Kumar","male","Boxing","(75kg)"));
//        rtps.add(new RTP("60","Satish Kumar","male","Boxing","+91 Kg"));
//        rtps.add(new RTP("61","Shiva Thapa","Male","Boxing","60 Kg"));
//        rtps.add(new RTP("62","Sumit Sangwan","Male","Boxing","91kg"));
//        rtps.add(new RTP("63","Vinesh Phogat","Female","Wrestling","50 Kg"));
//        rtps.add(new RTP("64","Deepak Punia","Male","Wrestling","86kg"));
//        rtps.add(new RTP("65","Sakshi Malik","Female","Wrestling","62 Kg"));
//        rtps.add(new RTP("66","Pooja Dhanda,","Female","Wrestling","57kg"));
//        rtps.add(new RTP("67","Bajrang Punia","Male","Wrestling","65kg"));
//        rtps.add(new RTP("68","Rahul Aware","Male","Wrestling","57 Kg"));
//        rtps.add(new RTP("69","Sushil Kumar","Male","Wrestling","74 Kg"));
//        rtps.add(new RTP("70","Sumit Malik","Male","Wrestling","125 Kg"));
//        rtps.add(new RTP("71","Pradeep Narwal","Male","Kabaddi","Kabaddi"));
//        rtps.add(new RTP("72","Rohit Kumar","Male","Kabaddi","Kabaddi"));
//        rtps.add(new RTP("73","Ajay Thakur","Male","Kabaddi","Kabaddi"));
//        rtps.add(new RTP("74","Shamsher Singh","Male","Kabaddi","Kabaddi"));
//        rtps.add(new RTP("75","Dharmaraj Cheralathan","Male","Kabaddi","Kabaddi"));
//        rtps.add(new RTP("76","Cheteshwar Pujara","Male","Cricket","Cricket"));
//        rtps.add(new RTP("77","Ravindra Jadeja","Male","Cricket","Cricket"));
//        rtps.add(new RTP("78","Lokesh Rahul","Male","Cricket","Cricket"));
//        rtps.add(new RTP("79","Smriti Mandhana","Female","Cricket","Cricket"));
//        rtps.add(new RTP("80","Deepti Sharma","Female","Cricket","Cricket"));
//        rtps.add(new RTP("81","Deepender","Male","Para Shooting","P1 – 10m Air Pistol SH1 (Men)"));
//        rtps.add(new RTP("82","Sachin Chaudhary","Male","Para powerlifting","Para powerlifting"));
//        rtps.add(new RTP("83","Sudhir","Male","Para powerlifting","Para powerlifting"));
//        rtps.add(new RTP("84","Sakina Khatun","Female","Para powerlifting","Para powerlifting"));
//        rtps.add(new RTP("85","Manoj Sarkar","Male","Para badminton","Sl3 - Singles"));
//        rtps.add(new RTP("86","Varun Bhati","Male","Para Athletics (jumps)","Men's High Jump T63 (42, 63)"));
//        rtps.add(new RTP("87","Sharad Kumar","Male","Para Athletics (jumps)","Men's High Jump T63 (42, 63)"));
//        rtps.add(new RTP("88","Sandeep Chaudhary","Male","Para Athletics (throws)","Men's Javelin F64 (42-44, 61-64)"));
//        rtps.add(new RTP("89","Sumit","Male","Para Athletics (throws)","Men's Javelin F64"));
//        rtps.add(new RTP("90","Narayan Thakur","Male","Para Athletics (sprint 400m or less)","100m race"));
//        rtps.add(new RTP("91","Jayanti Behera","Female","Para Athletics (sprint 400m or less)","Women's 400 m T47"));
//        rtps.add(new RTP("92","Sundar Singh Gurjar","Male","Para Athletics (throws)","Men's Javelin F46"));
//        rtps.add(new RTP("93","Yogesh Kathuniya","Male","Para Athletics (jumps)","high jump"));
//        rtps.add(new RTP("94","Anandan Gunasekaran","Male","Para Athlete (Sprint 400m or less)","IPC Sprint 400m or less"));
//        rtps.add(new RTP("95","Niranjan Mukundan","Male","IPC Aquatics","Middle Distance 200-400m"));
//        rtps.add(new RTP("96","Suyash Narayan Jadhav","Male","IPC Aquatics","Middle Distance 200-400m"));
//        rtps.add(new RTP("97","Swapnil Patil","Male","IPC Aquatics","Middle Distance 200-400m"));
//        rtps.add(new RTP("98","Devanshi Satija","Female","IPC Aquatics","Women’s 100M Butterfly S10"));
//        rtps.add(new RTP("99","Srihari Nataraj","Male","IPC Aquatics","100m freestyle and 50m backstroke"));
//        rtps.add(new RTP("100","Sharath Gayakwad","Male","IPC Aquatics","Men's 100 m Butterfly"));
//        rtps.add(new RTP("101","Dattu Baban Bhokanal","Male","Rowing","Rowing"));
//        rtps.add(new RTP("102","Sawarn Singh","Male","Rowing","Rowing"));
//        rtps.add(new RTP("103","Saurabh Sangvekar","Male","Swimming (Long Distance)","800-1500m"));
//        rtps.add(new RTP("104","Advait Page","Male","Swimming (Long Distance)","800-1500m"));
//        rtps.add(new RTP("105","Deborah h","Female","Cycling Track","Track"));
//        rtps.add(new RTP("106","Kiran Kumar Raju","Male","Cycling Mountain Bike","Mountain Bike"));
//        rtps.add(new RTP("107","Arvind Pawar","Male","Cycling Road","Road"));
//        rtps.add(new RTP("108","Samira Abraham","Female","Cycling Road","Road"));
//        rtps.add(new RTP("109","Sunil Singh","Male","Canoe Long distance","1000m"));
//        rtps.add(new RTP("110","Gaurav Tomar","Male","Canoe Long distance","1000m"));

    }

    public void getRTPUsers() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        showProgressBar();
        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                dismissProgressBar();
                rtps.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("rtp_data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            RTPPOJO rtp = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), RTPPOJO.class);
                            rtps.add(rtp);
                        }
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rtpAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                dismissProgressBar();
            }
        }, "GET_RTP_DATA").requestAPICall(Webserviceurls.GET_RTP_DATA, requestParams);
    }

    List<RTPPOJO> rtps = new ArrayList<>();
    RTPAdapter rtpAdapter;

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        rtpAdapter = new RTPAdapter(this, null, rtps);
        rv.setAdapter(rtpAdapter);
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

    public void showDetailDialog(RTPPOJO rtppojo) {
        final Dialog dialog1 = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog1.setCancelable(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setContentView(R.layout.dialog_rtp_item_info);
        dialog1.show();
        dialog1.setCancelable(true);
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        Button btn_close = dialog1.findViewById(R.id.btn_close);

        TextView tv_name = dialog1.findViewById(R.id.tv_name);
        TextView tv_gender = dialog1.findViewById(R.id.tv_gender);
        TextView tv_sports = dialog1.findViewById(R.id.tv_sports);
        TextView tv_discipline = dialog1.findViewById(R.id.tv_discipline);
        TextView tv_address = dialog1.findViewById(R.id.tv_address);
        TextView tv_date = dialog1.findViewById(R.id.tv_date);
        TextView tv_time = dialog1.findViewById(R.id.tv_time);

        tv_name.setText(rtppojo.getUserName());
        if (rtppojo.getUserGender().equalsIgnoreCase("1")) {
            tv_gender.setText("Male");
        } else {
            tv_gender.setText("Female");
        }

        tv_sports.setText(rtppojo.getSportsName());
        tv_discipline.setText(rtppojo.getUserDicipline());
        tv_address.setText(rtppojo.getAddress());
        if (rtppojo.getDatetime() != null) {
            try {
                String date = rtppojo.getDatetime().split(" ")[0];
                String time = rtppojo.getDatetime().split(" ")[1];
                tv_date.setText(date);
                tv_time.setText(time);
            } catch (Exception e) {
                e.printStackTrace();
                tv_date.setText("");
                tv_time.setText("");
            }
        } else {
            tv_date.setText("");
            tv_time.setText("");
        }
//        tv_address.setText(rtppojo.getDatetime());

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
    }
}
