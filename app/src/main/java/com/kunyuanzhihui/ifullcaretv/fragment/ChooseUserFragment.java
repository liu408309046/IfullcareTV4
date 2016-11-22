package com.kunyuanzhihui.ifullcaretv.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.adapter.CMGalleryAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/14- 17:56.
 */

public class ChooseUserFragment extends BaseFragment {

    RecyclerView gallery_choose;
    List<GalleryBean> user_list;

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.page_choose_character;
    }

    @Override
    protected void init() {
        initpage_choose();
    }


    private void initpage_choose() {
        setData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery_choose.setLayoutManager(manager);
        gallery_choose.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 30));
        gallery_choose.setFocusable(false);


        CMGalleryAdapter adapter = new CMGalleryAdapter(getActivity(), user_list);
        gallery_choose.setAdapter(adapter);
        adapter.setOnItemSelectListener(new CMGalleryAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {

            }
        });
    }

    private void setData() {

        EHapiManager.getFamilyMembers("", MyApplication.getInstance().getTv_token(), new EHResponse() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String request) {

            }
        });

    }


}
