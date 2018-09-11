package com.loubii.account.ui.card;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.constants.Extra;
import com.loubii.account.ui.avtivity.BaseActivity;
import com.loubii.account.view.BaseToolbar;

import butterknife.BindView;

public class CardDetailActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    BaseToolbar mToolbar;
    @BindView(R.id.tv_card_depost_describe)
    TextView mTvCardDepostDescribe;
    @BindView(R.id.lin_card_credit_describe)
    LinearLayout mLinCardCreditDescribe;
    @BindView(R.id.tv_transfer)
    TextView mTvTransfer;
    @BindView(R.id.iv_alarm)
    ImageView mIvAlarm;
    private int mCardType;

    @Override
    protected void initData() {
        mCardType = getIntent().getIntExtra(Extra.CARD_TYPE, -1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void initView() {
        initLayout();
    }

    private void initLayout() {
        mTvTransfer.setVisibility(View.GONE);
        mIvAlarm.setVisibility(View.GONE);
        if (mCardType == Extra.CARD_DEPOSIT) {
            // mLinCardCreditDescribe.setVisibility(View.GONE);
            mTvCardDepostDescribe.setVisibility(View.VISIBLE);
        } else if (mCardType == Extra.CARD_CREDIT) {
            //mTvCardDepostDescribe.setVisibility(View.GONE);
            mLinCardCreditDescribe.setVisibility(View.VISIBLE);
        }
    }

    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            //返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.mipmap.ic_toolbar_left);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mCardType == Extra.CARD_DEPOSIT) {
            mToolbar.setCenterTitle("我的储蓄卡");
            mToolbar.setSettingText("换卡");
        } else if (mCardType == Extra.CARD_CREDIT) {
            mToolbar.setCenterTitle("我的信用卡");
            mToolbar.setSettingText("解绑");
        }

        mToolbar.setOnSettingTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
