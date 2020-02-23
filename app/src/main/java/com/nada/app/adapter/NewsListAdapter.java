package com.nada.app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.pojo.NewsPOJO;
import com.nada.app.utils.UtilityFunction;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<NewsPOJO> items;
    Activity activity;
    Fragment fragment;


    public NewsListAdapter(Activity activity, Fragment fragment, List<NewsPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        try {
            holder.tv_day.setText(items.get(position).getNewsDate().split("-")[2]);
            holder.tv_month.setText(UtilityFunction.getMonth(Integer.parseInt(items.get(position).getNewsDate().split("-")[1])));
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.tv_title.setText(items.get(position).getNewsTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).getNewsPath()));
                    activity.startActivity(browserIntent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        holder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_day)
        TextView tv_day;
        @BindView(R.id.tv_month)
        TextView tv_month;
        @BindView(R.id.tv_title)
        TextView tv_title;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}