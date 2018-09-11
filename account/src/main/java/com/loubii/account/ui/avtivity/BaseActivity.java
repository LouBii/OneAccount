package com.loubii.account.ui.avtivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.db.database.DbHelper;

import butterknife.ButterKnife;

/**
 * @author luo
 * @date 2017/8/9
 */
public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity mContext;
    public DBManager<AccountModel, Long> mDbManager;
    private Dialog mProgressDialog;
    private AlertDialog.Builder mAlertDialogBuilder;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.mContext = this;
        init();
        initData();
        initToolBar();
        initView();
    }

    protected abstract void initData();

    /**
     * 初始化一些插件
     */
    protected void init() {
        ButterKnife.bind(this);
        mDbManager = DbHelper.getInstance().author();
        initDialog();

    }

    private void initDialog() {
        mProgressDialog = new Dialog(this, R.style.progress_dialog);
        mProgressDialog.setContentView(R.layout.dialog_progress);
        mProgressDialog.setCancelable(true);
        mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        TextView msg = (TextView) mProgressDialog.findViewById(R.id.id_tv_loadingmsg);
//        msg.setText("卖力加载中");

        mAlertDialogBuilder = new AlertDialog.Builder(this);
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

    protected void showAlertDialog(String title, String message,
                                   String negativeButton, String positiveButton,
                                   DialogInterface.OnClickListener positiveListener) {
        if (!TextUtils.isEmpty(title))
            mAlertDialogBuilder.setTitle(title);
        mAlertDialogBuilder.setMessage(message);
        if (!TextUtils.isEmpty(negativeButton))
            mAlertDialogBuilder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAlertDialog.dismiss();
                }
            });
        if (!TextUtils.isEmpty(positiveButton))
            mAlertDialogBuilder.setPositiveButton(positiveButton, positiveListener);
        mAlertDialog = mAlertDialogBuilder.show();
    }

    protected void showAlertDialog(String message, DialogInterface.OnClickListener positiveListener) {
        showAlertDialog("",message, "取消", "确定",  positiveListener);
    }

    protected void dismissAlertDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    /**
     * @return 当前界面的布局id
     */
    protected abstract int getLayoutId();

    /**
     * @return 初始化标题栏
     */
    protected void initToolBar() {
    }

    /**
     * @return 初始化
     */
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        dismissAlertDialog();
        dismissProgressDialog();
        super.onDestroy();
    }
}
