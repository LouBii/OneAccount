package com.loubii.account.ui.fragments.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.beiing.leafchart.SlideSelectLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.support.OnPointSelectListener;
import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.ChartConfig;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.AccountModelDao;
import com.loubii.account.db.database.DbHelper;
import com.loubii.account.event.ChartClassifyEvent;
import com.loubii.account.ui.fragments.BaseEventFragment;
import com.loubii.account.ui.fragments.ChartTypeFragment;
import com.loubii.account.ui.fragments.FragmentChart;
import com.loubii.account.util.AccListUtil;
import com.loubii.account.util.NumUtil;
import com.loubii.account.util.TimeUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author luo
 * @date 2017/9/15
 */
public class ChartDetailFragment extends BaseEventFragment {

    private static final String TIME_TYPE = "TIME_TYPE";
    private static final String END_TIME = "END_TIME";
    private static final String MAX_VALUE = "MAX_VALUE";
    @BindView(R.id.select_chart)
    SlideSelectLineChart mSelectChart;
    @BindView(R.id.rv_chart_classify)
    RecyclerView mRvChartClassify;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.tv_expend_total_des)
    TextView mTvExpendTotalDes;
    @BindView(R.id.tv_expend_total)
    TextView mTvExpendTotal;
    @BindView(R.id.tv_account_total)
    TextView mTvAccountTotal;
    @BindView(R.id.tv_account_max)
    TextView mTvAccountMax;

    private int mTimeType;//周、月、年
    private int mTime;
    /**
     * 天数（对应坐标轴点数）
     */
    private int mDays;
    private List<AccountModel> mAccountList = new ArrayList<>();
    private Date mDateStart, mDateEnd;
    private float mMaxValue = 0f; //list最大值
    private ArrayList<Float> mFloatList;
    private int mAccountType = Extra.ACCOUNT_TYPE_EXPEND;
    private int mSelectPosition = 3; //chart当前选中
    private List<ChartDataBean> mRecycleList = new ArrayList<>(); //recycviews数据源
    private ChartDetailCountAdapter mAdapter;
    private String mDetailType = Extra.DETAIL_TYPE_DEFAULT;
    private float mDaySumCount; //当天记账总额

    public static ChartDetailFragment newInstance(int timeType, int endTime, float maxValue) {
        ChartDetailFragment fragment = new ChartDetailFragment();
        Bundle args = new Bundle();
        args.putInt(TIME_TYPE, timeType);
        args.putInt(END_TIME, endTime);
        args.putFloat(MAX_VALUE, maxValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTimeType = getArguments().getInt(TIME_TYPE);
            mTime = getArguments().getInt(END_TIME);
            mMaxValue = getArguments().getFloat(MAX_VALUE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_detail;
    }

    @Override
    protected void initData() {
        showProgressDialog();
        //初始化分类类型
        FragmentChart fragmentChart = (FragmentChart) (getParentFragment().getParentFragment());
        mDetailType = fragmentChart.getDetailType();
        initChartData();
        setDaySumCount(); //当天记账总额
        setRecycleListData(mSelectPosition);
        dismissProgressDialog();

        //消息发送到fragmentChart，获取分类列表
        //sendEventBus();

    }

    private void setDaySumCount() {
        List<AccountModel> accountList = getAccountModels(mSelectPosition, Extra.DETAIL_TYPE_DEFAULT);
        mDaySumCount = AccListUtil.sum(accountList);
    }


    @Override
    protected void initView(View view) {
        initTitleText();

        initLineChart();
        initRecycleView();
        initListener();

    }

    private void initTitleText() {
        switch (mTimeType) {
            case ChartTypeFragment.TYPE_WEEK:
                mTvExpendTotalDes.setText("最近1周支出总额");
                break;
            case ChartTypeFragment.TYPE_MONTH:
                mTvExpendTotalDes.setText("最近1月支出总额");
                break;
            case ChartTypeFragment.TYPE_YEAR:
                mTvExpendTotalDes.setText("最近1年支出总额");
                break;
            default:
                break;
            //Arrays.sort(mAccountList.toArray())
        }

        if (mAccountList != null && mAccountList.size() > 0) {
            mTvExpendTotal.setText(AccListUtil.sum(mAccountList) + "");
            mTvAccountTotal.setText(mAccountList.size() + "");
            mTvAccountMax.setText(AccListUtil.max(mAccountList) + "");
        }
    }

    private void initListener() {
        mSelectChart.setOnPointSelectListener(new OnPointSelectListener() {
            @Override
            public void onPointSelect(int position, String xLabel, String value) {
                mSelectPosition = position;
                setDaySumCount(); //当天记账总额
                setRecycleListData(position);
                mAdapter.notifyDataSetChanged();
                //sendEventBus();
                //setTitleText(position);
                //Logger.e("point: " + position  +  "    value: " + value);
            }
        });

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //Logger.e("折叠状态");
                } else if (verticalOffset == 0) {
                    //Logger.e("展开状态");
                }

            }
        });
    }

    private void initRecycleView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRvChartClassify.setLayoutManager(linearLayoutManager);
        mAdapter = new ChartDetailCountAdapter(context, mRecycleList);
        mRvChartClassify.setAdapter(mAdapter);
    }

    private void initChartData() {
        switch (mTimeType) {
            case ChartTypeFragment.TYPE_WEEK:
                mDays = 7;
                mDateStart = TimeUtil.getFirstDayOfWeek(TimeUtil.getDateByWeek(mTime));
                mDateEnd = TimeUtil.getEndDayOfWeek(TimeUtil.getDateByWeek(mTime));
                mAccountList = DbHelper.getInstance().getAccountList(mAccountType, mDetailType, mDateStart, mDateEnd);
                mFloatList = getValues(mAccountList);
                //mMaxValue = Collections.max(mFloatList);
                //Logger.e("max:" + mAccountList.size());
                break;
            case ChartTypeFragment.TYPE_MONTH:
                mDays = 31;

                mDateStart = TimeUtil.getFirstDayOfMonth(mTime);
                mDateEnd = TimeUtil.getEndDayOfMonth(mTime);
                mAccountList = DbHelper.getInstance().getAccountList(mAccountType, mDetailType, mDateStart, mDateEnd);
                mFloatList = getValues(mAccountList);
                //mMaxValue = Collections.max(mFloatList);
                break;
            case ChartTypeFragment.TYPE_YEAR:
                mDays = 12;
                mDateStart = TimeUtil.getFirstDayOfYear(mTime);
                mDateEnd = TimeUtil.getEndDayOfYear(mTime);
                mAccountList = DbHelper.getInstance().getAccountList(mAccountType, mDetailType, mDateStart, mDateEnd);
                mFloatList = getValues(mAccountList);
                break;
            default:
                break;
        }
    }

    private ArrayList<Float> getValues(List<AccountModel> accountList) {
        ArrayList<Float> list = new ArrayList<>();
        int currentDay;
        for (int i = 1; i <= mDays; i++) {
            if (mTimeType == ChartTypeFragment.TYPE_YEAR) { //年
                currentDay = i - 1;
            } else { //月周
                Date currentDate = TimeUtil.getDistanceDate(mDateStart, i - 1);
                currentDay = TimeUtil.getDayOfYear(currentDate);
            }

            if (accountList == null || accountList.size() == 0) { //天数为0
                list.add(0f);
            } else { //list天数小于等于days
                float sumDayCount = 0f;
                for (AccountModel accountModel : accountList) {
                    int day;
                    if (mTimeType == ChartTypeFragment.TYPE_YEAR)
                        day = TimeUtil.getMonthOfYear(accountModel.getTime());
                    else
                        day = TimeUtil.getDayOfYear(accountModel.getTime());
                    if (currentDay == day) { //周、月模式同一天的数据累计相加，年为同月数据相加
                        sumDayCount += accountModel.getCount();
                    }
                }

                list.add(sumDayCount);
            }
        }
        return list;

    }

    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setAxisColor(Color.parseColor("#a9a6b8")).setTextColor(Color.parseColor("#a9a6b8")).setHasLines(false).setShowText(true);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setAxisColor(Color.TRANSPARENT).setTextColor(Color.DKGRAY).setHasLines(true).setShowText(false).setScaleLength(5);
        mSelectChart.setAxisX(axisX);
        mSelectChart.setAxisY(axisY);

        mSelectChart.setSlideLine(ChartConfig.getSlideingLine());
        mSelectChart.setChartData(getFoldLine());
        //新加属性 // TODO: 2017/9/21 判断是否越界
        mSelectChart.setSelectedPoint(mSelectPosition);

        mSelectChart.show();

        //mSelectChart.
    }

    private void setRecycleListData(int position) {
        mRecycleList.clear();

        List<AccountModel> accountList = getAccountModels(position, mDetailType);

        if (accountList != null && accountList.size() > 0) {


            String type = accountList.get(0).getDetailType();
            int imgRes = accountList.get(0).getPicRes();
            float sumAccountClassify = 0f; //记账总额
            int addCount = 0; //相加次数(记账笔数)
            for (AccountModel accountModel : accountList) {

                if (!accountModel.getDetailType().equals(type)) {
                    ChartDataBean chartBean = getChartDataBean(type, imgRes, sumAccountClassify, addCount);
                    //Logger.e(NumUtil.getPointFloat(sumAccountClassify/sumAccount, 4) + "");
                    mRecycleList.add(chartBean);

                    sumAccountClassify = 0f;
                    addCount = 0;
                    type = accountModel.getDetailType();
                    imgRes = accountModel.getPicRes();
                }
                sumAccountClassify += accountModel.getCount();
                addCount++;
            }
            ChartDataBean chartBean = getChartDataBean(type, imgRes, sumAccountClassify, addCount);
            mRecycleList.add(chartBean);
        }
        //对list按照金额反转排序（金额从高到低）
        Collections.sort(mRecycleList);
        //Collections.reverse(mRecycleList);
    }

    @NonNull
    private ChartDataBean getChartDataBean(String type, int imgRes, float sumAccountClassify, int addCount) {
        ChartDataBean chartBean = new ChartDataBean();
        chartBean.setTotal(sumAccountClassify);
        chartBean.setCount(addCount);
        chartBean.setName(type);
        chartBean.setImgRes(imgRes);
        chartBean.setPrecent(NumUtil.getPointFloat(sumAccountClassify / mDaySumCount, 4));
        return chartBean;
    }

    private List<AccountModel> getAccountModels(int position, String detailType) {
        Date selectedDate;
        List<AccountModel> list;
        if (mTimeType == ChartTypeFragment.TYPE_YEAR) {
            selectedDate = TimeUtil.getMonthAgo(mDateStart, position);
            list = getAccountList(mAccountType, detailType,
                    TimeUtil.getFirstDayOfMonth(selectedDate), TimeUtil.getEndDayOfMonth(selectedDate));
        } else {
            selectedDate = TimeUtil.getDistanceDate(mDateStart, position);
            list = getAccountList(mAccountType, detailType,
                    TimeUtil.getDayStartTime(selectedDate), TimeUtil.getDayEndTime(selectedDate));
        }
        return list;

    }

    public List<AccountModel> getAccountList(int accountType, String detailType, Date startTime, Date endTime) {
        QueryBuilder<AccountModel> builder = mDbManager.queryBuilder()
                .where(AccountModelDao.Properties.Time.between(startTime, endTime),
                        AccountModelDao.Properties.OutIntype.eq(accountType));
        if (!detailType.equals(Extra.DETAIL_TYPE_DEFAULT))
            builder.where(AccountModelDao.Properties.DetailType.eq(detailType));
        builder.orderAsc(AccountModelDao.Properties.DetailType);
        //List<AccountModel> accountList  .list();
        return builder.list();
    }


    //x轴
    private List<AxisValue> getAxisValuesX() {
        List<AxisValue> axisValues = new ArrayList<>();

        for (int i = 1; i <= mDays; i++) {
            Date currentDate = TimeUtil.getDistanceDate(mDateStart, i - 1);
            AxisValue value = new AxisValue();
            if (mTimeType == ChartTypeFragment.TYPE_MONTH) {
                if (i % 5 == 0) {
                    value.setLabel(TimeUtil.date2String(currentDate, "MM-dd"));
                    value.setShowLabel(true);
                } else
                    value.setLabel("");
            } else if (mTimeType == ChartTypeFragment.TYPE_YEAR) {
                value.setLabel(i + "月");
                value.setShowLabel(true);
            } else {
                value.setLabel(TimeUtil.date2String(currentDate, "MM-dd"));
                value.setShowLabel(true);
            }

            axisValues.add(value);
        }
        return axisValues;
    }

    //Y轴
    private List<AxisValue> getAxisValuesY() {
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(String.valueOf(i * (mMaxValue / 10)));
            axisValues.add(value);
        }
        return axisValues;
    }

    //点坐标数据
    private Line getFoldLine() {

        List<PointValue> pointValues = new ArrayList<>();
        for (int i = 1; i <= mDays; i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX((i - 1) / (mDays - 1f));
            //int var = 5 + i + (int) (Math.random() * 200);
            pointValue.setLabel(String.valueOf(mFloatList.get(i - 1)));
            if (mMaxValue != 0) {
                pointValue.setY(mFloatList.get(i - 1) / mMaxValue);
                //Logger.e(mFloatList.get(i - 1) + ":" );
            } else
                pointValue.setY(0f);
            pointValues.add(pointValue);
        }
        return ChartConfig.getLine(pointValues);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //Logger.e("" + isVisibleToUser);
    }


    public List<AccountModel> getTypeListData() {
        return mAccountList;
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(ChartClassifyEvent classifyEvent) {
        //Logger.e("收到eventbus：" + classifyEvent.getMessage());

        mDetailType = classifyEvent.getMessage();
        setRecycleListData(mSelectPosition);
        mAdapter.notifyDataSetChanged();

        initChartData();
        initLineChart();
        initTitleText();
    }

}
