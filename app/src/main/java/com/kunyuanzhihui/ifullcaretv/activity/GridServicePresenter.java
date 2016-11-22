package com.kunyuanzhihui.ifullcaretv.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.ServiceBean;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/11- 14:58.
 */
public class GridServicePresenter extends OpenPresenter {

    private List<ServiceBean> service_list;

    public GridServicePresenter(List<ServiceBean> service_list) {
        this.service_list=service_list;
    }

    @Override
    public int getItemCount() {
        return service_list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, null);
        return new GridServicePresenter.ServicesHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ServicesHolder gridViewHolder = (ServicesHolder) viewHolder;
        TextView textView = (TextView) gridViewHolder.tv;
        ImageView iv = gridViewHolder.iv;
        textView.setText(service_list.get(position).getTitle());
        iv.setBackgroundResource(service_list.get(position).getBg_res());
    }

    public class ServicesHolder extends OpenPresenter.ViewHolder {
        private TextView tv;
        private ImageView iv;

        public ServicesHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_service);
            iv = (ImageView) view.findViewById(R.id.img_bg);
        }
    }
}
