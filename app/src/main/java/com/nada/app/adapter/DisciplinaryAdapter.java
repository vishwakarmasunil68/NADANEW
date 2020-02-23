package com.nada.app.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.pojo.PanelPOJO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisciplinaryAdapter extends RecyclerView.Adapter<DisciplinaryAdapter.ViewHolder> {

    private List<PanelPOJO> items;
    Activity activity;
    Fragment fragment;


    public DisciplinaryAdapter(Activity activity, Fragment fragment, List<PanelPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_discplinary_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position % 2 != 0) {
            holder.ll_view.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }

        holder.tv_dt.setText(items.get(position).getDatetime());
        holder.tv_name.setText(items.get(position).getName());
        holder.tv_orders.setText(items.get(position).getOrders());
        holder.tv_cases.setText(items.get(position).getCases());

        holder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_view)
        LinearLayout ll_view;
        @BindView(R.id.tv_dt)
        TextView tv_dt;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_orders)
        TextView tv_orders;
        @BindView(R.id.tv_cases)
        TextView tv_cases;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}