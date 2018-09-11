package com.loubii.account.ui.avtivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.loubii.account.R;

import butterknife.BindView;

/**
 * 预算设置
 */
public class BudgetActivity extends BaseToolBarActivity {

    @BindView(R.id.switch_button)
    SwitchButton mSwitchButton;
    @BindView(R.id.et_count)
    EditText mEtCount;
    @BindView(R.id.rl_budget)
    RelativeLayout mRlBudget;
    @BindView(R.id.tv_budget_describe)
    TextView mTvBudgetDescribe;

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_budget;
    }

    @Override
    protected void initView() {
        setTitle("预算设置");
        setSwitchButton();
    }

    private void setSwitchButton() {
        mSwitchButton.setShadowEffect(false);
        mSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    if (mRlBudget.getVisibility() == View.GONE)
                        mRlBudget.setVisibility(View.VISIBLE);
                    if (mTvBudgetDescribe.getVisibility() == View.GONE)
                        mTvBudgetDescribe.setVisibility(View.VISIBLE);
                } else {
                    if (mRlBudget.getVisibility() == View.VISIBLE)
                        mRlBudget.setVisibility(View.GONE);
                    if (mTvBudgetDescribe.getVisibility() == View.VISIBLE)
                        mTvBudgetDescribe.setVisibility(View.GONE);
                }

            }
        });
    }


}
