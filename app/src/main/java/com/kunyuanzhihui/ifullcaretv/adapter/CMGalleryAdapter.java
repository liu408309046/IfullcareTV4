package com.kunyuanzhihui.ifullcaretv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.fragment.ChooseUserFragment;
import com.kunyuanzhihui.ifullcaretv.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/15- 10:02.
 */

public  class CMGalleryAdapter extends RecyclerView.Adapter<CMGalleryAdapter.GalleryViewHolder> {

    private int selectedItem;
    private Context context;
    private List<GalleryBean> data;
    private List<Integer> heights;
    private int currentPosition;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    private CMGalleryAdapter.OnItemClickListener mListener;
    private CMGalleryAdapter.OnItemSelectListener mSelectListener;

    public void setOnItemSelectListener(CMGalleryAdapter.OnItemSelectListener listener) {
        mSelectListener = listener;
    }

    public void setOnItemClickListener(CMGalleryAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public CMGalleryAdapter(Context context, List<GalleryBean> data) {
        this.context = context;
        this.data = data;
        getRandomHeight(data.size());
    }


    @Override
    public CMGalleryAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_character, null);
        CMGalleryAdapter.GalleryViewHolder holder = new CMGalleryAdapter.GalleryViewHolder(view);
        holder.iv_head = (CircleImageView) view.findViewById(R.id.iv_head);
        holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        return holder;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(final GalleryViewHolder holder, final int position) {

        holder.iv_head.setBackgroundResource(data.get(position % data.size()).getHeadRes());
        holder.tv_name.setText(data.get(position).getHeadText());
        holder.itemView.setFocusable(true);
        holder.itemView.setTag(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    currentPosition = (int) holder.itemView.getTag();
                    mSelectListener.onItemSelect(holder.itemView, currentPosition);
                }
            }
        });
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(v, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    private void getRandomHeight(int size) {
        heights = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            heights.add((int) (200 + Math.random() * 400));
        }
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        CircleImageView  iv_head;
        TextView tv_name;

        public GalleryViewHolder(View itemView) {
            super(itemView);

        }
    }
}
