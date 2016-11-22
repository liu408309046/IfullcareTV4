package com.kunyuanzhihui.ifullcaretv.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.GoodTypeBean;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView demo 的左侧菜单.
 * Created by hailongqiu on 2016/8/24.
 */
public class LeftMenuPresenter extends OpenPresenter {
    private Context context;
    private List<GoodTypeBean.DataBean> strList;

    public LeftMenuPresenter(Context context, List<GoodTypeBean.DataBean> strList) {
        this.context = context;
        this.strList = strList;
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    @Override
    public LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, null);

        return new LeftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        LeftViewHolder leftViewHolder = (LeftViewHolder) viewHolder;
        TextView tv_menu = (TextView) leftViewHolder.view.findViewById(R.id.tv_menu);
        tv_menu.setText(strList.get(position).getCat_name());

    }

    class LeftViewHolder extends OpenPresenter.ViewHolder {
        TextView tv_type;

        public LeftViewHolder(View itemView) {
            super(itemView);
        }
    }

}
