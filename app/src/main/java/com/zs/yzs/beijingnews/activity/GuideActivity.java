package com.zs.yzs.beijingnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zs.yzs.beijingnews.R;
import com.zs.yzs.beijingnews.SplashActivity;
import com.zs.yzs.beijingnews.utlls.CacheUtils;
import com.zs.yzs.beijingnews.utlls.DensityUtil;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * 引导页面
 */

/**
 * 步骤
 * 1
 */

public class GuideActivity extends Activity {
    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    //两点的间距
    private int leftmax;

    private int widthdpi;

    //存放图片的集合
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        widthdpi = DensityUtil.dip2px(this,10);
        Log.e(TAG,widthdpi+"-----------------");
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中
            imageViews.add(imageView);

            //创建小圆点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             *  显示的单位是像素
             *
             *  把单位当成是 dp 转成对应的像素
             */
            LinearLayout.LayoutParams parms = new
                    LinearLayout.LayoutParams(widthdpi, widthdpi);
            if (i != 0) {
                //不包括第 0 个，所有的点距离左边有10 个像素
                parms.leftMargin = widthdpi;
            }
            point.setLayoutParams(parms);
            //添加到线性布局中
            ll_point_group.addView(point);

        }

        //设置ViewPager的适配器
        viewpager.setAdapter(new MyPagerAdapter());

        //根据 View 的生命周期，当视图执行到 onLayout 或者 onDraw 的时候，视图的高和宽，边距就知道了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //得到屏幕滑动的百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        btn_start_main.setOnClickListener(new MyOnOnClickListener());

    }

    /**
     * 此类
     * 按钮监听事件
     */
    class MyOnOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //1、保存曾经进入过主页的数据
            CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);

            //2、跳转到主页面
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            //3、关闭引导页面
            finish();


        }
    }

    /**
     * 监听页面的变化
     */
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 当页面滚动会回调这个方法
         *
         * @param position             当前滑动页面的位置
         * @param positionOffset       页面滚动 的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点移动的距离 = 屏幕滑动的百分比 * 间距
            int leftmargin = (int) (positionOffset * leftmax);
            //打印日志
            Log.e(TAG, "position==" + position + ",positionOffset ==" + positionOffset + "positionOffsetPixels==" + positionOffsetPixels);
            //两点滑动距离对应的坐标 = 原来的起始位置 + 两点间移动的距离
            leftmargin = (int) (position * leftmax + positionOffset * leftmax);
            //params.leftMargin = 两点间滑动距离对应的坐标

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候，回调这个方法
         *
         * @param position 被选中页面对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                //最后一个页面,显示按钮
                btn_start_main.setVisibility(View.VISIBLE);
            } else {
                //其他页面，隐藏按钮
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 当ViewPager页面滑动状态发声变化的时候
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    /**
     * 红点滑动监听
     */
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            //执行不止一次,而我们只执行一次就够了
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            //间距 = 第一个点距离左边的距离 - 第0个点距离左边的额距离
            leftmax = ll_point_group.getChildAt(1).getLeft()
                    - ll_point_group.getChildAt(0).getLeft();

        }
    }

    /**
     * ViewPager 适配器
     */
    class MyPagerAdapter extends PagerAdapter {
        /**
         * 返回数据的总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 此方法类似 ListView 的getView 方法，
         *
         * @param container ViewPager
         * @param position  要创建页面的位置
         * @return 返回和创建当前页面有关系的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageview = imageViews.get(position);
            //添加到容器中
            container.addView(imageview);
            return imageview;
        }

        /**
         * 判断
         *
         * @param view   当前创建的视图
         * @param object 上面 instantiateItem 返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁页面
         *
         * @param container ViewPager
         * @param position  要销毁页面的位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);

            container.removeView((View) object);
        }
    }
}
