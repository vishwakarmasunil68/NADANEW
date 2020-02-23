package com.nada.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loopj.android.http.RequestParams;
import com.nada.app.R;
import com.nada.app.activity.MainActivity;
import com.nada.app.fragmentcontroller.FragmentController;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.ToastClass;
import com.nada.app.utils.UtilityFunction;
import com.nada.app.utils.Webserviceurls;
import com.nada.app.webservice.APICallback;
import com.nada.app.webservice.ApiCallBase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class DCOWhereAboutFragment extends FragmentController {

//    @BindView(R.id.btn_next)
//    Button btn_next;

    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;
    @BindView(R.id.btn_update)
    Button btn_update;

//
//    DCODeputeUserPOJO dcoDeputeUserPOJO;
//
//    public DCOWhereAboutFragment(DCODeputeUserPOJO dcoDeputeUserPOJO) {
//        this.dcoDeputeUserPOJO = dcoDeputeUserPOJO;
//    }


    List<Calendar> calendars = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dco_whereabout, container, false);
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

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int position = checkDateExistInList(date.getCalendar());
                Log.d(TagUtils.getTag(), "position:-" + position);
                Log.d(TagUtils.getTag(),"day of year:-"+date.getCalendar().get(Calendar.DAY_OF_YEAR));
                if (position == -1) {
                    calendars.add(date.getCalendar());
                } else {
//                    calendars.remove(position);
                }
                setDateSelected(position);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDetailDialog();
                if (calendarView.getSelectedDates().size() > 0) {
                    dcoWhereAbout(checkValidity());
                } else {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please select date");
                }
            }
        });
    }

    public int checkDateExistInList(Calendar cal1) {
        int position = -1;
        for (int i = 0; i < calendars.size(); i++) {
            Calendar cal2 = calendars.get(i);
//            if (calendar1.getTimeInMillis() == calendar.getTimeInMillis()) {
//                position = i;
//            }

            if(cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)){
                position=i;
            }
        }

        return position;
    }

    public List<String> checkValidity() {
        List<String> dates = new ArrayList<>();
        if (calendarView.getSelectedDates().size() > 0) {
            for (CalendarDay calendarDay : calendarView.getSelectedDates()) {
                dates.add(calendarDay.getYear() + "-" + (calendarDay.getMonth() + 1) + "-" + calendarDay.getDay());
            }
        }
        return dates;
    }

    public void setDateSelected(int position) {
        if(position==-1){
            for (Calendar calendar : calendars) {
                calendarView.setDateSelected(calendar, true);
            }
        }else{
            for (int i=0;i<calendars.size();i++) {
                if(i==position) {
                    calendarView.setDateSelected(calendars.get(i), false);
                }else{
                    calendarView.setDateSelected(calendars.get(i), true);
                }
            }
            calendars.remove(position);
        }

    }

    public void dcoWhereAbout(List<String> dates) {

        RequestParams requestParams = new RequestParams();
        requestParams.put("city", UtilityFunction.getUserPOJO(getActivity()).getUserCity());
        requestParams.put("request_type", "api");
        requestParams.put("add_user", "1");
        requestParams.put("time", UtilityFunction.getCurrentTime());
        requestParams.put("user_id", UtilityFunction.getUserPOJO(getActivity()).getUserId());
        for (int i = 0; i < dates.size(); i++) {
            if (i == 0) {
                requestParams.put("booking_date", dates.get(i) + " 12:00:05");
            } else {
                requestParams.put("booking_date_" + i, dates.get(i) + " 12:00:05");
            }
        }

        Log.d(TagUtils.getTag(), "booking dates:-" + dates.toString());

        activityManager.showProgressBar();

        new ApiCallBase(getActivity(), new APICallback() {
            @Override
            public void onGetMsg(String apicall, String response) {
                activityManager.dismissProgressBar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equalsIgnoreCase("success")) {
                        ToastClass.showShortToast(getActivity().getApplicationContext(), "Booking done");
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finishAffinity();
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
        }, "DCO_WHEREABOUT").requestAPICall(Webserviceurls.DCO_WHEREABOUT, requestParams);
    }

    public void showDetailDialog(String dates) {
        final Dialog dialog1 = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog1.setCancelable(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setContentView(R.layout.dialog_dco_whereabout);
        dialog1.show();
        dialog1.setCancelable(true);
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        Button btn_close = dialog1.findViewById(R.id.btn_close);
        TextView tv_dates = dialog1.findViewById(R.id.tv_dates);

        tv_dates.setText(dates);

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
