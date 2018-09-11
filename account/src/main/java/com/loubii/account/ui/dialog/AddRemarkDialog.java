package com.loubii.account.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.adapter.AddRemarkAdapter;
import com.loubii.account.bean.RemarkBean;

import java.util.ArrayList;
import java.util.List;


public class AddRemarkDialog extends Dialog implements
        View.OnClickListener {

    private static final String TEXT_USER_DEFINE = "#添加标签#";
    private static final int MAX_SIZE = 10;
    private EditText mEtRemark;
    private AddRemarkAdapter mRemarkAdapter;

    private String mCurrentText;
    private List<RemarkBean> mRemarkBeanList;
    private OnFinishListener mFinishListener;


    private AddRemarkDialog(Context context, boolean flag, OnCancelListener listener) {
        super(context, flag, listener);
    }

    private AddRemarkDialog(final Context context, int defStyle) {
        super(context, defStyle);
        View contentView = View.inflate(context, R.layout.dialog_add_remark, null);
        mEtRemark = (EditText) contentView.findViewById(R.id.et_remark);
        //etRemark.setImeActionLabel("", EditorInfo.IME_ACTION_UNSPECIFIED);
        mEtRemark.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    String etStr = mEtRemark.getText().toString().trim();
                    //添加标签模式
                    if (etStr.startsWith("#") && etStr.endsWith("#") && etStr.length() > 1) {
                        if (etStr.substring(1, etStr.length() - 1).length() > 0) {
                            //最多显示两排标签,否则移除最后一个标签，添加标签到第一个
                            if (mRemarkBeanList.size() == MAX_SIZE)
                                mRemarkBeanList.remove(mRemarkBeanList.size() - 2);
                            RemarkBean remarkBean = new RemarkBean();
                            remarkBean.setName(etStr.substring(1, etStr.length() - 1));
                            remarkBean.setChecked(false);
                            mRemarkBeanList.add(0, remarkBean);
                            mRemarkAdapter.notifyDataSetChanged();
                            // TODO: 2017/8/28 保存至数据库
                        }
                        if (!TextUtils.isEmpty(mCurrentText))
                            mEtRemark.setText(mCurrentText);
                        else {
                            mEtRemark.setText("");
                            mEtRemark.setHint(context.getResources().getString(R.string.hint_add_remark));
                        }
                    } else {
                        mCurrentText = mEtRemark.getText().toString().trim();
                        List<RemarkBean> beanList = new ArrayList<>();
                        for (RemarkBean remarkBean : mRemarkBeanList) {
                            if (remarkBean.isChecked() == true)
                                beanList.add(remarkBean);
                        }
                        if (mFinishListener != null)
                            mFinishListener.onFinish(mCurrentText, beanList);
                        dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        RecyclerView mRvRemark = (RecyclerView) contentView.findViewById(R.id.rv_remark);
        initRecycleView(context, mRvRemark);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(contentView);

    }

    private void initRecycleView(Context context, RecyclerView rvRemark) {
        GridLayoutManager manager = new GridLayoutManager(context, 5);
        rvRemark.setLayoutManager(manager);
        mRemarkAdapter = new AddRemarkAdapter(context, getRecycleData());
        mRemarkAdapter.setOnEndClickListener(new AddRemarkAdapter.OnEndClickListener() {
            @Override
            public void onEndClick() {
                if (!mEtRemark.getText().toString().trim().startsWith("#")
                        && !mEtRemark.getText().toString().trim().endsWith("#"))
                    mCurrentText = mEtRemark.getText().toString().trim();
                mEtRemark.setText(TEXT_USER_DEFINE);
                mEtRemark.setSelection(1, mEtRemark.getText().toString().length() - 1);
            }
        });
        rvRemark.setAdapter(mRemarkAdapter);
    }

    private List<RemarkBean> getRecycleData() {
        if (mRemarkBeanList == null) {
            mRemarkBeanList = new ArrayList<>();
            for (int i = 0; i < 9 + 1; i++) {
                RemarkBean remarkBean = new RemarkBean();
                remarkBean.setId(i);
                remarkBean.setName("测试" + i);
                if (i == 9)
                    remarkBean.setName("    +    ");
                remarkBean.setChecked(false);
                mRemarkBeanList.add(remarkBean);
            }
        }
        return mRemarkBeanList;
    }

    public AddRemarkDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }



    @Override
    public void onClick(View v) {
//        dismiss();
    }

    public void setRemarkList(List<RemarkBean> remarkList) {
        this.mRemarkBeanList = remarkList;
    }

    public interface OnFinishListener {
        void onFinish(String remarkStr, List<RemarkBean> remarkList);
    }

    public void setOnFinishListener(OnFinishListener lis) {
        mFinishListener = lis;
    }
}