package com.zs.yzs.beijingnews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zs.yzs.beijingnews.R;
import com.zs.yzs.beijingnews.fragment.ContentFragment;
import com.zs.yzs.beijingnews.fragment.LeftmenuFragment;
import com.zs.yzs.beijingnews.utlls.DensityUtil;

public class MainActivity extends SlidingFragmentActivity{

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1、设置主页面（新闻）
        setContentView(R.layout.activity_main);

        //2、设置左侧滑菜单
        setBehindContentView(R.layout.activity_leftmenu);

        //3、设置侧滑菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);//设置右侧菜单

        //4、设置显示的模式：左侧菜单+主页；左侧菜单+主页+右侧菜单； 主页+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);  //左菜单

        //5、设置滑动模式：滑动边缘 TOUCHMODE_MARGIN；滑动全屏 TOUCHMODE_FULLSCREEN；不可滑动 TOUCHMODE_NONE
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //6、设置主页宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 150));

        //7、设置淡入淡出
        slidingMenu.setFadeEnabled(true);

        //8、设置淡入淡出比例
        slidingMenu.setFadeDegree(1.0f);

        //9、设置侧滑菜单图片
        slidingMenu.setBackgroundResource(R.drawable.splash_sheep);



        //初始化 Fragment
        initFragment();
    }

    private void initFragment() {
        //1、得到 Fragment
        FragmentManager fm = getSupportFragmentManager();
        //2、开始事务
        FragmentTransaction ft = fm.beginTransaction();
        //3、替换
                //主页
        ft.replace(R.id.fl_main_content, new ContentFragment(), MAIN_CONTENT_TAG);
                //左侧菜单
        ft.replace(R.id.fl_leftmenu, new LeftmenuFragment(), LEFTMENU_TAG);
        //4、提交
        ft.commit();


        /**
         * 简写
         */
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fl_main_content, new ContentFragment(), MAIN_CONTENT_TAG)
//                .replace(R.id.fl_leftmenu, new ContentFragment(), LEFTMENU_TAG)
//                .commit();

    }
}
