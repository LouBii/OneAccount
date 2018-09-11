package com.loubii.account.ui.avtivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.database.DbHelper;
import com.loubii.account.ui.calendar.CountBean;
import com.loubii.account.ui.calendar.CountDecorator;
import com.loubii.account.ui.calendar.TodayDecorator;
import com.loubii.account.util.TimeUtil;
import com.loubii.account.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CalendarActivity extends BaseActivity {


    @BindView(R.id.calendarView)
    MaterialCalendarView mCalendarView;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.ll_title_left)
    FrameLayout mLlTitleLeft;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.ll_title_right)
    FrameLayout mLlTitleRight;
    @BindView(R.id.tv_title_time)
    TextView mTvTitleTime;
    @BindView(R.id.tv_expend)
    TextView mTvExpend;
    @BindView(R.id.tv_income)
    TextView mTvIncome;
    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.rv_day_account)
    RecyclerView mRvDayAccount;
    private CountDecorator mCountDecorator;
    private ArrayList<CountBean> mFloatList = new ArrayList<>();
    private int mSelectDay;
    private Date mCurrentDate; //当前时间
    private List<AccountModel> mAccountList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initView() {
//        TintTypedArray a = TintTypedArray.obtainStyledAttributes(mContext,
//                null,R.styleable.ActionBar,R.attr.actionBarStyle, 0);
//        mIvTitleLeft.setImageDrawable(a.getDrawable(R.styleable.ActionBar_homeAsUpIndicator));
        initCalendar();
    }

    private void initCalendar() {
        mCalendarView.setTopbarVisible(false);
        mSelectDay = CalendarDay.today().getDay();
        mCalendarView.setSelectedDate(CalendarDay.today());
        CalendarDay currentDate = mCalendarView.getCurrentDate();
        setTitleTime(currentDate.getYear(), currentDate.getMonth());

        mCountDecorator = new CountDecorator(getCurrentMonthData());
        mCalendarView.addDecorators(new TodayDecorator(),
                mCountDecorator);
        mCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(final MaterialCalendarView widget, CalendarDay date) {
                setTitleTime(date.getYear(), date.getMonth());

                mCalendarView.setSelectedDate(TimeUtil.getDistanceDate(date.getDate(), 15));
                mSelectDay = date.getDay();
                getCurrentMonthData();
                widget.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        widget.invalidateDecorators();//
                    }
                }, 500);

            }
        });

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mSelectDay = date.getDay();
                getCurrentMonthData();
                initRecyclerView();
                widget.invalidateDecorators();
                ToastUtil.showShort(date.getDay() + "");
            }
        });
    }

    private void initRecyclerView() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        mRvDayAccount.setLayoutManager(linearLayoutManager);
//
//        mRvDayAccount.setAdapter();
}

    private List<CountBean> getCurrentMonthData() {
        mAccountList.clear();
        mFloatList.clear();
        Date mDateStart = TimeUtil.getFirstDayOfMonth(mCurrentDate);
        Date mDateEnd = TimeUtil.getEndDayOfMonth(mCurrentDate);
        mAccountList = DbHelper.getInstance().getAccountList(Extra.ACCOUNT_TYPE_ALL, mDateStart, mDateEnd);
        //mFloatList = getValues(mAccountList);


        int maxDay = TimeUtil.getDayOfMonth(TimeUtil.getEndDayOfMonth(mCurrentDate));
        //Logger.e(maxDay + "");
        mFloatList = getValues(maxDay, mDateStart, mAccountList);
        //Logger.e(mFloatList.size() + "");

//        for (int i = 0; i < maxDay; i++) {
//            CountBean countBean = new CountBean();
//            countBean.setDay(i + 1);
//            countBean.setSelect(false);
//            if (i == mSelectDay - 1)
//                countBean.setSelect(true);
//            if (i % 2 == 0)
//                countBean.setCount(0f);
//            else if (i == 7 || i == 9)
//                countBean.setCount(-20f);
//            else
//                countBean.setCount(10f);
//            mFloatList.add(countBean);
//        }
        return mFloatList;
    }

    private ArrayList<CountBean> getValues(int maxDay, Date dateStart, List<AccountModel> accountList) {
        //ArrayList<CountBean> beanList = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            Date currentDate = TimeUtil.getDistanceDate(dateStart, i);
            int currentDay = TimeUtil.getDayOfYear(currentDate);
            CountBean countBean = new CountBean();
            countBean.setDay(i + 1);
            countBean.setSelect(false);
            //当前日期
            if (i == mSelectDay - 1)
                countBean.setSelect(true);
            if (accountList == null || accountList.size() == 0) { //天数为0
                countBean.setCount(0f);
            } else { //list天数小于等于days
                float sumDayCount = 0f;
                for (AccountModel accountModel : accountList) {
                    int day = TimeUtil.getDayOfYear(accountModel.getTime());
                    if (currentDay == day) { //周、月模式同一天的数据累计相加，年为同月数据相加
                        sumDayCount += accountModel.getCount();
                    }
                }
                countBean.setCount(sumDayCount);
            }
            mFloatList.add(countBean);
        }
        return mFloatList;
    }

    /**
     * 设置标题年月
     *
     * @param year
     * @param month
     */
    private void setTitleTime(int year, int month) {
        mTvTitleTime.setText(year + "年" + (month + 1) + "月");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        long curTime = getIntent().getLongExtra(Extra.ACCOUNT_DATE, 0l);
        mCurrentDate = new Date(curTime);
    }

    @OnClick({R.id.ll_title_left, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left:
                mCalendarView.setMonthToBack();
                break;
            case R.id.ll_title_right:
                mCalendarView.setMonthToNext();
                break;
        }
    }
}
