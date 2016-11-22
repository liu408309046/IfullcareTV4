package com.kunyuanzhihui.ifullcaretv.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.adapter.UserListAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;

import java.util.ArrayList;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/14- 17:57.
 */

public class DeleteUserFragment extends BaseFragment implements UserListAdapter.OnItemSelectListener, UserListAdapter.OnItemClickListener {
    private RecyclerView gallery_delete;
    private ArrayList<GalleryBean> user_list;

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.page_delete_character;
    }

    @Override
    protected void init() {
        initpage_choose();
    }

    private void initpage_choose() {
        setData();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        gallery_delete.setLayoutManager(manager);
        gallery_delete.addItemDecoration(new SpaceItemDecoration(30, 0, 30, 0));
        gallery_delete.setFocusable(false);

        UserListAdapter adapter = new UserListAdapter(getActivity(), user_list);
        gallery_delete.setAdapter(adapter);
        adapter.setOnItemSelectListener(this);
        adapter.setOnItemClickListener(this);

    }

    private void setData() {
        user_list = new ArrayList<>();
        user_list.add(new GalleryBean(R.mipmap.head1, "奶奶kmkakmkmkssssssssss"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));
        user_list.add(new GalleryBean(R.mipmap.head1, "表哥1"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));
        user_list.add(new GalleryBean(R.mipmap.head1, "表哥1"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));
        user_list.add(new GalleryBean(R.mipmap.head1, "表哥1"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));
        user_list.add(new GalleryBean(R.mipmap.head1, "表哥1"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));
        user_list.add(new GalleryBean(R.mipmap.head1, "表哥1"));
        user_list.add(new GalleryBean(R.mipmap.head2, "表姐2"));


    }

    @Override
    public void onItemSelect(View view, int position) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}