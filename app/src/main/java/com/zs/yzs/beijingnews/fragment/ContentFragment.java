package com.zs.yzs.beijingnews.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.zs.yzs.beijingnews.Pager.GovaffairPager;
import com.zs.yzs.beijingnews.Pager.HomePager;
import com.zs.yzs.beijingnews.Pager.NewsCenterPager;
import com.zs.yzs.beijingnews.Pager.SettingPager;
import com.zs.yzs.beijingnews.Pager.SmartServicePager;
import com.zs.yzs.beijingnews.R;
import com.zs.yzs.beijingnews.base.BaseFragment;
import com.zs.yzs.beijingnews.base.BasePager;
import com.zs.yzs.beijingnews.utlls.LogUtil;
import com.zs.yzs.beijingnews.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


/**
 *  正文 Fragment
 * Created by YZS on 2017/4/13.
 */

public class ContentFragment extends BaseFragment{
    //初始化控件
    @ViewInject(R.id.viewpager)     //找到控件ID
    private NoScrollViewPager viewpager;    //命名可变
    //初始化控件
    @ViewInject(R.id.rg_main)       //找到控件ID
    private RadioGroup rg_main;

    /**
     * 装5个页面的集合
     */
    private ArrayList<BasePager> basepagers;


    @Override
    public View initView() {
        LogUtil.e("正文Fragment视图被初始化了");
        View view = View.inflate(context, R.layout.content_fragment,null);
        /**
         * 使用xUtils初始化了控件就不需要findById
         */
        //        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        //        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);

        //把视图注入到框架中，让 ContentFragment.this 和 view 关联起来
        x.view().inject(ContentFragment.this,view);


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("正文Fragment数据被初始化了");

        //初始化五个页面 ，并且放进集合中
        basepagers = new ArrayList<>();

        basepagers.add(new HomePager(context));//主页面
        basepagers.add(new NewsCenterPager(context));//新闻中心页面
        basepagers.add(new SmartServicePager(context));//智慧指南中心页面
        basepagers.add(new GovaffairPager(context));//政要指南页面
        basepagers.add(new SettingPager(context));//设置页面


        //设置默认选中首页
        rg_main.check(R.id.rb_home);

        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragmentAdapter());

        //设置 RadioGroup的选中状态
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

    }

    /**
     * RadioGroup 选中状态监听
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         *
         * @param group  RadioGroup
         * @param checkedId  被选中的RadioButton 的ID
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                /**
                 *  默认有动画。。。FALSE 就是没有动画
                 */
                case R.id.rb_home://主页面RadioButton的ID
                    viewpager.setCurrentItem(0,false);
                    break;
                case R.id.rb_newscenter://新闻中心页面RadioButton的ID
                    viewpager.setCurrentItem(1, false);
                    break;
                case R.id.rb_smartservice://智慧服务页面RadioButton的ID
                    viewpager.setCurrentItem(2, false);
                    break;
                case R.id.rb_government://政要指南页面RadioButton的ID
                    viewpager.setCurrentItem(3, false);
                    break;
                case R.id.rb_setting://设置页面RadioButton的ID
                    viewpager.setCurrentItem(4, false);
                    break;
            }
        }
    }



    class ContentFragmentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return basepagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //各个页面的实例
            BasePager basepager = basepagers.get(position);
            View rootView = basepager.rootview;

            //调用各个页面的initData方法
            basepager.initData();//初始化数据

            container.addView(rootView);
            return rootView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除
            container.removeView((View) object);
        }
    }
}
