package com.zs.yzs.beijingnews.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zs.yzs.beijingnews.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * 左侧菜单的 Fragment
 *
 * Created by YZS on 2017/4/13.
 */

public class LeftmenuFragment extends BaseFragment{

    private TextView textview;
    @Override
    public View initView() {
        Log.e(TAG, "左侧菜单--视图--被初始化了");
        textview = new TextView(context);
        textview.setTextSize(23);
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(Color.RED);
        return textview;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "左侧菜单数据被初始化了");

        textview.setText("左侧菜单");

    }
}
