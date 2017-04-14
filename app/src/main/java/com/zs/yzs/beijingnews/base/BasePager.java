package com.zs.yzs.beijingnews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zs.yzs.beijingnews.R;

/**
 *  五个视图的基类
 *
 * Created by YZS on 2017/4/14.
 */

public class BasePager {
    /**
     *  上下文
     */
    public final Context content;  //MainActivity
    /**
     *  视图，代表不同的页面
     */
    public View rootview;
    /**
     * 显示标题
     */
    public TextView tv_title;

    /**
     *  点击侧滑的按钮
     */
    public ImageButton ib_menu;

    /**
     * 加载各个子页面
     */
    public FrameLayout fl_content;


    public BasePager(Context context){
        this.content = context;
        //构造方法一执行，视图就被初始化了
        rootview = initview();
    }

    /**
     * 用于初始化公共部分视图，并且初始化加载自视图的FragmeLayout
     * @return
     */
    private View initview() {
        View view = View.inflate(content, R.layout.base_pager,null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        return view;

    }

    /**
     *  初始化数据；当子类需要初始化数据，或者绑定数据；联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){

    }
}
