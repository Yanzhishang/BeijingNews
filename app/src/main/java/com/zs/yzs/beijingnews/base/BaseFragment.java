package com.zs.yzs.beijingnews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基本的 Fragment
 *   LeftMenuFragment 和 ContentFragment 将继承它
 * Created by YZS on 2017/4/13.
 */

public abstract class BaseFragment extends Fragment{

    public Activity context;    //MainActivity


    /**
     *  当Fragment 被创建的时候被回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }

    /**
     *  当视图被创建的时候回调
     * @param inflater
     * @param container
     * @param savedInstanceState
     *  创建了视图
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return initView();
    }

    /**
     * 此方法让子类实现自己的视图，达到自己特有的效果
     * @return
     */
    public abstract View initView();

    /**
     *  当Activity 被创建之后被回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     *  1、如果子页面没有数据，可以联网去请求数据，并且可以绑定到 initView 初始化的视图上
     *  2、绑定到 initView 初始化的视图上
     *
     */
    public void initData(){

    }
}
