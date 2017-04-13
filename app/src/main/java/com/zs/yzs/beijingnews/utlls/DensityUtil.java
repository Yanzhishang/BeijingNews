package com.zs.yzs.beijingnews.utlls;

import android.content.Context;

/**
 *   dp 与 sp 之间的转换
 *
 *   单位转换工具
 *   px 与 dp 互相转换
 * Created by YZS on 2017/4/13.
 */

public class DensityUtil {
    /**
     * 根据手机的分辨率 从 dip 的单位转换成为 px （像素）
     */
    public static int dip2px(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * 根据手机的分辨率 从 px （像素） 的单位转换成为  dp
     */
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5);
    }
}
