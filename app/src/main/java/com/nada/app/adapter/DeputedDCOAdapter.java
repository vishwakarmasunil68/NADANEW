package com.nada.app.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.pojo.DCODeputeUserPOJO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeputedDCOAdapter extends RecyclerView.Adapter<DeputedDCOAdapter.ViewHolder> {

    public List<DCODeputeUserPOJO> items;
    Activity activity;
    Fragment fragment;

    public DeputedDCOAdapter(Activity activity, Fragment fragment, List<DCODeputeUserPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_dco_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position % 2 != 0) {
            holder.ll_view.setBackgroundColor(Color.parseColor("#EEEEEE"));
        } else {
            holder.ll_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.tv_so.setText(String.valueOf(position + 1));
        holder.tv_name.setText(items.get(position).getUserName());
        holder.tv_available_time.setText(items.get(position).getDateAvailability());

        if (items.get(position).getDeputeOnOff().equalsIgnoreCase("1")) {
            holder.btn_depute.setVisibility(View.GONE);
        } else {
            holder.btn_depute.setVisibility(View.VISIBLE);
        }

        holder.check_dco.setChecked(items.get(position).isChecked());

        holder.check_dco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                items.get(position).setChecked(isChecked);
            }
        });

//        holder.btn_depute.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fragment != null && fragment instanceof DCODeputeFragment) {
//                    DCODeputeFragment dcoDeputeFragment = (DCODeputeFragment) fragment;
//                    dcoDeputeFragment.deputeDCO(items.get(position));
//                }
////                ToastClass.showShortToast(activity.getApplicationContext(), "DCO Deputed");
//            }
//        });


        holder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_view)
        LinearLayout ll_view;
        @BindView(R.id.tv_so)
        TextView tv_so;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_available_time)
        TextView tv_available_time;
        @BindView(R.id.btn_depute)
        Button btn_depute;
        @BindView(R.id.check_dco)
        CheckBox check_dco;
//        @BindView(R.id.tv_gender)
//        TextView tv_gender;
//        @BindView(R.id.tv_sports)
//        TextView tv_sports;
//        @BindView(R.id.tv_discipline)
//        TextView tv_discipline;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}