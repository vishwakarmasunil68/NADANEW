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

import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;

public class MedicineCheckFragment extends FragmentController {

    @BindView(R.id.btn_check)
    Button btn_check;
    @BindView(R.id.et_medicine)
    EditText et_medicine;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_medicine_check, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_medicine.getText().toString().length() == 0) {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please enter medicine name");
                    return;
                }
                searchMedicine();
            }
        });
    }

    public void searchMedicine() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("request_type", "api");
        requestParams.put("search", et_medicine.getText().toString());
        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        JSONArray jsonArray=jsonObject.optJSONObject("data").optJSONArray("prohi_data");
                        if(jsonArray.length()>0){
                            showDetailDialog(jsonArray.optJSONObject(0).optString("p_medicine_name"));
                        }else{
                            ToastClass.showShortToast(getActivity().getApplicationContext(), "Medicine not found in prohibited list");
                        }
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
        }, "SEARCH_MEDICINE").requestAPICall(Webserviceurls.SEARCH_MEDICINE, requestParams);
    }

    public void showDetailDialog(String medicine_name) {
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

        tv_status.setText(medicine_name+" is prohibited");

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
