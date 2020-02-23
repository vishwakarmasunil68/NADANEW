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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.pojo.TestResultPOJO;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;

public class CheckTestSampleFragment extends FragmentController implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_dot)
    TextView tv_dot;
    @BindView(R.id.tv_dob)
    TextView tv_dob;
    @BindView(R.id.btn_check)
    Button btn_check;
    @BindView(R.id.et_sample_code)
    EditText et_sample_code;

    TextView dateTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_check_test_samples, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTextView = tv_dot;
                openDatePicker();
            }
        });

        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTextView = tv_dob;
                openDatePicker();
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_dot.getText().toString().length() == 0) {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select Date of Test");
                    return;
                }
                if (tv_dob.getText().toString().length() == 0) {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select Date of Birth");
                    return;
                }
                if (et_sample_code.getText().toString().length() == 0) {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter sample code");
                    return;
                }
                searchTest();
            }
        });
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

    public void searchTest() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        requestParams.put("user_dob", tv_dob.getText().toString());
        requestParams.put("date_of_test", tv_dot.getText().toString());
        requestParams.put("sample_code", et_sample_code.getText().toString());
        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONObject testJsonObject = jsonObject.optJSONObject("data").optJSONObject("test_result");

                        TestResultPOJO resultPOJO = new Gson().fromJson(testJsonObject.toString(), TestResultPOJO.class);

                        if (resultPOJO.getSampleResult().equalsIgnoreCase("Negative")) {
                            showDetailDialog();
                        }

//                        if(jsonArray.length()>0){
//                            showDetailDialog(jsonArray.optJSONObject(0).optString("p_medicine_name"));
//                        }else{
//                            ToastClass.showShortToast(getActivity().getApplicationContext(), "Medicine not found in prohibited list");
//                        }

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
        }, "SEARCH_DOPE_TEST").requestAPICall(Webserviceurls.SEARCH_DOPE_TEST, requestParams);
    }

    public void showDetailDialog() {
        final Dialog dialog1 = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog1.setCancelable(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setContentView(R.layout.dialog_medicine_result);
        dialog1.show();
        dialog1.setCancelable(true);
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_close = dialog1.findViewById(R.id.btn_close);
        TextView tv_status = dialog1.findViewById(R.id.tv_status);

        tv_status.setText("Your test result is Negative");

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

        String date = year + "-" + selectedMonth + "-" + selectedDay;
        dateTextView.setText(date);
    }
}
