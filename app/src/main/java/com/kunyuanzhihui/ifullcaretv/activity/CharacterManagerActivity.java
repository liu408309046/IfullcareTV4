package com.kunyuanzhihui.ifullcaretv.activity;

import android.animation.Animator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.QrcodeBean;
import com.kunyuanzhihui.ifullcaretv.fragment.AddUserFragment;
import com.kunyuanzhihui.ifullcaretv.fragment.BaseFragment;
import com.kunyuanzhihui.ifullcaretv.fragment.ChooseUserFragment;
import com.kunyuanzhihui.ifullcaretv.fragment.DeleteUserFragment;
import com.kunyuanzhihui.ifullcaretv.fragment.UserListFragment;

import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.net.TvAPI;
import com.open.androidtvwidget.adapter.BaseTabTitleAdapter;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.OpenTabHost;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.open.androidtvwidget.view.TextViewWithTTF;


import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/11- 16:06.
 */
public class CharacterManagerActivity extends BaseActivity {


    private EffectNoDrawBridge mEffectNoDrawBridge;
    private View mNewFocus;
    private View mOldView;
    private LinearLayout lin_qrcode;
    private ImageView img_home, img_back, img_qrcode;//二维码
    private OpenTabHost openTabHost;
    private ViewPager viewpager;
    private View view1, view2, view3, view4;
    private List<View> viewList = new ArrayList<>();



    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_character_manager;
    }

    @Override
    protected void init() {

        intitab();
        initViewPager();
        initMoveBridge();
        getQrcode();

    }

    private void getQrcode() {

        EHapiManager.get_qecode(new EHResponse() {
            @Override
            public void onSuccess(String result) {
                Log.e("----二维码------", result.replace("\\",""));
                QrcodeBean qrcodeBean=new Gson().fromJson( result.replace("\\",""), QrcodeBean.class);
                int result_code=qrcodeBean.getResult_code();
                String message=qrcodeBean.getMessage();
                QrcodeBean.DataBean data=qrcodeBean.getData();

                if(result_code==0){
                    if(data!=null&&!data.equals("")) {
                        Bitmap qrBitmap = generateBitmap(data.getQcode(),200,200);
                        img_qrcode.setImageBitmap(qrBitmap);
                    }
                }
            }

            @Override
            public void onError(String request) {

            }
        });

    }

    private void intitab() {


        openTabHost.setOnTabSelectListener(new OpenTabHost.OnTabSelectListener() {
            @Override
            public void onTabSelect(OpenTabHost openTabHost, View titleWidget, int postion) {

                if (viewpager != null) {
                    viewpager.setCurrentItem(postion);
                }

            }
        });
        openTabHost.setAdapter(new OpenTabTitleAdapter());


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

    }

    private float getDimension(int id) {
        return getResources().getDimension(id);
    }

    private void initMoveBridge() {
        float density = getResources().getDisplayMetrics().density;

        mEffectNoDrawBridge = new EffectNoDrawBridge();
        mainUpView.setEffectBridge(mEffectNoDrawBridge);
        mEffectNoDrawBridge.setUpRectResource(R.mipmap.select); // 设置移动边框图片.
        mEffectNoDrawBridge.setDrawUpRectPadding(new Rect(19, 19, 19, 19));
    }


    private void initViewPager() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        List<BaseFragment> fragmentList = new ArrayList<>();
        AddUserFragment addUserFragment = new AddUserFragment();
        ChooseUserFragment chooseUserFragment = new ChooseUserFragment();
        DeleteUserFragment deleteUserFragment = new DeleteUserFragment();
        UserListFragment userListFragment = new UserListFragment();
        fragmentList.add(chooseUserFragment);
        fragmentList.add(addUserFragment);
        fragmentList.add(userListFragment);
        fragmentList.add(deleteUserFragment);


        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewpager.setOffscreenPageLimit(4);

        viewpager.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null && !(newFocus instanceof TextViewWithTTF)) {
                    mEffectNoDrawBridge.setVisibleWidget(false);
                    mNewFocus = newFocus;
                    mOldView = oldFocus;
                    mainUpView.setFocusView(newFocus, oldFocus, 1.1f);
                    // 让被挡住的焦点控件在前面.
                    newFocus.bringToFront();

                } else { // 标题栏处理.
                    mNewFocus = null;
                    mOldView = null;
                    mainUpView.setUnFocusView(oldFocus);
                    mEffectNoDrawBridge.setVisibleWidget(true);
                }
            }
        });
        viewpager.setCurrentItem(0);

        switchTab(openTabHost, 0);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switchTab(openTabHost, position);
                if (position == 1) {
                    lin_qrcode.setVisibility(View.VISIBLE);
                } else {
                    lin_qrcode.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE: // viewpager 滚动结束.
                        mainUpView.setFocusView(mNewFocus, mOldView, 1.1f);
                        // 监听动画事件.
                        mEffectNoDrawBridge.setOnAnimatorListener(new OpenEffectBridge.NewAnimatorListener() {
                            @Override
                            public void onAnimationStart(OpenEffectBridge bridge, View view, Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(OpenEffectBridge bridge, View view, Animator animation) {
                                // 动画结束的时候恢复原来的时间. (这里只是DEMO)
                                mEffectNoDrawBridge.setTranDurAnimTime(300);
                            }
                        });
                        // 让被挡住的焦点控件在前面.
                        if (mNewFocus != null)
                            mNewFocus.bringToFront();
                        OPENLOG.D("SCROLL_STATE_IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        OPENLOG.D("SCROLL_STATE_DRAGGING");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING: // viewPager开始滚动.
                        mEffectNoDrawBridge.clearAnimator(); // 清除之前的动画.
                        mEffectNoDrawBridge.setTranDurAnimTime(0); // 避免边框从其它地方跑出来.
                        OPENLOG.D("SCROLL_STATE_SETTLING");
                        break;
                }
            }
        });
        // 初始化.

    }


    public void switchTab(OpenTabHost openTabHost, int postion) {
        List<View> viewList = openTabHost.getAllTitleView();
        for (int i = 0; i < viewList.size(); i++) {
            TextViewWithTTF view = (TextViewWithTTF) openTabHost.getTitleViewIndexAt(i);
            view.setPadding(10, 10, 10, 10);
            view.setTextSize(20);
            if (view != null) {
                Resources res = view.getResources();
                if (res != null) {
                    if (i == postion) {
                        view.setTextColor(res.getColor(R.color.white));
                        view.setBackgroundResource(R.mipmap.button);
                        view.setTypeface(null, Typeface.BOLD);
                        view.setSelected(true); // 为了显示 失去焦点，选中为 true的图片.
                        if (i == 1) {
                            lin_qrcode.setVisibility(View.VISIBLE);
                        } else {
                            lin_qrcode.setVisibility(View.GONE);
                        }
                    } else {

                        view.setBackgroundResource(android.R.color.transparent);
                        view.setTextColor(res.getColor(android.R.color.black));
                        view.setTypeface(null, Typeface.NORMAL);
                        view.setSelected(false);
                    }
                }
            }
        }
    }

    public class OpenTabTitleAdapter extends BaseTabTitleAdapter {
        private List<String> titleList = new ArrayList<String>();

        public OpenTabTitleAdapter() {
            titleList.add("选择成员");
            titleList.add("添加成员");
            titleList.add("成员列表");
            titleList.add("删除成员");
        }

        @Override
        public int getCount() {
            return titleList.size();
        }


        private List<Integer> ids = new ArrayList<Integer>() {
            {
                add(R.id.tab1);
                add(R.id.tab2);
                add(R.id.tab3);
                add(R.id.tab4);
            }
        };

        @Override
        public Integer getTitleWidgetID(int pos) {
            return ids.get(pos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            parent.getContext();
            String title = titleList.get(position);
            if (convertView == null) {
                convertView = newTabIndicator(parent.getContext(), title, false);
                convertView.setId(ids.get(position)); // 设置ID.
            } else {
                // ... ...
            }
            return convertView;
        }


        private View newTabIndicator(Context context, String tabName, boolean focused) {
            final String name = tabName;
            View viewC = View.inflate(context, R.layout.tab_view_indicator_item, null);
            TextViewWithTTF view = (TextViewWithTTF) viewC.findViewById(R.id.tv_tab_indicator);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 0);
            view.setLayoutParams(lp);


            view.setText(name);

            if (focused == true) {
                Resources res = context.getResources();
                view.setTextColor(res.getColor(android.R.color.black));
                view.setTypeface(null, Typeface.BOLD);
                view.requestFocus();
            }
            return viewC;
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public static class GalleryBean {
        public int headRes;
        public String headText;

        public GalleryBean(int headRes, String headText) {
            this.headRes = headRes;
            this.headText = headText;
        }

        public int getHeadRes() {
            return headRes;
        }

        public void setHeadRes(int headRes) {
            this.headRes = headRes;
        }

        public String getHeadText() {
            return headText;
        }

        public void setHeadText(String headText) {
            this.headText = headText;
        }
    }

    private Bitmap generateBitmap(String content,int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}