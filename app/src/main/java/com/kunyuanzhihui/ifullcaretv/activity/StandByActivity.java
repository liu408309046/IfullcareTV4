package com.kunyuanzhihui.ifullcaretv.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.util.Util;

import java.util.ArrayList;
import java.util.List;

public class StandByActivity extends BaseActivity {

    Gallery gallery_user;

    @Override
    protected boolean needFocusFrame() {
        return false;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_stand_by;
    }

    @Override
    protected void init() {
        List<GalleryBean> data = new ArrayList<>();
        data.add(new GalleryBean(R.mipmap.head1, "head1"));
        data.add(new GalleryBean(R.mipmap.head2, "head2"));
        data.add(new GalleryBean(R.mipmap.head3, "head3"));
        data.add(new GalleryBean(R.mipmap.head4, "head4"));
        data.add(new GalleryBean(R.mipmap.head5, "head5"));
        data.add(new GalleryBean(R.mipmap.head6, "head6"));

        final MyGalleryAdapter adapter = new MyGalleryAdapter(this, data);
        gallery_user.setAdapter(adapter);
        gallery_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//                com.kunyuanzhihui.ifullcaretv.adapter.setSelectedItem(position);
//                int childCount = parent.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    View view = parent.getChildAt(i);
//                    if (position == i) {
//                        view.setBackgroundResource(R.mipmap.select);
//                    } else {
//                        view.setBackgroundResource(android.R.color.transparent);
//                    }
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static class GalleryBean {
        public int headRes;
        public String headText;

        public GalleryBean(int headRes, String headText) {
            this.headRes = headRes;
            this.headText = headText;
        }
    }

    public static class MyGalleryAdapter extends android.widget.BaseAdapter {

        private int selectedItem;
        private StandByActivity context;
        private List<GalleryBean> data;
        int[] screenSize;
        int baseWidth, baseHeight;
        int imgWidth = 300;
        int imgHeight = 300;

        public MyGalleryAdapter(StandByActivity context, List<GalleryBean> data) {
            this.context = context;
            this.data = data;
            screenSize = Util.getScreenSize(context);
            baseWidth = (int) (imgWidth + (screenSize[1] - 720) * imgWidth / 720.f);
            baseHeight = (int) (imgHeight + (screenSize[0] - 1280) * imgHeight / 1280.f);
            Log.e("tag", "screenSize=[" + screenSize[0] + "," + screenSize[1] + "]");
        }

        public void setSelectedItem(int selectedItem) {
            if (this.selectedItem != selectedItem) {
                this.selectedItem = selectedItem;
                notifyDataSetChanged();
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layout = null;
            if (convertView == null) {
                layout = new LinearLayout(context);
//                layout.setFocusable(true);
//                layout.setFocusableInTouchMode(true);
                layout.setGravity(Gravity.CENTER);
                layout.setOrientation(LinearLayout.VERTICAL);

                StateListDrawable drawable = new StateListDrawable();
                Drawable selected = context.getResources().getDrawable(R.mipmap.select);
                drawable.addState(new int[]{
                        android.R.attr.state_focused,
                        android.R.attr.state_selected,
                        android.R.attr.state_pressed
                }, selected);
                drawable.addState(new int[]{android.R.attr.state_selected},selected);
                drawable.addState(new int[]{android.R.attr.state_focused},selected);
                drawable.addState(new int[]{android.R.attr.state_pressed},selected);
                drawable.addState(StateSet.NOTHING, new ColorDrawable(Color.TRANSPARENT));
                layout.setBackgroundDrawable(drawable);
                Log.e("tag", drawable.toString());

                ImageView view = new ImageView(context);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(baseWidth, baseHeight);
                layout.addView(view, params);

                TextView tv = new TextView(context);
                tv.setPadding(10, 10, 10, 10);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                tv.getPaint().setFakeBoldText(true);
                tv.getPaint().setAntiAlias(true);
                tv.setBackgroundColor(Color.TRANSPARENT);
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.addView(tv, params);
                convertView = layout;
            } else {
                layout = (LinearLayout) convertView;
            }
            int temp = (int) (baseHeight * 1.2);
            layout.setLayoutParams(new Gallery.LayoutParams((int) (baseWidth * 1.2), (int) (temp * 1.2)));
            ((ImageView) layout.getChildAt(0)).setImageDrawable(context.getResources().getDrawable(data.get(position).headRes));
            ((TextView) layout.getChildAt(1)).setText(data.get(position).headText);
            Log.e("tag", convertView.toString());
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }
    }
}
