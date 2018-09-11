package com.loubii.account.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.loubii.account.R;
import com.loubii.account.adapter.CenterAdapter;

import butterknife.BindView;

/**
 * 个人中心
 */
public class FragmentCenter extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rv_mine)
    RecyclerView mRvMine;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    private String mParam1;


    public FragmentCenter() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    mCollapsingToolbar.setTitle("我的");
                    //Logger.e("折叠状态");
                } else
                    mCollapsingToolbar.setTitle("");
                    //Logger.e("展开状态");
            }
        });
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRvMine.setLayoutManager(linearLayoutManager);
        mRvMine.setAdapter(new CenterAdapter(context));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }


}
