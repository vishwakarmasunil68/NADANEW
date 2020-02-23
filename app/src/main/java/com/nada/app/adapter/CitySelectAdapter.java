package com.nada.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.listeners.ItemClickListener;
import com.nada.app.pojo.CityPOJO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitySelectAdapter extends RecyclerView.Adapter<CitySelectAdapter.ViewHolder> {

    public List<CityPOJO> items;
    Activity activity;
    Fragment fragment;
    ItemClickListener itemClickListener;

    public CitySelectAdapter(Activity activity, Fragment fragment, List<CityPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
        setHasStableIds(true);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_city_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.check_city.setChecked(items.get(position).isChecked());

        holder.tv_city_name.setText(items.get(position).getName());

        holder.check_city.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                items.get(position).setChecked(isChecked);
                itemClickListener.onItemClicked(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check_city.callOnClick();
            }
        });

        holder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<CityPOJO> getItems() {
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_city_name)
        TextView tv_city_name;
        @BindView(R.id.check_city)
        CheckBox check_city;
        @BindView(R.id.ll_view)
        LinearLayout ll_view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}