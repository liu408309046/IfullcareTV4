package com.kunyuanzhihui.ifullcaretv.activity;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.LeftPopupWindowBean;
import com.kunyuanzhihui.ifullcaretv.util.Adapter;
import com.kunyuanzhihui.ifullcaretv.util.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends BaseActivity {

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_detail;
    }

    TextView tv_tips, tv_tipsContent, tv_advice, tv_moreAdvice, tv_valueTitle, tv_value, tv_unit, tv_lowSign, tv_centerSign, tv_highSign;
    ImageView img_headIcon, img_bottom;
    SeekBar seekBar;
    ListView left_items;
    SimpleAdapter adapter;
    int[] imgRes = {
            R.mipmap.report_left_weight, R.mipmap.report_left_fat, R.mipmap.report_left_water, R.mipmap.report_left_bloodpress,
            R.mipmap.report_left_muscle, R.mipmap.report_left_bmi, R.mipmap.report_left_calorie
    };
    String[] text = {
            "体重", "脂肪率", "水份率", "血压",
            "肌肉量", "身体质量指数", "卡路里"
    };
    int[] pro = {
            50, 65, 70, 80,
            33, 99, 21
    };

    PopupWindow mLeftPopupWindow;

    @Override
    protected void init() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<LeftPopupWindowBean> data = new ArrayList<>();
        int count = 0;
        for (int r : imgRes) {
            Map<String, Object> m = new HashMap<>();
            m.put("imgRes", r);
            list.add(m);
            data.add(new LeftPopupWindowBean(imgRes[count], text[count], pro[count]));
            count++;
        }
        adapter = new SimpleAdapter(this, list, R.layout.detail_left_item, new String[]{"imgRes"}, new int[]{R.id.img_item});
        left_items.setAdapter(adapter);
        left_items.setFocusable(false);
        left_items.setFocusableInTouchMode(false);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();
                    mainUpView.setFocusView(newFocus, oldFocusView, 1.1f);
                }
                oldFocusView = newFocus;
            }
        });

        final View contentView = getLayoutInflater().inflate(R.layout.left_popupwindow_layout, null, false);

        tv_moreAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLeftPopupWindow.isShowing()) {
                    mLeftPopupWindow.showAtLocation(DetailActivity.this.getWindow().getDecorView(), Gravity.START, 0, 0);
                    backgroundAlpha(0.5f);
                }
            }
        });
        mLeftPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mLeftPopupWindow.setAnimationStyle(R.style.PopupWindowStyle);

        mLeftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1);
            }
        });

        final ListView popList = (ListView) contentView.findViewById(R.id.popList);
        popList.setFocusable(true);
        popList.setFocusableInTouchMode(true);
        popList.setItemsCanFocus(false);
        popList.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("tag", "keycode=" + keyCode);
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        if (mLeftPopupWindow.isShowing()) {
                            mLeftPopupWindow.dismiss();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        Adapter<LeftPopupWindowBean> adapter = new Adapter<LeftPopupWindowBean>(contentView.getContext(), data, R.layout.left_popupwindow_item) {
            @Override
            public void insert(ViewHolder helper, LeftPopupWindowBean item) {
                ImageView img = helper.getView(R.id.item_img);
                img.setImageResource(item.getImgRes());

                TextView tv = helper.getView(R.id.item_text);
                tv.setText(item.getItemText());

                SeekBar bar = helper.getView(R.id.seekBar);
                bar.setProgress(item.getItemProgress());
            }
        };
        popList.setAdapter(adapter);
        popList.requestFocus();

        popList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("tag", "position1111=" + position + "---" +
                        parent.hasWindowFocus());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        popList.setSelection(0);
    }

    private void backgroundAlpha(float a) {
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.alpha = a;
        getWindow().setAttributes(p);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}