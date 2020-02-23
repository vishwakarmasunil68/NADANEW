package com.nada.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.adapter.DCONotAvailable;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.pojo.DCODeputeUserPOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DCONotAvailableFragment extends FragmentController {

    @BindView(R.id.rv_dco)
    RecyclerView rv_dco;
    @BindView(R.id.btn_next)
    Button btn_next;

    String city_id;

    public DCONotAvailableFragment(String city_id) {
        this.city_id = city_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_not_available, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        attachGenericAdapter(rv_dco);
        getDCOList();
    }

//
    List<DCODeputeUserPOJO> dcoDeputeUserPOJOS=new ArrayList<>();
    DCONotAvailable searchResultAdapter;

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        searchResultAdapter = new DCONotAvailable(getActivity(), this, dcoDeputeUserPOJOS);
        rv.setAdapter(searchResultAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    public void getDCOList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("city_id", city_id);

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
                        JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("dop_availability");
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
                searchResultAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "DCO_NOT_AVAILABLE").requestAPICall(Webserviceurls.DCO_NOT_AVAILABLE, requestParams);
    }


}
