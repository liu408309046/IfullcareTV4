package com.kunyuanzhihui.ifullcaretv.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/10/25.
 */

public class Util {
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int[] getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getRealMetrics(outMetrics);
            return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
        } else {
            return new int[]{manager.getDefaultDisplay().getWidth(), manager.getDefaultDisplay().getHeight()};
        }
    }
}
