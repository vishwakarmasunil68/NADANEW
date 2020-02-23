package com.nada.app.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.fragmentcontroller.ActivityManager;
import com.nada.app.pojo.CityPOJO;
import com.nada.app.pojo.DisciplinePOJO;
import com.nada.app.pojo.SportPOJO;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends ActivityManager implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_back_signin)
    TextView tv_back_signin;
    @BindView(R.id.ll_register)
    LinearLayout ll_register;

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_dob)
    EditText et_dob;
    @BindView(R.id.spinner_sports)
    Spinner spinner_sports;
    @BindView(R.id.spinner_disciplinary)
    Spinner spinner_disciplinary;
    @BindView(R.id.spinner_cities)
    SearchableSpinner spinner_cities;

    Map<String, List<String>> stringListMap = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        tv_back_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ll_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI();
            }
        });

        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                stringListMap.putAll(UtilityFunction.addSportList());
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                updateDropDowns();
////                postSportDiscipline();
//            }
//        }.execute();

        getSportList();

        getCityList();

        spinner_sports.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TagUtils.getTag(), "item selected:-" + sportPOJOS.get(position).getName());
                getDiscipline();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TagUtils.getTag(), "item selected:-" + cityPOJOS.get(position).getName());
                Log.d(TagUtils.getTag(), "Selected position:-" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void openDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        dpd.show(getSupportFragmentManager(), "Date Picker");
    }

    List<SportPOJO> sportPOJOS = new ArrayList<>();
    List<String> sportStringList = new ArrayList<>();

    public void getSportList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        showProgressBar();
        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                dismissProgressBar();
                sportPOJOS.clear();
                sportStringList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            SportPOJO sportPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), SportPOJO.class);
                            sportStringList.add(sportPOJO.getName());
                            sportPOJOS.add(sportPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attachSportAdapter();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                dismissProgressBar();
            }
        }, "GET_SPORTS").requestAPICall(Webserviceurls.GET_SPORTS, requestParams);
    }

    List<DisciplinePOJO> disciplinePOJOS = new ArrayList<>();
    List<String> disciplineStrings = new ArrayList<>();

    public void getDiscipline() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        requestParams.put("sports_id", sportPOJOS.get(spinner_sports.getSelectedItemPosition()).getId());
        showProgressBar();
        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                dismissProgressBar();
                disciplinePOJOS.clear();
                disciplineStrings.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DisciplinePOJO disciplinePOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), DisciplinePOJO.class);
                            disciplinePOJOS.add(disciplinePOJO);
                            disciplineStrings.add(disciplinePOJO.getName());
                        }
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attachDisciplineAdapter();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                dismissProgressBar();
            }
        }, "GET_DISCIPLINE").requestAPICall(Webserviceurls.GET_DISCIPLINE, requestParams);
    }


    List<CityPOJO> cityPOJOS = new ArrayList<>();
    List<String> cities = new ArrayList<>();

    public void getCityList() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
//        showProgressBar();
        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
//                dismissProgressBar();
                cityPOJOS.clear();
                cities.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            CityPOJO cityPOJO = new Gson().fromJson(jsonArray.optJSONObject(i).toString(), CityPOJO.class);
                            cities.add(cityPOJO.getName());
                            cityPOJOS.add(cityPOJO);
                        }
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attachCityAdapter();
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
//                dismissProgressBar();
            }
        }, "GET_CITIES").requestAPICall(Webserviceurls.GET_CITIES, requestParams);
    }

    public void attachCityAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cities.setAdapter(arrayAdapter);
    }

    public void attachSportAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sportStringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sports.setAdapter(arrayAdapter);
    }

    public void attachDisciplineAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, disciplineStrings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_disciplinary.setAdapter(arrayAdapter);
    }

    public void updateDropDowns() {

        List<String> keys = new ArrayList<String>(stringListMap.keySet());

        Log.d(TagUtils.getTag(), "keys:-" + keys.toString());

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        keys); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        spinner_sports.setAdapter(spinnerArrayAdapter);

        spinner_sports.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TagUtils.getTag(), "position selected:-" + position);
                updateDisciplinaryDropDown(stringListMap.get(spinner_sports.getSelectedItem().toString()));
//                if(spinner_sports.getSelectedItem().toString().equalsIgnoreCase("BCO")
//                        ||spinner_sports.getSelectedItem().toString().equalsIgnoreCase("Chaperone")
//                        ||spinner_sports.getSelectedItem().toString().equalsIgnoreCase("DCO")
//                        ||spinner_sports.getSelectedItem().toString().equalsIgnoreCase("Lead DCO")
//                        ||spinner_sports.getSelectedItem().toString().equalsIgnoreCase("NADA OFFICIALS")
//                        ||spinner_sports.getSelectedItem().toString().equalsIgnoreCase("ADMIN")
//                ){
//                    et_dob.setVisibility(View.GONE);
//                }else{
//                    et_dob.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void updateDisciplinaryDropDown(List<String> list) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        list); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        spinner_disciplinary.setAdapter(spinnerArrayAdapter);

        spinner_disciplinary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TagUtils.getTag(), "position selected:-" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void callAPI() {
        if (et_email.getText().toString().length() != 0 && et_email.getText().toString().length() != 0 && et_password.getText().toString().length() != 0) {

            if (et_name.getText().toString().length() == 0) {
                ToastClass.showShortToast(getApplicationContext(), "Please Enter Name");
                return;
            }

            if (et_email.getText().toString().length() == 0) {
                ToastClass.showShortToast(getApplicationContext(), "Please Enter Email");
                return;
            }

            if (et_phone.getText().toString().length() != 10) {
                ToastClass.showShortToast(getApplicationContext(), "Please Enter Correct Mobile Number");
                return;
            }

            if (et_password.getText().toString().length() < 4) {
                ToastClass.showShortToast(getApplicationContext(), "Password length should be greater than 4");
                return;
            }

            if (spinner_sports.getSelectedItemPosition() == 0) {
                ToastClass.showShortToast(getApplicationContext(), "Please select sports");
                return;
            }

            if (et_dob.getText().toString().length() == 0) {
                ToastClass.showShortToast(getApplicationContext(), "Please select DOB");
                return;
            }

            String url = Webserviceurls.SP_REGISTER;
            boolean is_sports = true;


            if (spinner_sports.getSelectedItem().toString().equalsIgnoreCase("BCO")
                    || spinner_sports.getSelectedItem().toString().equalsIgnoreCase("Chaperone")
                    || spinner_sports.getSelectedItem().toString().equalsIgnoreCase("DCO")
                    || spinner_sports.getSelectedItem().toString().equalsIgnoreCase("Lead DCO")
                    || spinner_sports.getSelectedItem().toString().equalsIgnoreCase("NADA OFFICIALS")
            ) {
                is_sports = false;
                url = Webserviceurls.DCO_REGISTER;
            } else if (spinner_sports.getSelectedItem().toString().equalsIgnoreCase("ADMIN")) {
                is_sports = false;
                url = Webserviceurls.ADMIN_REGISTER;
            }


//            if(is_sports){
//
//            }

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("user_name", et_name.getText().toString());
            params.put("user_email", et_email.getText().toString());
            params.put("request_type", "api");
            params.put("add_user", "1");
            params.put("user_phone", et_phone.getText().toString());
            params.put("user_password", et_password.getText().toString());
            params.put("user_dicipline", disciplinePOJOS.get(spinner_disciplinary.getSelectedItemPosition()).getId());
            params.put("user_sports", sportPOJOS.get(spinner_sports.getSelectedItemPosition()).getId());
            params.put("user_city", cityPOJOS.get(spinner_cities.getSelectedItemPosition()).getId());
            Log.d(TagUtils.getTag(), "city_id:-" + cityPOJOS.get(spinner_cities.getSelectedItemPosition()).getId());
            if (is_sports) {
                params.put("user_dob", et_dob.getText().toString());
            } else {
                params.put("user_dob", et_dob.getText().toString());
            }
            params.put("token", Pref.GetStringPref(getApplicationContext(),Pref.FCM_REGISTRATION_TOKEN,""));

            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    try {
                        String responseString = new String(responseBody);
                        Log.d(TagUtils.getTag(), "response:-" + responseString);
                        JSONObject jsonObject = new JSONObject(responseString);
                        if (jsonObject.optString("response").equalsIgnoreCase("success")) {
//                            Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, true);
//                            Pref.SetStringPref(getApplicationContext(), StringUtils.LOGIN_DATA, jsonObject.optJSONObject("result").toString());
//                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                            finishAffinity();

//                            Pref.SetStringPref(getApplicationContext(), StringUtils.LOGIN_DATA,jsonObject.optJSONObject("result").toString());
//                            Intent intent=new Intent(RegisterActivity.this, OtpActivity.class);
//                            intent.putExtra("otp",jsonObject.optJSONObject("result").optString("otp"));
//                            startActivity(intent);

                            ToastClass.showShortToast(getApplicationContext(), "User Registered");
                            Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, true);
                            Pref.SetStringPref(getApplicationContext(), StringUtils.LOGIN_DATA, jsonObject.optJSONObject("data").toString());
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finishAffinity();

                        } else {
                            ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    ToastClass.showShortToast(getApplicationContext(), "Network error");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Email and Password Correctly", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String selectedMonth = String.valueOf((monthOfYear + 1));
        if ((monthOfYear + 1) < 10) {
            selectedMonth = "0" + (monthOfYear + 1);
        }

        String selectedDay = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) {
            selectedDay = "0" + String.valueOf(dayOfMonth);
        }

//        String date = selectedDay + "-" + selectedMonth + "-" + year;
        String date = year + "-" + selectedMonth + "-" + selectedDay;
        et_dob.setText(date);
    }

    public void postSportDiscipline() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        List<String> keys = new ArrayList<String>(stringListMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            requestParams.put("sport[" + i + "][0]", keys.get(i));
            List<String> discipline = stringListMap.get(keys.get(i));
            for (int j = 0; j < discipline.size(); j++) {
                requestParams.put("sport[" + i + "][" + (j + 1) + "]", discipline.get(j));
            }
        }


        showProgressBar();

        new ApiCallBase(this, new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                dismissProgressBar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        ToastClass.showShortToast(getApplicationContext(), "DCO Deputed");
                    } else {
                        ToastClass.showShortToast(getApplicationContext(), jsonObject.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorMsg(String apicall, String error) {
                dismissProgressBar();
            }
        }, "SAVE_SPORTS_DISCIPLINE").requestAPICall(Webserviceurls.SAVE_SPORTS_DISCIPLINE, requestParams);
    }
}
