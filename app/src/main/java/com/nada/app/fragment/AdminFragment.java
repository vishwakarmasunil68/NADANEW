package com.nada.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.nada.app.R;
import com.nada.app.activity.MainActivity;
import com.nada.app.activity.RTPActivity;
import com.nada.app.fragment.dconotify.DCOMultiSelectCitiesFragment;
import com.nada.app.fragmentcontroller.FragmentController;

import butterknife.BindView;

public class AdminFragment extends FragmentController {

    @BindView(R.id.cv_dco_depute)
    CardView cv_dco_depute;
    @BindView(R.id.cv_dco_notify)
    CardView cv_dco_notify;
    @BindView(R.id.cv_rtp_whereabout)
    CardView cv_rtp_whereabout;
    @BindView(R.id.ic_back)
    ImageView ic_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_admin, container, false);
        setUpView(getActivity(), this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_dco_depute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDCODepute();
            }
        });


        cv_dco_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDCONotify();
            }
        });

        cv_rtp_whereabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RTPActivity.class));
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void openDCODepute() {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.startFragment(R.id.frame_home, new DCOMultiSelectCitiesFragment(true));
        }
    }

    public void openDCONotify() {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.startFragment(R.id.frame_home, new DCOMultiSelectCitiesFragment(false));
        }
    }


}
