package com.loubii.account.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.loubii.account.util.DensityUtil;

/**
 * @author luo
 * @date 2017/9/18
 */
public class ListPopWindow extends ListPopupWindow implements PopupWindow.OnDismissListener {
    private Activity mContext;
    public static int ITEM_HEIGHT = DensityUtil.dip2px(45);
    public ListPopWindow(@NonNull Context context, int size) {
        super(context);
        this.mContext = (Activity) context;
        setWidth(DensityUtil.screenWidth()/2);
        setHeight(getTotleHeight(size));
        //获取焦点
        setModal(true);
        initView();
        setOnDismissListener(this);
        //setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.classify_baby));
    }

    private int getTotleHeight(int size) {
        int height;
        if(size > 5){
            height = ITEM_HEIGHT * 5;
        }else {
            height = ITEM_HEIGHT * size;
        }
        return height;
    }

    private void initView() {

    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    backgroundAlpha((float)msg.obj);
                    break;
            }
        }
    };


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void show() {
        super.show();
        //Logger.e( "height:  " + getHeight());
        backgroundAlpha(0.7f);
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                while(alpha>0.5f){
//                    try {
//                        //4是根据弹出动画时间和减少的透明度计算
//                        Thread.sleep(4);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Message msg =mHandler.obtainMessage();
//                    msg.what = 1;
//                    //每次减少0.01，精度越高，变暗的效果越流畅
//                    alpha-=0.01f;
//                    msg.obj =alpha ;
//                    mHandler.sendMessage(msg);
//                }
//            }
//
//        }).start();
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                //此处while的条件alpha不能<= 否则会出现黑屏
//                while(alpha<1f){
//                    try {
//                        Thread.sleep(4);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d("HeadPortrait","alpha:"+alpha);
//                    Message msg =mHandler.obtainMessage();
//                    msg.what = 1;
//                    alpha+=0.01f;
//                    msg.obj =alpha ;
//                    mHandler.sendMessage(msg);
//                }
//            }
//
//        }).start();
    }

}
