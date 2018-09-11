package com.loubii.account.ui.avtivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author luo
 * @date 2017/9/4
 */
public abstract class BaseEventActivity extends BaseActivity {

    @Override
    protected void init() {
        super.init();
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    protected abstract <T> void onReceiveEvent(T event) ;
}
