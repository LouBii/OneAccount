package com.loubii.account.ui.card;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.ui.avtivity.BaseToolBarActivity;
import com.loubii.account.view.DeleteEditText;

import butterknife.BindView;

public class TransferActivity extends BaseToolBarActivity {

    @BindView(R.id.tv_account)
    TextView mTvAccount;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_transfer_count)
    DeleteEditText mEtTransferCount;
    @BindView(R.id.rl_phone_num)
    RelativeLayout mRlPhoneNum;
    @BindView(R.id.tv_tips_service_count)
    TextView mTvTipsServiceCount;
    @BindView(R.id.bt_next)
    Button mBtNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @Override
    protected void initView() {

    }
}
