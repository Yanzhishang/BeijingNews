package com.zs.yzs.beijingnews.utlls;

import android.content.Context;
import android.content.SharedPreferences;

import com.zs.yzs.beijingnews.SplashActivity;

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
}
