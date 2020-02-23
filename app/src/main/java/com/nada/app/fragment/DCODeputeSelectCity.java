package com.nada.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.activity.MainActivity;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.pojo.CityPOJO;
import com.nada.app.pojo.StatePOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DCODeputeSelectCity extends FragmentController {

    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.spinner_city)
    Spinner spinner_city;
    @BindView(R.id.spinner_state)
    Spinner spinner_state;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_depute_city, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (spinner_city.getSelectedItemPosition() != 0) {
//                    openDCODepute();
//                } else {
//                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please Select City");
//                }
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    getCityList(statePOJOS.get(position).getId());
                } else {
                    cityPOJOS.clear();
                    cities.clear();
                    attachCityAdapter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getStateList();
    }

    public void openDCODepute(String city_ids, String city_names) {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
//            mainActivity.startFragment(R.id.frame_home, new DCODeputeFragment(cityPOJOS.get(spinner_city.getSelectedItemPosition())));
//            mainActivity.startFragment(R.id.frame_home, new DCODeputeFragment(city_ids, city_names));
        }
    }

    public void attachStateAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stateList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(arrayAdapter);
    }

    public void attachCityAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cities);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(arrayAdapter);
    }

    List<String> stateList = new ArrayList<>();
    List<StatePOJO> statePOJOS = new ArrayList<>();

    public void getStateList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        activityManager.showProgressBar();
        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                statePOJOS.clear();
                stateList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        setFirstState();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            StatePOJO statePOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), StatePOJO.class);
                            stateList.add(statePOJO.getName());
                            statePOJOS.add(statePOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attachStateAdapter();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "GET_STATES").requestAPICall(Webserviceurls.STATE_LIST, requestParams);
    }

    public void setFirstState() {
        StatePOJO statePOJO = new StatePOJO();
        statePOJOS.add(statePOJO);
        stateList.add("Select State");
    }

    public void setFirstCity() {
        CityPOJO statePOJO = new CityPOJO();
        cityPOJOS.add(statePOJO);
        cities.add("Select City");
    }

    List<CityPOJO> cityPOJOS = new ArrayList<>();
    List<String> cities = new ArrayList<>();

    public void getCityList(String state_id) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("", "");

        requestParams.put("request_type", "api");
        requestParams.put("state_id", state_id);
        activityManager.showProgressBar();
        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                cityPOJOS.clear();
                cities.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        setFirstCity();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            CityPOJO cityPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), CityPOJO.class);
                            cities.add(cityPOJO.getName());
                            cityPOJOS.add(cityPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attachCityAdapter();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "GET_CITIES").requestAPICall(Webserviceurls.CITY_LIST, requestParams);
    }
}
