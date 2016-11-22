package com.kunyuanzhihui.ifullcaretv.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.activity.AddCharacterActivity;
import com.kunyuanzhihui.ifullcaretv.activity.BaseActivity;
import com.kunyuanzhihui.ifullcaretv.adapter.CMGalleryAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.widget.MyHorizontalScrollView;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/14- 17:56.
 */

public class AddUserFragment extends BaseFragment implements CMGalleryAdapter.OnItemSelectListener, CMGalleryAdapter.OnItemClickListener {
    @BaseActivity.ViewResourceId(R.id.h_scrollview)
    MyHorizontalScrollView h_scrollview;
    RelativeLayout rel_hasid, rel_tur;
    private RecyclerView gallery_add;
    private List<GalleryBean> user_list;



    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.page_add_character;
    }

    @Override
    protected void init() {
        initpage_choose();
    }




    private void initpage_choose() {

        setData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery_add.setLayoutManager(manager);
        gallery_add.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 30));
        gallery_add.setFocusable(false);


        CMGalleryAdapter adapter = new CMGalleryAdapter(getActivity(), user_list);
        gallery_add.setAdapter(adapter);
        adapter.setOnItemSelectListener(this);
        adapter.setOnItemClickListener(this);

    }

    private void setData() {
        user_list = new ArrayList<>();
        user_list.add(new GalleryBean(R.mipmap.newcharacter, "已有账号登入"));
        user_list.add(new GalleryBean(R.mipmap.newcharacter, "添加访客"));

    }

    @Override
    public void onItemSelect(View view, int position) {

    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position){
            case 0:
                break;

            case 1:

                startActivity(new Intent(getActivity(),AddCharacterActivity.class));

                break;

        }

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
