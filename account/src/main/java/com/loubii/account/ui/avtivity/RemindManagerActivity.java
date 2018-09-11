package com.loubii.account.ui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.loubii.account.R;
import com.loubii.account.ui.card.CardRemindActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RemindManagerActivity extends BaseToolBarActivity {

    @BindView(R.id.rel_remind_account)
    RelativeLayout mRelRemindAccount;
    @BindView(R.id.rel_remind_credit)
    RelativeLayout mRelRemindCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remind_manager;
    }

    @Override
    protected void initView() {
        setTitle("提醒设置");
    }

    @OnClick({R.id.rel_remind_account, R.id.rel_remind_credit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_remind_account:
                startActivity(new Intent(mContext, AccountRemindActivity.class));
                break;
            case R.id.rel_remind_credit:
                startActivity(new Intent(mContext, CardRemindActivity.class));
                break;
        }
    }
}
