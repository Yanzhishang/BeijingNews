package com.zs.yzs.beijingnews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *      自定义不可滑动的ViewPager
 * Created by YZS on 2017/4/14.
 */

public class NoScrollViewPager extends ViewPager{

    /**
     *  在代码中实例化的时候用该方法
     * @param context
     */
    public NoScrollViewPager(Context context) {
        super(context);
    }

    /**
     *  在布局文件中使用该类的时候，实例化该类用该构造方法，这个方法不能少，不然会崩溃
     * @param context
     * @param attrs
     */
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *  重新触摸事件，消耗掉
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
