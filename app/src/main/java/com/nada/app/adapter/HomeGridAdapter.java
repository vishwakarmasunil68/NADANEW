package com.nada.app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.activity.AboutUsActivity;
import com.nada.app.activity.AppealPanelActivity;
import com.nada.app.activity.DisciplinaryActivity;
import com.nada.app.activity.GalleryActivity;
import com.nada.app.activity.MainActivity;
import com.nada.app.activity.NewsActivity;
import com.nada.app.activity.RTPActivity;
import com.nada.app.pojo.HomGridPOJO;
import com.nada.app.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    private List<HomGridPOJO> items;
    Activity activity;
    Fragment fragment;


    public HomeGridAdapter(Activity activity, Fragment fragment, List<HomGridPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_home_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.iv_grid.setImageResource(items.get(position).getDrawable());
        holder.tv_grid.setText(items.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) activity;
                switch (items.get(position).getName()) {
                    case StringUtils.NEWS:
                        activity.startActivity(new Intent(activity, NewsActivity.class));
                        break;
                    case StringUtils.GALLERY:
                        activity.startActivity(new Intent(activity, GalleryActivity.class));
                        break;
                    case StringUtils.ABOUT:
                        activity.startActivity(new Intent(activity, AboutUsActivity.class));
                        break;
                    case StringUtils.DISCIPLINARY:
                        activity.startActivity(new Intent(activity, DisciplinaryActivity.class));
                        break;
                    case StringUtils.APPEAL:
                        activity.startActivity(new Intent(activity, AppealPanelActivity.class));
                        break;
                    case StringUtils.RTP_WHEREABOUT:
                        mainActivity.startRTPWhereAboutFragment();
                        break;
                    case StringUtils.ADMIN:
                        mainActivity.startAdminFragment();
                        break;
                    case StringUtils.RTP:
                        activity.startActivity(new Intent(activity, RTPActivity.class));
                        break;
                    case StringUtils.PROHIBITED_LIST:
                        mainActivity.startProhibitedListActivity();
                        break;
                    case StringUtils.DRUGS:
                        mainActivity.startDrugsActivity();
                        break;
                    case StringUtils.DCO_WHEREABOUT:
                        mainActivity.startDCOWhereAboutFragment();
                        break;
                    case StringUtils.CHECK_MEDICINE:
                        mainActivity.startCheckMedicine();
                        break;
                    case StringUtils.DOPE_TEST:
                        mainActivity.checkDopeTest();
                        break;
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

        @BindView(R.id.cv_home)
        CardView cv_home;
        @BindView(R.id.iv_grid)
        ImageView iv_grid;
        @BindView(R.id.tv_grid)
        TextView tv_grid;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}