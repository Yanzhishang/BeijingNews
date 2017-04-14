package com.zs.yzs.beijingnews.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.zs.yzs.beijingnews.base.BasePager;

/**
 *  设置页面
 * Created by YZS on 2017/4/14.
 */

public class SettingPager extends BasePager{
    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //1.设置标题
        tv_title.setText("设置页面");
        //2、联网请求，得到数据，创建视图
        TextView textview = new TextView(content);
            //设置文本居中
        textview.setGravity(Gravity.CENTER);
            //设置文本颜色
        textview.setTextColor(Color.RED);
            //设置文本字体大小
        textview.setTextSize(25);
        //3、把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textview);
        //4、绑定数据
        textview.setText("设置页面内容");
    }
}
