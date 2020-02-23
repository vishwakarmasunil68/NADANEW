package com.nada.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.adapter.DCONotifyAdapter;
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

public class DCONotifyFragment extends FragmentController {

    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.spinner_state)
    Spinner spinner_state;
    @BindView(R.id.spinner_city)
    Spinner spinner_city;
    @BindView(R.id.spinner_dco)
    Spinner spinner_dco;
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.et_city)
    EditText et_city;
    @BindView(R.id.rv_dco)
    RecyclerView rv_dco;
    @BindView(R.id.tv_select_all)
    TextView tv_select_all;

    String city_ids;
    String city_names;

    public DCONotifyFragment(String city_ids, String city_names) {
        this.city_ids = city_ids;
        this.city_names = city_names;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_notify, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (spinner_city.getSelectedItemPosition() <= 0) {
//                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select city");
//                }
//                if (spinner_dco.getSelectedItemPosition() <= 0) {
//                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select DCO");
//                }
//                if (et_city.getText().toString().length() == 0) {
//                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter message");
//                }

                List<DCODeputeUserPOJO> dcoDeputeUserPOJOList = validateDCO();
                if (dcoDeputeUserPOJOList.size() > 0) {
                    showDetailDialog(dcoDeputeUserPOJOList);
                } else {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select dco");
                }
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        attachGenericAdapter(rv_dco);
        getAllDCOList();

        tv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < dcoDeputeUserPOJOS.size(); i++) {
                    dcoDeputeUserPOJOS.get(i).setChecked(true);
                }
            }
        });

//        getStateList();

//        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position!=0) {
//                    getCityList(statePOJOS.get(position).getId());
//                }else{
//                    cityPOJOS.clear();
//                    cities.clear();
//                    attachCityAdapter();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    getDCOInCities(cityPOJOS.get(position).getId());
//                }else{
//                    dcoDeputeUserPOJOS.clear();
//                    dcoInCityList.clear();
//                    attachDCOINCity();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    public List<DCODeputeUserPOJO> validateDCO() {
        List<DCODeputeUserPOJO> dcoDeputeUserPOJOList = new ArrayList<>();
        for (DCODeputeUserPOJO dcoDeputeUserPOJO : dcoDeputeUserPOJOS) {
            if (dcoDeputeUserPOJO.isChecked()) {
                dcoDeputeUserPOJOList.add(dcoDeputeUserPOJO);
            }
        }
        return dcoDeputeUserPOJOList;
    }

    public void showDetailDialog(List<DCODeputeUserPOJO> dcoDeputeUserPOJOS) {
        final Dialog dialog1 = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog1.setCancelable(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setContentView(R.layout.dialog_dco_notify);
        dialog1.show();
        dialog1.setCancelable(true);
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_send = dialog1.findViewById(R.id.btn_send);
        EditText et_message = dialog1.findViewById(R.id.et_message);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_message.getText().toString().length() > 0) {
                    callAPI(dcoDeputeUserPOJOS);
                    dialog1.dismiss();
                }
            }
        });

        dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
    }


    public void callAPI(List<DCODeputeUserPOJO> dcoDeputeUserPOJOS) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        requestParams.put("dconotify_msg", "1");
        requestParams.put("city_id", city_ids);
        for (int i = 0; i < dcoDeputeUserPOJOS.size(); i++) {
            requestParams.put("notify_user_id[" + i + "]", dcoDeputeUserPOJOS.get(i).getUserId());
        }
        requestParams.put("notification_message", et_city.getText().toString());
        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), "DCO Notified");
                        onBackPressed();
                    } else {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "NOTIFY_DCO").requestAPICall(Webserviceurls.NOTIFY_DCO, requestParams);
    }
//
//    public void attachStateAdapter() {
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stateList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_state.setAdapter(arrayAdapter);
//    }
//
//    public void attachCityAdapter() {
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cities);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_city.setAdapter(arrayAdapter);
//    }
//
//    List<String> stateList = new ArrayList<>();
//    List<StatePOJO> statePOJOS = new ArrayList<>();
//
//    public void getStateList() {
//        RequestParams requestParams = new RequestParams();
//        requestParams.put("request_type", "api");
//
//        new ApiCallBase(getActivity(), new APICallback() {
//            @Override
//            public void onGetMsg(String apicall, String response) {
//                statePOJOS.clear();
//                stateList.clear();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
//                        JSONArray jsonArray = jsonObject.optJSONArray("data");
//                        setFirstState();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            StatePOJO statePOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), StatePOJO.class);
//                            stateList.add(statePOJO.getName());
//                            statePOJOS.add(statePOJO);
//                        }
//                    } else {
//                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                attachStateAdapter();
//            }
//
//            @Override
//            public void onErrorMsg(String apicall, String error) {
//
//            }
//        }, "GET_STATES").requestAPICall(Webserviceurls.STATE_LIST, requestParams);
//    }
//
//    public void setFirstState(){
//        StatePOJO statePOJO=new StatePOJO();
//        statePOJOS.add(statePOJO);
//        stateList.add("Select State");
//    }
//
//    public void setFirstCity(){
//        CityPOJO statePOJO=new CityPOJO();
//        cityPOJOS.add(statePOJO);
//        cities.add("Select City");
//    }
//
//    List<CityPOJO> cityPOJOS=new ArrayList<>();
//    List<String> cities=new ArrayList<>();
//
//    public void getCityList(String state_id) {
//        RequestParams requestParams = new RequestParams();
//        requestParams.put("request_type", "api");
//        requestParams.put("state_id", state_id);
//
//        new ApiCallBase(getActivity(), new APICallback() {
//            @Override
//            public void onGetMsg(String apicall, String response) {
//                cityPOJOS.clear();
//                cities.clear();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
//                        JSONArray jsonArray = jsonObject.optJSONArray("data");
//                        setFirstCity();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            CityPOJO cityPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), CityPOJO.class);
//                            cities.add(cityPOJO.getName());
//                            cityPOJOS.add(cityPOJO);
//                        }
//                    } else {
//                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                attachCityAdapter();
//            }
//
//            @Override
//            public void onErrorMsg(String apicall, String error) {
//
//            }
//        }, "GET_CITIES").requestAPICall(Webserviceurls.CITY_LIST, requestParams);
//    }
//
//    List<DCODeputeUserPOJO> dcoDeputeUserPOJOS = new ArrayList<>();
//    List<String> dcoInCityList = new ArrayList<>();
//    List<KeyPairBoolData> dcoKeyPairBoolData = new ArrayList<>();
//
//    public void getDCOInCities(String city_id) {
//        RequestParams requestParams = new RequestParams();
//        requestParams.put("city_id", city_id);
//
//        requestParams.put("request_type", "api");
//        activityManager.showProgressBar();
//
//        new ApiCallBase(getActivity(), new APICallback() {
//            @Override
//            public void onGetMsg(String apicall, String response) {
//                activityManager.dismissProgressBar();
//                dcoDeputeUserPOJOS.clear();
//                dcoInCityList.clear();
//                try {
////                    getFirstDCO();
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
//                        JSONArray jsonArray = jsonObject.optJSONArray("data");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            DCODeputeUserPOJO dcoDeputeUserPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), DCODeputeUserPOJO.class);
//                            dcoDeputeUserPOJOS.add(dcoDeputeUserPOJO);
//                            dcoInCityList.add(dcoDeputeUserPOJO.getUserName());
//                        }
//                    } else {
//                        ToastClass.showShortToast(getActivity().getApplicationContext(), jsonObject.optString("message"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                attachDCOINCity();
//            }
//
//            @Override
//            public void onErrorMsg(String apicall, String error) {
//                activityManager.dismissProgressBar();
//            }
//        }, "GET_DCO_BY_CITY").requestAPICall(Webserviceurls.GET_DCO_BY_CITY, requestParams);
//    }

//    public void addDCOInSpinner(){
//        for (int i = 0; i < dcoDeputeUserPOJOS.size(); i++) {
//            KeyPairBoolData h = new KeyPairBoolData();
//            h.setId(i + 1);
//            h.setName(dcoDeputeUserPOJOS.get(i).getUserName());
//            h.setSelected(false);
//            dcoKeyPairBoolData.add(h);
//        }
//
//        spinner_dco.setItems(dcoKeyPairBoolData, -1, new SpinnerListener() {
//
//            @Override
//            public void onItemsSelected(List<KeyPairBoolData> items) {
//
//                for (int i = 0; i < items.size(); i++) {
//                    if (items.get(i).isSelected()) {
//                        Log.i(TagUtils.getTag(), i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
//                    }
//                }
//            }
//        });
//    }

//    public void getFirstDCO() {
//        DCODeputeUserPOJO dcoCitiesPOJO = new DCODeputeUserPOJO();
//        dcoDeputeUserPOJOS.add(dcoCitiesPOJO);
//        dcoInCityList.add("Select DCO");
//    }
//
//    public void attachDCOINCity() {
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dcoInCityList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_dco.setAdapter(arrayAdapter);
//    }

    List<DCODeputeUserPOJO> dcoDeputeUserPOJOS = new ArrayList<>();

    public void getAllDCOList() {
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
                dcoNotifyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                activityManager.dismissProgressBar();
            }
        }, "GET_DCO_BY_CITY_IDS").requestAPICall(Webserviceurls.GET_DCO_BY_CITY_IDS, requestParams);
    }

    DCONotifyAdapter dcoNotifyAdapter;

    public void attachGenericAdapter(RecyclerView rv) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        dcoNotifyAdapter = new DCONotifyAdapter(getActivity(), this, dcoDeputeUserPOJOS);
        rv.setAdapter(dcoNotifyAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
    }


}
