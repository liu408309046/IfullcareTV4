package com.kunyuanzhihui.ifullcaretv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kunyuanzhihui.ifullcaretv.R;

/**
 * Created by dp on 2016/10/31.
 */

public class CenterImageButton extends LinearLayout{

    public CenterImageButton(Context context) {
        this(context,null);
    }

    public CenterImageButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CenterImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    ImageView imgView;
    TextView textView;

    private void init(AttributeSet attrs) {
        setFocusable(true);
        setFocusableInTouchMode(true);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CenterImageButton);
        int imgWidth = array.getDimensionPixelSize(R.styleable.CenterImageButton_image_width,0);
        int imgHeight = array.getDimensionPixelSize(R.styleable.CenterImageButton_image_height,0);
        int srcId = array.getResourceId(R.styleable.CenterImageButton_image_src,0);
        imgView = new ImageView(this.getContext());
        imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgView.setImageResource(srcId);
        LayoutParams imgViewParams = new LayoutParams(imgWidth,imgHeight);
        this.addView(imgView,imgViewParams);

        int textWidth = array.getDimensionPixelSize(R.styleable.CenterImageButton_text_width,0);
        int textHeight = array.getDimensionPixelSize(R.styleable.CenterImageButton_text_height,0);
        int textColor = array.getColor(R.styleable.CenterImageButton_text_color,0);
        float textSize = array.getDimension(R.styleable.CenterImageButton_text_size,0);
        String text = array.getString(R.styleable.CenterImageButton_text);
        textView = new TextView(this.getContext());
        textView.setPadding(10,10,10,10);
        textView.setGravity(Gravity.CENTER);
        textView.getPaint().setAntiAlias(true);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setText(text);
        LayoutParams textViewParams = new LayoutParams(textWidth,textHeight);
        this.addView(textView,textViewParams);

        array.recycle();
    }
}
