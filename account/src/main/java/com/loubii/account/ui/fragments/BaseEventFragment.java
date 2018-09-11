package com.loubii.account.ui.fragments;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * @author luo
 * @date 2017/9/4
 */
public abstract class BaseEventFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerEvent();
        //Eventbus2是反射取Class，EventBus3是注解apt，子类不会被加入
        //注册事件
        EventBus.getDefault().register(this);
        //Logger.e(this.getClass().toString());
    }

    //protected abstract void registerEvent();


    @Override
    protected void init(View view) {
        super.init(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unRegisterEvent();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    //protected abstract void unRegisterEvent();
//
//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onEvent(BaseEvent event) {
//
//    }


}
