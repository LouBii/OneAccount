package com.loubii.account.ui.avtivity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.loubii.account.R;

import butterknife.BindView;

/**
 * @author luo
 * @date 2017/8/9
 * in xml add: <include layout="@layout/layout_toolbar_header_title"/>
 */
public abstract class BaseToolBarActivity extends BaseActivity {
    @BindView(R.id.tb_base_title)
    public Toolbar mTbBaseTitle;


    protected void initToolBar() {
        setSupportActionBar(mTbBaseTitle);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.mipmap.ic_toolbar_left);
        }
        // 手动设置才有效果
        mTbBaseTitle.setTitleTextAppearance(this, R.style.ToolBar_Title);
        mTbBaseTitle.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);
        //        mTbBaseTitle.inflateMenu(R.menu.base_header_menu);
        //        mTbBaseTitle.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        mTbBaseTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //        mTbBaseTitle.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
        //            @Override
        //            public boolean onMenuItemClick(MenuItem item) {
        //                switch (item.getItemId()) {
        //                    case R.id.actionbar_more:// 更多信息
        //                        setTitleClickMore();
        //                        break;
        //                }
        //                return false;
        //            }
        //        });
    }

    /**
     * 1. 标题
     */
    public void setTitle(CharSequence text) {
        mTbBaseTitle.setTitle(text);
    }

    /**
     * 2. 副标题
     */
    protected void setSubTitle(CharSequence text) {
        mTbBaseTitle.setSubtitle(text);
    }

}
