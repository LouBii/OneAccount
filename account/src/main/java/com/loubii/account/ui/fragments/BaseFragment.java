package com.loubii.account.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.db.database.DbHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    public FragmentActivity context;
    public DBManager<AccountModel, Long> mDbManager;
    public boolean mIsVisible = false;
    private View mRootView;
    private Dialog mProgressDialog;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mDbManager = DbHelper.getInstance().author();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        init(mRootView);
        //if ( !mIsVisible) {
            initData();
            initView(mRootView);
        //}
        return mRootView;
    }

    /**
     * 初始化一些插件
     * @param view
     */
    protected void init(View view) {
        unbinder = ButterKnife.bind(this, view);
        initDialog();
    }

    private void initDialog() {
        mProgressDialog = new Dialog(context, R.style.progress_dialog);
        mProgressDialog.setContentView(R.layout.dialog_progress);
        mProgressDialog.setCancelable(true);
        mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        TextView msg = (TextView) mProgressDialog.findViewById(R.id.id_tv_loadingmsg);
//        msg.setText("卖力加载中");

    }

    protected abstract void initData();

    /**
     * 初始化
     * @param view
     */
    protected abstract void initView(View view);


    protected void loadData() {
    }
    /**
     * @return 当前界面的布局id
     */
    protected abstract int getLayoutId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //System.out.println("Infor_Fragment----setUserVisibleHint");
        if (getUserVisibleHint()) {
            mIsVisible = true;
            loadData();
//            initData();
//            initView(mRootView);
        } else {
            mIsVisible = false;
            //可以做一些销毁的操作。
        }
    }

}
