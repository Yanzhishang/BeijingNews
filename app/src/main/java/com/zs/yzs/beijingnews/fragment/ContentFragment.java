package com.zs.yzs.beijingnews.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zs.yzs.beijingnews.R;
import com.zs.yzs.beijingnews.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 *  正文 Fragment
 * Created by YZS on 2017/4/13.
 */

public class ContentFragment extends BaseFragment{

    private ViewPager viewpager;
    private RadioGroup rg_main;
    @Override
    public View initView() {
        Log.e(TAG, "正文Fragment视图被初始化了");
        View view = View.inflate(context, R.layout.content_fragment,null);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "正文Fragment数据被初始化了");

        //设置默认选中首页
        rg_main.check(R.id.rb_home);

    }
}
