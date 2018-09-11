package com.loubii.account.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.database.DbHelper;
import com.loubii.account.event.ChartClassifyEvent;
import com.loubii.account.ui.dialog.ListPopWindow;
import com.loubii.account.ui.fragments.adapter.BaseFragmentPagerAdapter;
import com.loubii.account.ui.fragments.chart.ChartPopWindowAdapter;
import com.loubii.account.util.AccListUtil;
import com.loubii.account.util.DensityUtil;
import com.loubii.account.view.SliderLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图表
 */
public class FragmentChart extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ll_title_return)
    FrameLayout mLlTitleReturn;
    @BindView(R.id.rb_expend)
    RadioButton mRbExpend;
    @BindView(R.id.rb_income)
    RadioButton mRbIncome;
    @BindView(R.id.vp_chart)
    ViewPager mVpChart;
    @BindView(R.id.tab_year_month)
    TabLayout mTabYearMonth;
    //    @BindView(R.id.tab_period)
//    TabLayout mTabPeriod;
    @BindView(R.id.slider_layout)
    SliderLayout mSliderLayout;
    @BindView(R.id.tv_classify)
    TextView mTvClassify;
    private ListPopWindow mListPopWindow;
    private List<AccountModel> mPopData = new ArrayList<>();
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<ChartTypeFragment> mFragmentList = new ArrayList<>();
    private int mAccountType = Extra.ACCOUNT_TYPE_EXPEND;

    private String mDetailType = Extra.DETAIL_TYPE_DEFAULT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mListPopWindow != null && mListPopWindow.isShowing()) {
                        mListPopWindow.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initData() {
        initPopData();
        initChartData();
    }

    private void initChartData() {
        mTitleList.clear();
        mFragmentList.clear();
        mTitleList.add("周");
        mTitleList.add("月");
        mTitleList.add("年");

        mFragmentList.add(ChartTypeFragment.newInstance(ChartTypeFragment.TYPE_WEEK));
        mFragmentList.add(ChartTypeFragment.newInstance(ChartTypeFragment.TYPE_MONTH));
        mFragmentList.add(ChartTypeFragment.newInstance(ChartTypeFragment.TYPE_YEAR));
    }

    private void initPopData() {
        mPopData.clear();
        addHeaderToPop();

        Date maxDate = DbHelper.getInstance().getMaxDate();
        Date minDate = DbHelper.getInstance().getMinDate();
        if (minDate != null && maxDate != null) {
            List<AccountModel> accountList = DbHelper.getInstance().getAccountList(mAccountType, minDate, maxDate);
            mPopData.addAll(AccListUtil.removeRepeat(accountList));
        }
    }

    private void addHeaderToPop() {
        AccountModel bean = new AccountModel();
        bean.setDetailType("全部");
        bean.setPicRes(R.drawable.classify_baby);
        mPopData.add(bean);
    }

    @Override
    protected void initView(View view) {
        mLlTitleReturn.setVisibility(View.GONE);
        initViewPager();
    }

    private void initViewPager() {

        // 注意使用的是：getSupportFragmentManager
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mVpChart.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        mVpChart.setOffscreenPageLimit(1);
        //vpContent.addOnPageChangeListener(this);
        mVpChart.setCurrentItem(0);
        //adapter.notifyDataSetChanged();
        mTabYearMonth.setTabMode(TabLayout.MODE_FIXED);
        mTabYearMonth.setupWithViewPager(mVpChart);
        //mVpChart.clearOnPageChangeListeners();

        mVpChart.addOnPageChangeListener(new SliderLayout.SliderOnPageChangeListener(mTabYearMonth, mSliderLayout));
//        mVpChart.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                //Logger.e(position + "");mFragmentList.get(position).getTypeListData()
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }


    @OnClick(R.id.tv_classify)
    public void onViewClicked() {

//        ListPopupWindow listPopupWindow =new ListPopupWindow(context);
//
//        listPopupWindow.setWidth(400);//设置宽度
//
//        listPopupWindow.setHeight(ListPopupWindow.MATCH_PARENT);//设置高度
//        listPopupWindow.setAnchorView(mTvClassify);
//        listPopupWindow.setAdapter(new ChartPopWindowAdapter(context, mPopData));
//        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Logger.e("click " + position);
//            }
//        });
//        listPopupWindow.show();

        if (mPopData == null || mPopData.size() < 2)
            return;
        if (mListPopWindow != null && mListPopWindow.isShowing())
            return;
        mListPopWindow = new ListPopWindow(context, mPopData.size());
        mListPopWindow.setAnchorView(mTvClassify);
        mListPopWindow.setHorizontalOffset(DensityUtil.dip2px(5));
        mListPopWindow.setAdapter(new ChartPopWindowAdapter(context, mPopData));
        mListPopWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Logger.e("click " + position);
                mTvClassify.setText(mPopData.get(position).getDetailType());
                mListPopWindow.dismiss();

                //发送消息通知chartdetailfragment
                String message;
                if (position == 0)
                    message = Extra.DETAIL_TYPE_DEFAULT;
                else
                    message = mPopData.get(position).getDetailType();
                if (mDetailType.equals(message))
                    return;
                mDetailType = message;
                EventBus.getDefault().post(new ChartClassifyEvent(message));
            }
        });
        mListPopWindow.show();
    }

    public String getDetailType() {
        return mDetailType;
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onEvent(List<AccountModel> chartList) {
//        //Logger.e("收到eventbus");
//    }
}
