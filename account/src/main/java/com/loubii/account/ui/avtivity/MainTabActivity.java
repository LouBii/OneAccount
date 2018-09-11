package com.loubii.account.ui.avtivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.ui.MainTab;
import com.loubii.account.view.MyFragmentTabHost;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainTabActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    @BindView(R.id.tab_content)
    FrameLayout mTabContent;
    @BindView(R.id.tab_host)
    MyFragmentTabHost mTabHost;
    @BindView(R.id.iv_add_bill)
    ImageView mIvAddBill;

    private String mNoTabChangeFlag;
    private String mLastTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        initTabHost();
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_icon);

            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            //title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
                mNoTabChangeFlag = getString(mainTab.getResName());
                //mTabHost.setNoTabChangedTag(getString(mainTab.getResName()));
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainTabActivity.this);
                }
            });
            mTabHost.addTab(tab, mainTab.getClz(), null);

            //mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
//        if (tabId.equals(mNoTabChangeFlag))
//            mTabHost.setCurrentTabByTag(mLastTag);
//        else
//            mLastTag = tabId;
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        //mTitle = tabId;
        supportInvalidateOptionsMenu();
    }

    @OnClick(R.id.iv_add_bill)
    public void onViewClicked() {
        startActivity(new Intent(this, AddAccountActivity.class));
    }
}
