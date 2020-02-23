package com.nada.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.pojo.CityPOJO;
import com.nada.app.pojo.StatePOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.UtilityFunction;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class RTPWhereAboutFragment extends FragmentController implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.et_street_address)
    EditText et_street_address;
    @BindView(R.id.et_colony)
    EditText et_colony;
    @BindView(R.id.et_landmark)
    EditText et_landmark;
    @BindView(R.id.spinner_city)
    Spinner spinner_city;
    @BindView(R.id.spinner_state)
    Spinner spinner_state;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.btn_update)
    Button btn_update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rtp_wherabout, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRTPAPI();
            }
        });

        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    getCityList(statePOJOS.get(position).getId());
                }else{
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

    public void openDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        dpd.show(getFragmentManager(), "Date Picker");
    }

    public void openTimePicker() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.show(getFragmentManager(), "Time Picker");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String selectedMonth=String.valueOf((monthOfYear+1));
        if((monthOfYear+1)<10){
            selectedMonth="0"+(monthOfYear+1);
        }

        String selectedDay=String.valueOf(dayOfMonth);
        if(dayOfMonth<10){
            selectedDay="0"+String.valueOf(dayOfMonth);
        }

        String date = selectedDay + "-" + selectedMonth + "-" + year;
        tv_date.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        String selectedHour=String.valueOf(hourOfDay);
        if(hourOfDay<10){
            selectedHour="0"+hourOfDay;
        }

        String selectedMinute=String.valueOf(minute);
        if(minute<10){
            selectedMinute="0"+String.valueOf(minute);
        }

        String selectedSec=String.valueOf(second);
        if(second<10){
            selectedSec="0"+String.valueOf(second);
        }

        String time = selectedHour + ":" + selectedMinute + ":" + selectedSec;
        tv_time.setText(time);
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
        requestParams.put("", "");

        requestParams.put("request_type", "api");

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
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

            }
        }, "GET_STATES").requestAPICall(Webserviceurls.STATE_LIST, requestParams);
    }

    public void setFirstState(){
        StatePOJO statePOJO=new StatePOJO();
        statePOJOS.add(statePOJO);
        stateList.add("Select State");
    }

    public void setFirstCity(){
        CityPOJO statePOJO=new CityPOJO();
        cityPOJOS.add(statePOJO);
        cities.add("Select City");
    }

    List<CityPOJO> cityPOJOS=new ArrayList<>();
    List<String> cities=new ArrayList<>();

    public void getCityList(String state_id) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("", "");

        requestParams.put("request_type", "api");
        requestParams.put("state_id", state_id);

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
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

            }
        }, "GET_CITIES").requestAPICall(Webserviceurls.CITY_LIST, requestParams);
    }

    public void callRTPAPI() {

        if (tv_date.getText().toString().length() == 0) {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select date");
            return;
        }

        if (tv_time.getText().toString().length() == 0) {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select time");
            return;
        }

        if (et_street_address.getText().toString().length() == 0) {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter address");
            return;
        }

        if (et_colony.getText().toString().length() == 0) {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter colony");
            return;
        }

        if (et_landmark.getText().toString().length() == 0) {
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter colony");
            return;
        }

        if(spinner_state.getSelectedItemPosition()<=0){
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select state");
            return;
        }

        if(spinner_city.getSelectedItemPosition()<=0){
            ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select city");
            return;
        }


        RequestParams requestParams = new RequestParams();
        requestParams.put("", "");

        requestParams.put("request_type", "api");
        requestParams.put("add_rtpwhereabout", "Yes");
        requestParams.put("user_id", UtilityFunction.getUserPOJO(getActivity()).getUserId());
        requestParams.put("address", et_street_address.getText().toString());
        requestParams.put("locality", et_colony.getText().toString());
        requestParams.put("landmark", et_landmark.getText().toString());
        requestParams.put("state", statePOJOS.get(spinner_state.getSelectedItemPosition()).getId());
        requestParams.put("city", cityPOJOS.get(spinner_city.getSelectedItemPosition()).getId());
        requestParams.put("date", UtilityFunction.parseDTtoYYYYMMDD(tv_date.getText().toString()));
        requestParams.put("time", tv_time.getText().toString());

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ToastClass.showShortToast(getActivity().getApplicationContext(),jsonObject.optString("message"));
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        onBackPressed();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorMsg(String apicall, String error) {

            }
        }, "GET_SEARCH_RESULT").requestAPICall(Webserviceurls.SAVE_RTP_WHEREABOUT, requestParams);
    }
}
