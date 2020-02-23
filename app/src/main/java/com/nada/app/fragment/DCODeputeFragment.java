package com.nada.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.activity.MainActivity;
import com.nada.app.adapter.DeputedDCOAdapter;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.pojo.DCODeputeUserPOJO;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DCODeputeFragment extends FragmentController {

    @BindView(R.id.rv_dco)
    RecyclerView rv_dco;
    //    @BindView(R.id.btn_next)
//    Button btn_next;
    @BindView(R.id.tv_selected_city)
    TextView tv_selected_city;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_select_all)
    TextView tv_select_all;

//    CityPOJO dcoCitiesPOJO;

    String city_ids, city_names;

    //    public DCODeputeFragment(CityPOJO dcoCitiesPOJO) {
//        this.dcoCitiesPOJO = dcoCitiesPOJO;
//    }
    public DCODeputeFragment(String city_ids, String city_names) {
        this.city_ids = city_ids;
        this.city_names = city_names;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_depute, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDCONotAvailableFragment();
//            }
//        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        tv_selected_city.setText("SELECTED CITY : " + dcoCitiesPOJO.getName());
        tv_selected_city.setText("SELECTED CITY : " + city_names);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyDCOSelected()) {
                    deputeDCO();
                } else {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please Select DCO");
                }
//                getCheckedDCO();
            }
        });

        tv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < dcoDeputeUserPOJOS.size(); i++) {
                    dcoDeputeUserPOJOS.get(i).setChecked(true);
                }

                deputedDCOAdapter.notifyDataSetChanged();
            }
        });

        attachGenericAdapter(rv_dco);
        getDCODeputList();
    }

    public void openDCONotAvailableFragment() {
//        if (getActivity() instanceof MainActivity) {
//            MainActivity mainActivity = (MainActivity) getActivity();
//            mainActivity.startFragment(R.id.frame_home, new DCONotAvailableFragment(dcoCitiesPOJO.getId()));
//        }
    }

    List<DCODeputeUserPOJO> dcoDeputeUserPOJOS = new ArrayList<>();
    DeputedDCOAdapter deputedDCOAdapter;

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        deputedDCOAdapter = new DeputedDCOAdapter(getActivity(), this, dcoDeputeUserPOJOS);
        rv.setAdapter(deputedDCOAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    public void getCheckedDCO() {
        for (int i = 0; i < dcoDeputeUserPOJOS.size(); i++) {
            Log.d(TagUtils.getTag(), "checked at " + i + " :- " + dcoDeputeUserPOJOS.get(i).isChecked());
        }
    }

    public boolean isAnyDCOSelected() {
        for (DCODeputeUserPOJO dcoDeputeUserPOJO : dcoDeputeUserPOJOS) {
            if (dcoDeputeUserPOJO.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public void getDCODeputList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("city_ids", city_ids);
//        Log.d(TagUtils.getTag(), "city_id:-" + dcoCitiesPOJO.getId());
        requestParams.put("request_type", "api");
        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                dcoDeputeUserPOJOS.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("city_dco");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DCODeputeUserPOJO dcoDeputeUserPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), DCODeputeUserPOJO.class);
                            dcoDeputeUserPOJOS.add(dcoDeputeUserPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deputedDCOAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "GET_DCO_BY_CITY_IDS").requestAPICall(Webserviceurls.GET_DCO_BY_CITY_IDS, requestParams);
    }


    public void deputeDCO() {

        List<DCODeputeUserPOJO> dcoDeputeUserPOJOList = new ArrayList<>();
        for (DCODeputeUserPOJO dcoDeputeUserPOJO : dcoDeputeUserPOJOS) {
            if (dcoDeputeUserPOJO.isChecked()) {
                dcoDeputeUserPOJOList.add(dcoDeputeUserPOJO);
            }
        }

        RequestParams requestParams = new RequestParams();
        requestParams.put("city_id", city_ids);

        requestParams.put("request_type", "api");
        requestParams.put("depute_dco", "1");
        for (int i = 0; i < dcoDeputeUserPOJOList.size(); i++) {
            requestParams.put("depute_id[" + i + "]", dcoDeputeUserPOJOList.get(i).getId());
        }
        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                activityManager.dismissProgressBar();
                dcoDeputeUserPOJOS.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("city_rtpdata");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DCODeputeUserPOJO dcoDeputeUserPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), DCODeputeUserPOJO.class);
                            if (dcoDeputeUserPOJO.getDeputeOnOff().equalsIgnoreCase("0")) {
                                dcoDeputeUserPOJOS.add(dcoDeputeUserPOJO);
                            }
                        }
                        ToastClass.showShortToast(getActivity().getApplicationContext(), "Dco deputed");
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deputedDCOAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "SAVE_DCO").requestAPICall(Webserviceurls.SAVE_DCO_DEPUTE, requestParams);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finishAffinity();
    }
}
