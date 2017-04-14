package com.zs.yzs.beijingnews;

import android.app.Application;

import org.xutils.x;

/**
 *   代表整个软件
 *
 * Created by YZS on 2017/4/14.
 */

public class BeijingNewsApplication extends Application {
    /**
     * 此方法是在所有组件创建之前执行
     *
     */

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
