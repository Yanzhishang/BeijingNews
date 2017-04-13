package com.zs.yzs.beijingnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.zs.yzs.beijingnews.activity.GuideActivity;
import com.zs.yzs.beijingnews.activity.MainActivity;
import com.zs.yzs.beijingnews.utlls.CacheUtils;

public class SplashActivity extends Activity {
    /**
     * 静态常量
     */
    public static final String START_MAIN = "start_main";

    private RelativeLayout rl_splash_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);

        //渐变动画、 缩放动画、 旋转动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
//        aa.setDuration(1000);        //设置动画持续播放时间
        aa.setFillAfter(true);      //状态
        // 缩放动画
        ScaleAnimation sa = new ScaleAnimation(
                0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF,
                0.5f, ScaleAnimation.RELATIVE_TO_SELF,
                0.5f);
        sa.setDuration(1000);      //设置动画持续播放时间
//        sa.setFillAfter(true);      //状态

        // 旋转动画
        RotateAnimation ra = new RotateAnimation(
                0, 360,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
//        ra.setDuration(1000);      //设置动画持续播放时间
        ra.setFillAfter(true);      //状态

        //同时播放3个动画
        AnimationSet set = new AnimationSet(false);
        //添加3个动画没有先后顺序,便于同时播放
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        set.setDuration(2000);      //设置动画持续播放时间

        rl_splash_root.startAnimation(set);     //启动动画

        //设置动画的监听
        set.setAnimationListener(new MyAnimationListener());


    }

    /**
     * 动画监听类
     */

    class MyAnimationListener implements Animation.AnimationListener {
        /**
         * 当动画开始播放的时候回调此方法
         *
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画开始结束的时候回调此方法
         *
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {

            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            //判断
            Intent intent;
            if (isStartMain) {
                //如果进入过主页面，就直接进入主页面
                //跳转到主页面
                intent = new Intent(SplashActivity.this, MainActivity.class);


            } else {
                //如果没有进入过主页面，进进入引导页面
                intent = new Intent(SplashActivity.this, GuideActivity.class);

            }
            //页面跳转
            startActivity(intent);
            //关闭 splash 页面
            finish();


            //Toast.makeText(SplashActivity.this, "动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        /**
         * 当动画开始播重复放的时候回调此方法
         *
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
