package com.kunyuanzhihui.ifullcaretv.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.adapter.CMGalleryAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;

import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/18- 14:55.
 */
public class FamilyListActivity  extends BaseActivity{

    RecyclerView gallery_familylist;
    private List<GalleryBean> familyList;


    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_family_list;
    }

    @Override
    protected void init() {
//        getFamilylist();

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery_familylist.setLayoutManager(manager);
        gallery_familylist.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 30));
        gallery_familylist.setFocusable(false);


//        CMGalleryAdapter adapter = new CMGalleryAdapter(getApplicationContext(),familyList );
//        gallery_familylist.setAdapter(adapter);

    }

    public void getFamilylist(){

        EHapiManager.getMyFamilys(MyApplication.getInstance().getTv_token(), new EHResponse() {
            @Override
            public void onSuccess(String result) {
                Log.e("---我的家庭组--",result);
            }

            @Override
            public void onError(String request) {

            }
        });
    }
}
