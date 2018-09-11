package com.loubii.account.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.adapter.BillAdapter;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.Config;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.AccountModelDao;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.db.database.DbHelper;
import com.loubii.account.event.AccountChangeEvent;
import com.loubii.account.ui.avtivity.BillDetailActivity;
import com.loubii.account.ui.avtivity.BudgetActivity;
import com.loubii.account.ui.avtivity.CalendarActivity;
import com.loubii.account.ui.avtivity.NewContractActivity;
import com.loubii.account.util.TimeUtil;
import com.loubii.account.util.ToastUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账单
 */
public class FragmentBill extends BaseEventFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final int CONTRACT_NOT = 0;
    private static final int CONTRACT_ING = 1;
    private static final int CONTRACT_FINISH = 2;
    private boolean mIsFirst = true;
    @BindView(R.id.tv_budget_month)
    TextView mTvBudgetMonth;
    @BindView(R.id.tv_budget_month_describe)
    TextView mTvBudgetMonthDescribe;
    @BindView(R.id.tv_expend)
    TextView mTvExpend;
    @BindView(R.id.tv_expend_describe)
    TextView mTvExpendDescribe;
    @BindView(R.id.tv_income)
    TextView mTvIncome;
    @BindView(R.id.tv_income_describe)
    TextView mTvIncomeDescribe;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_title_contract)
    FrameLayout mLlTitleContract;
    @BindView(R.id.tv_title_time)
    TextView mTvTitleTime;
    @BindView(R.id.ll_title_left)
    FrameLayout mLlTitleLeft;
    @BindView(R.id.ll_title_right)
    FrameLayout mLlTitleRight;
    @BindView(R.id.ultimate_recycler_view)
    UltimateRecyclerView mUltimateRecyclerView;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;

    private String mParam1;
    private BillAdapter mBillAdapter;
    private DBManager<AccountModel, Long> mDbManager;
    private List<AccountModel> mAccountList = new ArrayList<>();
    private Date mCurrentDate;


    public FragmentBill() {
        // Required empty public constructor
    }


    public static FragmentBill newInstance(String param1) {
        FragmentBill fragment = new FragmentBill();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbManager = DbHelper.getInstance().author();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bill;
    }

    @Override
    protected void initData() {
        initTitleData();
    }

    private void initTitleData() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        mTvTitleTime.setText(format.format(calendar.getTime()));
        mCurrentDate = calendar.getTime();
        //mDbManager.queryRaw("where AGE>?","10");
//        String first = TimeUtil.date2String(TimeUtil.getFirstDayOfMonth(mCurrentDate), "yyyy-MM-dd日   HH:mm");
//        String end = TimeUtil.date2String(TimeUtil.getLastDayOfMonth(mCurrentDate), "yyyy-MM-dd日   HH:mm");
//        Logger.e("本月第一天：" + first);
//        Logger.e("本月最末天：" + end);
        mAccountList.addAll(getAccountList(0, mCurrentDate));
    }

    @Override
    protected void initView(View view) {
        initToolbar();
        setTitleView();
        initRecycleView();
    }


    private void setTitleView() {
        mTvExpendDescribe.setText(mCurrentDate.getMonth() + 1 + "月支出");
        mTvIncomeDescribe.setText(mCurrentDate.getMonth() + 1 + "月收入");
        mTvBudgetMonthDescribe.setText(mCurrentDate.getMonth() + 1 + "月预算");
        if (mAccountList.size() > 0) {
            float sumExpend = 0f;
            float sumIncome = 0f;
            for (AccountModel accountModel : mAccountList) {
                int type = accountModel.getOutIntype();
                if (type == 1) //支出
                    sumExpend += accountModel.getCount();
                if (type == 2) //收入
                    sumIncome += accountModel.getCount();
            }
            mTvExpend.setText(String.valueOf(sumExpend));
            mTvIncome.setText(String.valueOf(sumIncome));
            //mTvBudgetMonth.setText();
        } else {
            mTvExpend.setText("——");
            mTvIncome.setText("——");
        }
    }

    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mUltimateRecyclerView.setLayoutManager(linearLayoutManager);
        mBillAdapter = new BillAdapter(mAccountList);
        mBillAdapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, CalendarActivity.class);
                intent.putExtra(Extra.ACCOUNT_DATE, mCurrentDate.getTime());
                getActivity().startActivity(intent);
                //ToastUtil.showShort(getActivity(), position + "");
            }
        });

        //悬浮头部布局需要加入
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mBillAdapter);
        mUltimateRecyclerView.addItemDecoration(headersDecor);
        //设置下拉刷新
        mUltimateRecyclerView.setDefaultSwipeToRefreshColorScheme(getResources().getColor(R.color.colorPrimary));
        mUltimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeList(0);
                        setTitleView();
                        ToastUtil.showShort("数据已更新");
                        //linearLayoutManager.scrollToPosition(0);
                    }
                }, 1000);
            }
        });
        mUltimateRecyclerView.setAdapter(mBillAdapter);
        mUltimateRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //如果滑动到第一条且完全可见则展开appbarLayout
                    int visiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if (visiblePosition == 0) {
                        mAppBar.setExpanded(true);
                    }
                }
            }
        });
        mUltimateRecyclerView.setEmptyView(R.layout.rv_empty_bill, UltimateRecyclerView.EMPTY_CLEAR_ALL);
        if (mAccountList.size() == 0)
            mUltimateRecyclerView.showEmptyView();

        mIsFirst = false;
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    /**
     * 分页查询
     *
     * @param offSet      设置开始页
     * @param currentDate
     */
    private List<AccountModel> getAccountList(int offSet, Date currentDate) {
        List<AccountModel> accountList = mDbManager.queryBuilder()
                .where(AccountModelDao.Properties.Time.between
                        (TimeUtil.getFirstDayOfMonth(currentDate), TimeUtil.getEndDayOfMonth(currentDate)))
                .orderAsc(AccountModelDao.Properties.Time)
                .offset(offSet * Config.LIST_LOAD_NUM)
                .limit(Config.LIST_LOAD_NUM)
                .list();
        return accountList;
    }


    @OnClick({R.id.ll_title_contract, R.id.tv_title_time, R.id.ll_title_left, R.id.ll_title_right,
            R.id.ll_expend_detail, R.id.ll_income_detail, R.id.tv_budget_month, R.id.tv_budget_month_describe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_contract:
                if (getContractState() == CONTRACT_NOT)
                    startActivity(new Intent(context, NewContractActivity.class));
                if (getContractState() == CONTRACT_ING)
                    startActivity(new Intent(context, CalendarActivity.class));
                if (getContractState() == CONTRACT_FINISH)
                    //startActivity(new Intent(context, CalendarContractActivity.class));
                    break;
            case R.id.tv_title_time:
                break;
            case R.id.ll_title_left:
                changeList(-1);
                setTitleView();
                //Logger.e(mAccountList.size() + "");
                break;
            case R.id.ll_title_right:
                changeList(1);
                setTitleView();
                break;
            case R.id.ll_expend_detail:
                Intent intent = new Intent(context, BillDetailActivity.class);
                intent.putExtra(Extra.ACCOUNT_DATE, mCurrentDate.getTime());
                intent.putExtra(Extra.ACCOUNT_TYPE, 1);
                startActivity(intent);
                break;
            case R.id.ll_income_detail:
                Intent intentIn = new Intent(context, BillDetailActivity.class);
                intentIn.putExtra(Extra.ACCOUNT_DATE, mCurrentDate.getTime());
                intentIn.putExtra(Extra.ACCOUNT_TYPE, 2);
                startActivity(intentIn);
                break;
            case R.id.tv_budget_month:
                startActivity(new Intent(context, BudgetActivity.class));
                break;
            case R.id.tv_budget_month_describe:
                startActivity(new Intent(context, BudgetActivity.class));
                break;
        }
    }

    /**
     * 前后一个月数据
     *
     * @param monthDistance example ：-1为前一月， 1为后一月
     */
    private void changeList(int monthDistance) {
        //当前月
        if (monthDistance > 0 && TimeUtil.date2String(new Date(), "yy年MM月")
                .equals(TimeUtil.date2String(mCurrentDate, "yy年MM月")))
            return;
        if (monthDistance != 0) {
            mCurrentDate = TimeUtil.getMonthAgo(mCurrentDate, monthDistance);
            mTvTitleTime.setText(TimeUtil.date2String(mCurrentDate, "yy年MM月"));
        }
        List<AccountModel> accList = getAccountList(0, mCurrentDate);
        if (accList != null) {
            mAccountList.clear();
            mAccountList.addAll(accList);
            //mBillAdapter.onBindHeaderViewHolder();
            mBillAdapter.notifyDataSetChanged();
            if (accList.size() == 0) {
                if (mUltimateRecyclerView != null)
                    mUltimateRecyclerView.showEmptyView();
            } else {
                if (mUltimateRecyclerView != null)
                    mUltimateRecyclerView.hideEmptyView();
            }
        }
    }

    /**
     * TODO
     * 获取契约状态
     *
     * @return 0：尚未签订契约  1：契约期间  2：本期契约成功
     */
    private int getContractState() {
        return CONTRACT_ING;
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(AccountChangeEvent event) {
        Intent intent = event.getMessage();
        boolean hasChange = intent.getBooleanExtra(Extra.ACCOUNT_HAS_CHANGE, false);
        if (hasChange) {
            changeList(0);
            setTitleView();
        }
    }
}
