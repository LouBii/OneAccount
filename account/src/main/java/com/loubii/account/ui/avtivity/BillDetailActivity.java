package com.loubii.account.ui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.loubii.account.R;
import com.loubii.account.adapter.BillSwipeAdapter;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.Config;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.AccountModelDao;
import com.loubii.account.event.AccountChangeEvent;
import com.loubii.account.util.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BillDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ultimate_recycler_view)
    UltimateRecyclerView mUltimateRecyclerView;
    @BindView(R.id.ll_title_return)
    FrameLayout mLlTitleReturn;
    @BindView(R.id.rb_expend)
    RadioButton mRbExpend;
    @BindView(R.id.rb_income)
    RadioButton mRbIncome;
    @BindView(R.id.rg_type)
    RadioGroup mRgType;
    private BillSwipeAdapter mBillSwipeAdapter;
    private Date mDate;
    private List<AccountModel> mAccountList = new ArrayList<>();
    private int mAccountType;
    private boolean hasDelete = false; //是否有过删除操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        long time = getIntent().getLongExtra(Extra.ACCOUNT_DATE, 0l);
        mAccountType = getIntent().getIntExtra(Extra.ACCOUNT_TYPE, 0);
        if (time != 0l) {
            mDate = new Date(time);
            mAccountList.addAll(getAccountList(0, mDate, 1));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bill_detail;
    }

    @Override
    protected void initView() {
        setListener();
        initRecyclerView();
        initRadioGroup();
    }

    private void initRadioGroup() {
        if (mAccountType == 2)
            mRbIncome.setChecked(true);
    }

    private void setListener() {
        mRgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_expend:
                        if (mDate != null)
                            changeList(1);
                        break;
                    case R.id.rb_income:
                        if (mDate != null)
                            changeList(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void changeList(int outInType) {
        mAccountList.clear();
        mAccountList.addAll(getAccountList(0, mDate, outInType));
        mBillSwipeAdapter.notifyDataSetChanged();
        if (mAccountList.size() == 0) {
            mUltimateRecyclerView.showEmptyView();
        } else
            mUltimateRecyclerView.hideEmptyView();
    }


    private void initRecyclerView() {
        ScrollSmoothLineaerLayoutManager mLayoutManager = new ScrollSmoothLineaerLayoutManager(this, LinearLayoutManager.VERTICAL, false, 500);
        mUltimateRecyclerView.setLayoutManager(mLayoutManager);
        mBillSwipeAdapter = new BillSwipeAdapter(mAccountList);
        mBillSwipeAdapter.setMode(SwipeItemManagerInterface.Mode.Single);
        //mBillSwipeAdapter.set
        //悬浮停留头部布局需要加入
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mBillSwipeAdapter);
        mUltimateRecyclerView.addItemDecoration(headersDecor);
        //设置下拉刷新
        mUltimateRecyclerView.setDefaultSwipeToRefreshColorScheme(getResources().getColor(R.color.colorPrimary));
        mUltimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mUltimateRecyclerView.setAdapter(mBillSwipeAdapter);
        mUltimateRecyclerView.setEmptyView(R.layout.rv_empty_bill, UltimateRecyclerView.EMPTY_CLEAR_ALL);
        if (mAccountList.size() == 0)
            mUltimateRecyclerView.showEmptyView();

        mBillSwipeAdapter.setOnDeleteListener(new BillSwipeAdapter.OnDeleteListener() {
            @Override
            public void onDelete() {
                hasDelete = true;
            }
        });
    }

    /**
     * 分页查询
     *
     * @param offSet      设置开始页
     * @param currentDate
     */
    private List<AccountModel> getAccountList(int offSet, Date currentDate, int outInType) {
        List<AccountModel> accountList = mDbManager.queryBuilder()
                .where(AccountModelDao.Properties.Time.between
                                (TimeUtil.getFirstDayOfMonth(currentDate), TimeUtil.getEndDayOfMonth(currentDate)),
                        AccountModelDao.Properties.OutIntype.eq(outInType))
                .orderAsc(AccountModelDao.Properties.Time)
                .offset(offSet * Config.LIST_LOAD_NUM)
                .limit(Config.LIST_LOAD_NUM)
                .list();
        return accountList;
    }

    @OnClick(R.id.ll_title_return)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (hasDelete == true) {
            Intent intent = new Intent();
            intent.putExtra(Extra.ACCOUNT_HAS_CHANGE, true);
            //消息推送
            EventBus.getDefault().post(new AccountChangeEvent(intent));
        }
        super.onBackPressed();
    }
}
