package com.nada.app.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.app.R;
import com.nada.app.pojo.GalleryPOJO;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.Webserviceurls;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryPOJO> items;
    Activity activity;
    Fragment fragment;


    public GalleryAdapter(Activity activity, Fragment fragment, List<GalleryPOJO> items) {
        this.items = items;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_gallery_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        int drawable = R.drawable.ic_gallery_1;
//
//        switch (position) {
//            case 0:
//                drawable = R.drawable.ic_gallery_1;
//                break;
//            case 1:
//                drawable = R.drawable.ic_gallery_2;
//                break;
//            case 2:
//                drawable = R.drawable.ic_gallery_3;
//                break;
//            case 3:
//                drawable = R.drawable.ic_gallery_4;
//                break;
//            case 4:
//                drawable = R.drawable.ic_gallery_5;
//                break;
//            case 5:
//                drawable = R.drawable.ic_gallery_6;
//                break;
//            case 6:
//                drawable = R.drawable.ic_gallery_7;
//                break;
//            case 7:
//                drawable = R.drawable.ic_gallery_8;
//                break;
//            case 8:
//                drawable = R.drawable.ic_gallery_9;
//                break;
//            case 9:
//                drawable = R.drawable.ic_gallery_10;
//                break;
//            case 10:
//                drawable = R.drawable.ic_gallery_11;
//                break;
//            case 11:
//                drawable = R.drawable.ic_gallery_12;
//                break;
//            case 12:
//                drawable = R.drawable.ic_gallery_1;
//                break;
//            case 13:
//                drawable = R.drawable.ic_gallery_2;
//                break;
//            case 14:
//                drawable = R.drawable.ic_gallery_3;
//                break;
//            case 15:
//                drawable = R.drawable.ic_gallery_4;
//                break;
//            case 16:
//                drawable = R.drawable.ic_gallery_5;
//                break;
//        }
//
//        holder.iv_gallery.setImageResource(drawable);

        String file_name = Webserviceurls.GALLERY_BASE_URL + items.get(position).getFileName();
        Log.d(TagUtils.getTag(), "gallery path:-" + file_name);

//        Glide.with(activity)
//                .load(Webserviceurls.GALLERY_BASE_URL+items.get(position).getFileName())
//                .placeholder(R.drawable.ic_gallery_icon)
//                .error(R.drawable.ic_gallery_icon)
//                .dontAnimate()
//                .into(holder.iv_gallery);

        Picasso.get()
                .load(file_name)
                .placeholder(R.drawable.ic_gallery_icon)
                .error(R.drawable.ic_gallery_icon)
                .into(holder.iv_gallery);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, GalleryImageViewActivity.class);
//                intent.putExtra("image", items.get(position).getFileName());
//                activity.startActivity(intent);
//            }
//        });

        holder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_gallery)
        ImageView iv_gallery;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}