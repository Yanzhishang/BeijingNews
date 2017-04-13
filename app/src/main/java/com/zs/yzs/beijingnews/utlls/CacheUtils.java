package com.zs.yzs.beijingnews.utlls;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.zs.yzs.beijingnews.activity.GuideActivity;


/**
 * Created by YZS on 2017/4/13.
 *
 *  用来缓存软件的一些参数和数据
 */

public class CacheUtils {
    /**
     *    得到缓存值
     *
     * @param context   上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("yzs", Context.MODE_PRIVATE);

        return sp.getBoolean(key, false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("yzs", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
