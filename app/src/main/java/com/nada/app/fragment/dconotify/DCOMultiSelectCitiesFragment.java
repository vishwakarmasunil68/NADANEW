package com.nada.app.fragment.dconotify;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.nada.app.adapter.CitySelectAdapter;
import com.nada.app.fragment.DCODeputeFragment;
import com.nada.app.fragment.DCONotifyFragment;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.listeners.ItemClickListener;
import com.nada.app.pojo.CityPOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DCOMultiSelectCitiesFragment extends FragmentController {

    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.rv_cities)
    RecyclerView rv_cities;
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.et_city)
    EditText et_city;
    @BindView(R.id.tv_select_all)
    TextView tv_select_all;
    boolean is_deputed;

    public DCOMultiSelectCitiesFragment(boolean is_deputed) {
        this.is_deputed = is_deputed;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_multi_select, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getselectedCities();
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_city.getText().toString().length() > 1) {
                    searchCityPOJOS.clear();
                    for (CityPOJO cityPOJO : masterCityPOJOS) {
                        if (cityPOJO.getName().toLowerCase().contains(et_city.getText().toString().toLowerCase())) {
                            searchCityPOJOS.add(cityPOJO);
                        }
                    }
                    citySelectAdapter.notifyDataSetChanged();
                } else {
                    searchCityPOJOS.addAll(masterCityPOJOS);
                    new CountDownTimer(200, 200) {

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            citySelectAdapter.notifyDataSetChanged();
                        }
                    }.start();
                }
            }
        });
        attachGenericAdapter(rv_cities);
        getCityList();
        tv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CityPOJO cityPOJO : masterCityPOJOS) {
                    cityPOJO.setChecked(true);
                }
                for (CityPOJO cityPOJO : searchCityPOJOS) {
                    cityPOJO.setChecked(true);
                }
                citySelectAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getselectedCities() {
        if (is_citySelected()) {
            String cities = "";
            String cityNames = "";
            for (CityPOJO cityPOJO : masterCityPOJOS) {
                if (cityPOJO.isChecked()) {
//                    Log.d(TagUtils.getTag(), "city_id:-" + cityPOJO.getId() + " , name:-" + cityPOJO.getName());
                    if (cities.length() == 0) {
                        cities = cityPOJO.getId();
                        cityNames = cityPOJO.getName();
                    } else {
                        cities += "," + cityPOJO.getId();
                        cityNames += "," + cityPOJO.getName();
                    }
                }
            }
            if (getActivity() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) getActivity();
//            mainActivity.startFragment(R.id.frame_home, new DCODeputeFragment(cityPOJOS.get(spinner_city.getSelectedItemPosition())));
                if(is_deputed) {
                    mainActivity.startFragment(R.id.frame_home, new DCODeputeFragment(cities, cityNames));
                }else{
                    mainActivity.startFragment(R.id.frame_home, new DCONotifyFragment(cities, cityNames));
                }
            }
        } else {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select city");
        }
    }

    public boolean is_citySelected() {
        for (CityPOJO cityPOJO : masterCityPOJOS) {
            if (cityPOJO.isChecked()) {
                return true;
            }
        }

        return false;
    }

    public void getCityList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        showProgressBar();
        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                dismissProgressBar();
                masterCityPOJOS.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            CityPOJO cityPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), CityPOJO.class);
                            masterCityPOJOS.add(cityPOJO);
                            searchCityPOJOS.add(cityPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                citySelectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
//                dismissProgressBar();
            }
        }, "GET_CITIES").requestAPICall(Webserviceurls.GET_CITIES, requestParams);
    }

    List<CityPOJO> masterCityPOJOS = new ArrayList<>();
    List<CityPOJO> searchCityPOJOS = new ArrayList<>();
    CitySelectAdapter citySelectAdapter;

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        citySelectAdapter = new CitySelectAdapter(getActivity(), this, searchCityPOJOS);
        rv.setAdapter(citySelectAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
        citySelectAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                for (int i = 0; i < masterCityPOJOS.size(); i++) {
                    if (searchCityPOJOS.get(position).getId().equals(masterCityPOJOS.get(i).getId())) {
                        masterCityPOJOS.get(i).setChecked(searchCityPOJOS.get(position).isChecked());
                    }
                }
            }
        });
    }

}
