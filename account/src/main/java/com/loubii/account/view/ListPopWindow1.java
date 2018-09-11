package com.loubii.account.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.loubii.account.util.DensityUtil;

/**
 * @author luo
 * @date 2017/9/18
 */
public class ListPopWindow1 extends PopupWindow implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener {
    private Activity mContext;

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

    public ListPopWindow1(@NonNull Context context) {
        super(context);
        this.mContext = (Activity) context;
        setWidth(DensityUtil.screenWidth()/2);
        //setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        initView();
        setOutsideTouchable(true);
        setOnDismissListener(this);
        setBackgroundDrawable(null);
    }

    private void initView() {
        LinearLayout layout = new LinearLayout(mContext);
        LayoutParams paramsParent = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(paramsParent);

        ListView listView = new ListView(mContext);
        LinearLayout.LayoutParams paramsListView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(paramsListView);
        listView.setDivider(null);
        listView.setBackgroundColor(Color.WHITE);
        listView.setId(1);
        layout.addView(listView);
        listView.setOnItemClickListener(this);
        setContentView(layout);
    }

    /**
     * 设置适配器
     * @param listAdapter
     */
    public void setAdapter(ListAdapter listAdapter) {
        ListView listView = (ListView) getContentView().findViewById(1);
        listView.setAdapter(listAdapter);

        int itemHeigh = DensityUtil.dip2px(50);
        int height;
        if(listAdapter.getCount() > 5){
            height = (int)itemHeigh * 5;
        }else {
            height = (int)itemHeigh * listAdapter.getCount();
        }
        setHeight(height);
    }

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
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        backgroundAlpha(0.7f);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
