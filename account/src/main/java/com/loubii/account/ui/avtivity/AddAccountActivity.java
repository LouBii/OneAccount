package com.loubii.account.ui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.loubii.account.R;
import com.loubii.account.adapter.GridPagerAdapter;
import com.loubii.account.adapter.ViewPagerClassifyAdapter;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.bean.RecycleClassifyPagerBean;
import com.loubii.account.bean.RemarkBean;
import com.loubii.account.constants.ClassifyExpendRes;
import com.loubii.account.constants.ClassifyIncomeRes;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.db.database.DbHelper;
import com.loubii.account.event.SortEvent;
import com.loubii.account.ui.dialog.AddRemarkDialog;
import com.loubii.account.util.KeyboardUtil;
import com.loubii.account.util.SPUtil;
import com.loubii.account.util.TimeUtil;
import com.loubii.account.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * 记账
 */
public class AddAccountActivity extends BaseActivity {


    private static final boolean INIT = false;
    private static final boolean CHANGE = true;
    @BindView(R.id.vp_classify)
    ViewPager mVpClassify;
    @BindView(R.id.et_count)
    EditText mEtCount;
    @BindView(R.id.tv_calendar)
    TextView mTvCalendar;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.ll_title_contract)
    FrameLayout mLlTitleContract;
    @BindView(R.id.rb_expend)
    RadioButton mRbExpend;
    @BindView(R.id.rb_income)
    RadioButton mRbIncome;
    @BindView(R.id.rg_type)
    RadioGroup mRgType;
    @BindView(R.id.iv_classify)
    ImageView mIvClassify;

    //支出分类
    private List<RecycleClassifyPagerBean> mExpendCommonList = new ArrayList<>();
    //收入分类
    private List<RecycleClassifyPagerBean> mIncomeList = new ArrayList<>();
    private List<View> mPagerList = new ArrayList<>();
    private ArrayList<GridPagerAdapter> mAdapterList = new ArrayList<>();

    /**
     * 每页条目数
     */
    private static final int PAGE_SIZE = 8;
    /**
     * viewpager页数
     */
    private int mPageCount;
    private KeyboardUtil mKeyboardUtil;
    private OptionsPickerView mPvOptions;

    private ArrayList<Date> mDateList = new ArrayList<>();

    private ArrayList<String> mDayList = new ArrayList<>();
    private ArrayList<String> mHourList = new ArrayList<>();
    private ArrayList<String> mMiniteList = new ArrayList<>();
    private TextView tvTime;
    private ViewPagerClassifyAdapter mVpAdapter;

    //备注选中标签
    public List<RemarkBean> mCheckedRemarkList = new ArrayList<>();
    private int mOutInType = 1; //记账类型 1：支出  2：收入
    private int mCurrentSelectPage = 0; //gridview当前选中页
    private int mCurrentSelectPosition = 0; //gridview当前选中条目下标

    private int[] timePickerPosArr = new int[3];
    private Date mSelectTime; //timepicker选中时间
    private DBManager<AccountModel, Long> mDbManager;
    //备注
    private String mStrNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mDbManager = DbHelper.getInstance().author();
        timePickerPosArr[0] = 15;
        timePickerPosArr[1] = TimeUtil.getNowDateHour();
        timePickerPosArr[2] = TimeUtil.getNowDateMinute();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    protected void initView() {
        mLlTitleContract.setVisibility(View.VISIBLE);
        setListener();
        setRecycleData(Extra.ACCOUNT_TYPE_EXPEND);
        initPagerWithGridView(INIT);
        initKeyboard();
        //注册事件
        EventBus.getDefault().register(this);
    }

    private void setListener() {
        mRgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_expend:
                        mOutInType = 1;
                        setRecycleData(Extra.ACCOUNT_TYPE_EXPEND);
                        initPagerWithGridView(INIT);
                        break;
                    case R.id.rb_income:
                        mOutInType = 2;
                        setRecycleData(Extra.ACCOUNT_TYPE_INCOME);
                        initPagerWithGridView(INIT);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initKeyboard() {
        mKeyboardUtil = new KeyboardUtil(AddAccountActivity.this, AddAccountActivity.this, mEtCount);
        mKeyboardUtil.showKeyboard();
        mEtCount.setInputType(InputType.TYPE_NULL);
        mEtCount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyboardUtil.showKeyboard();
                return false;
            }
        });
        mKeyboardUtil.setOnKeyListener(new KeyboardUtil.onKeyListener() {
            @Override
            public void onKeyFinish(float result) {
                saveData(result);
                AddAccountActivity.this.finish();
                //Logger.e(result + "");
            }
        });
    }

    private void saveData(float result) {
        AccountModel accountModel = new AccountModel();
        accountModel.setCount(result);
        accountModel.setOutIntype(mOutInType);
        if (mOutInType == 1) {
            accountModel.setDetailType(mExpendCommonList.get(mCurrentSelectPosition).getName());
            accountModel.setPicRes(mExpendCommonList.get(mCurrentSelectPosition).getIconRes());
        }
        if (mOutInType == 2) {
            accountModel.setDetailType(mIncomeList.get(mCurrentSelectPosition).getName());
            accountModel.setPicRes(mIncomeList.get(mCurrentSelectPosition).getIconRes());
        }

        Calendar calendar = Calendar.getInstance();
        if (mSelectTime == null) {
            accountModel.setTime(new Date());
        } else {
            accountModel.setTime(mSelectTime);
            calendar.setTime(mSelectTime);
        }

        if (!TextUtils.isEmpty(mStrNote))
            accountModel.setNote(mStrNote);
        mDbManager.insert(accountModel);

        // TODO: 2017/10/24  发送消息通知fragment UI
    }

    private void initPagerWithGridView(boolean hasChange) {
        List<RecycleClassifyPagerBean> gridList = new ArrayList<>();
        if (mOutInType == 1)
            gridList = mExpendCommonList;
        if (mOutInType == 2)
            gridList = mIncomeList;
        mPagerList.clear();
        mAdapterList.clear();
        //总的页数=总数/每页数量，并取整
        mPageCount = (int) Math.ceil(gridList.size() * 1.0 / PAGE_SIZE);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < mPageCount; i++) {
            // 每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) inflater.inflate(R.layout.gridview_pager_classify, mVpClassify, false);
            final GridPagerAdapter adapter = new GridPagerAdapter(this, gridList, i, PAGE_SIZE);
            gridView.setAdapter(adapter);
            gridView.setNumColumns(4);
            mAdapterList.add(adapter);
            mPagerList.add(gridView);

            final int page = i;
            final List<RecycleClassifyPagerBean> finalGridList = gridList;
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //点击自定义跳转拖拽
                    if (page == mPageCount - 1 && position == (finalGridList.size() - page * PAGE_SIZE - 1)) {
                        Intent intent = new Intent(AddAccountActivity.this, AddSortActivity.class);
                        intent.putExtra(Extra.ACCOUNT_TYPE, mOutInType);
                        startActivity(intent);
                    }
                    else {
                        //当选中页面发生变化时，更新上个页面为全灰
                        if (mCurrentSelectPage != page) {
                            mAdapterList.get(mCurrentSelectPage).onItemSelected(GridPagerAdapter.NO_ITEM_SELECT);
                            mCurrentSelectPage = page;
                        }
                        int curPos = position + mCurrentSelectPage * PAGE_SIZE;
                        if (mCurrentSelectPosition != curPos) {
                            mCurrentSelectPosition = position + mCurrentSelectPage * PAGE_SIZE;
                            //设置指示图标
                            mIvClassify.setImageResource(finalGridList.get(mCurrentSelectPosition).getIconRes());
                        }
                        //adapter数据更新
                        adapter.onItemSelected(position);
                    }
                }
            });
        }
        //设置适配器
//        if (hasChange)
//            mVpAdapter.notifyDataSetChanged();
//        else {
            mVpAdapter = new ViewPagerClassifyAdapter(mPagerList);
            mVpClassify.setAdapter(mVpAdapter);
//        }
        mIndicator.setViewPager(mVpClassify);
    }

    private void setRecycleData(int outInType) {
        String[] names;
        int[] icons;
        int[] iconsGray;
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND) {
            //如果已经有list缓存则不重新获取数据
            if (mExpendCommonList.size() == 0) {
                //先从sp文件中获取数据，为空则表示第一次加载从配置文件中获取
                mExpendCommonList = SPUtil.getCommonList(this, outInType);
                if (mExpendCommonList.size() == 0) {
                    names = ClassifyExpendRes.NAMES;
                    icons = ClassifyExpendRes.ICONS;
                    iconsGray = ClassifyExpendRes.ICONS_GRAY;
                    //常用分类
                    for (int i = 0; i < names.length; i++) {
                        RecycleClassifyPagerBean bean = new RecycleClassifyPagerBean();
                        bean.setId(i);
                        bean.setName(names[i]);
                        bean.setIconRes(icons[i]);
                        bean.setIconResGray(iconsGray[i]);
                        mExpendCommonList.add(bean);
                    }
                }
                //末尾自定义条目
                addDefineToEnd(outInType);
            }

        }

        if (outInType == Extra.ACCOUNT_TYPE_INCOME) {
            if (mIncomeList.size() == 0) {
                mIncomeList = SPUtil.getCommonList(this, outInType);
                if (mIncomeList.size() == 0) {
                    names = ClassifyIncomeRes.NAMES;
                    icons = ClassifyIncomeRes.ICONS;
                    iconsGray = ClassifyIncomeRes.ICONS_GRAY;
                    //常用分类
                    for (int i = 0; i < names.length; i++) {
                        RecycleClassifyPagerBean bean = new RecycleClassifyPagerBean();
                        bean.setId(i);
                        bean.setName(names[i]);
                        bean.setIconRes(icons[i]);
                        bean.setIconResGray(iconsGray[i]);
                        mIncomeList.add(bean);
                    }
                }
                //末尾自定义条目
                addDefineToEnd(outInType);
            }
        }


        //设置指示图标为第一个
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND)
            mIvClassify.setImageResource(mExpendCommonList.get(0).getIconRes());
        else
            mIvClassify.setImageResource(mIncomeList.get(0).getIconRes());
    }

    /**
     * 末尾自定义条目
     *
     * @param outInType
     */
    private void addDefineToEnd(int outInType) {
        RecycleClassifyPagerBean bean = new RecycleClassifyPagerBean();
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND)
            bean.setId(mExpendCommonList.size());
        if (outInType == Extra.ACCOUNT_TYPE_INCOME)
            bean.setId(mIncomeList.size());
        bean.setName("自定义");
        bean.setIconRes(R.drawable.classify_define);
        bean.setIconResGray(R.drawable.classify_define);
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND)
            mExpendCommonList.add(bean);
        if (outInType == Extra.ACCOUNT_TYPE_INCOME)
            mIncomeList.add(bean);
    }

    @OnClick({R.id.tv_calendar, R.id.tv_remark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_calendar:
                mKeyboardUtil.hideKeyboard();
                showTimePicker();
                break;
            case R.id.tv_remark:
                showRemarkDialog();
                break;
        }
    }

    private void showRemarkDialog() {
        //mKeyboardUtil.hideKeyboard();
        AddRemarkDialog dialog = new AddRemarkDialog(this);
        //dialog.setRemarkList(mCheckedRemarkList);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        // TODO: 2017/8/25 偶尔崩溃 显示软键盘
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//显示软键盘

        InputMethodManager imm = (InputMethodManager) this.
                getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示软键盘
        dialog.setOnFinishListener(new AddRemarkDialog.OnFinishListener() {
            @Override
            public void onFinish(String remarkStr, List<RemarkBean> remarkList) {
                //if (!TextUtils.isEmpty(remarkStr)) {
                    mTvRemark.setText(remarkStr);
                    mStrNote = mTvRemark.getText().toString().trim();
                //}
                mCheckedRemarkList = remarkList;
                ToastUtil.showShort(mContext, remarkStr + "---->" + remarkList.size());
            }
        });
    }

    private void showTimePicker() {
        setOptionData();
        mPvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                timePickerPosArr[0] = options1;
                timePickerPosArr[1] = options2;
                timePickerPosArr[2] = options3;
                //Date selectDate = mDateList.get(options1);
                String selectDate = TimeUtil.date2String(mDateList.get(options1), "yyyy-MM-dd");
                StringBuffer sbTime = new StringBuffer(selectDate);
                sbTime.append(" " + mHourList.get(options2) + ":" + mMiniteList.get(options3));
                //Logger.e(sbTime.toString());
                //mSelectTime = TimeUtil.string2Date(sbTime.toString(), "yyyy-MM-dd HH:mm").getTime();
                mSelectTime = TimeUtil.string2Date(sbTime.toString(), "yyyy-MM-dd HH:mm");

                Toast.makeText(AddAccountActivity.this, mDayList.get(options1) + mHourList.get(options2) + mMiniteList.get(options3), Toast.LENGTH_SHORT).show();
                tvTime.setText(mDayList.get(options1) + mHourList.get(options2) + mMiniteList.get(options3));
                mTvCalendar.setText(mDayList.get(options1).split(" ")[0]);
            }
        })
                .setLayoutRes(R.layout.pickerview_date_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvTime = (TextView) v.findViewById(R.id.tv_time);
                        tvTime.setText(TimeUtil.getNowDate());
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvOptions.returnData();
                                mPvOptions.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(timePickerPosArr[0], timePickerPosArr[1], timePickerPosArr[2])
                .build();
        mPvOptions.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                mKeyboardUtil.showKeyboard();
            }
        });
        mPvOptions.setNPicker(mDayList, mHourList, mMiniteList);
        mPvOptions.show();
    }

    /**
     * 设置时间数据（当前时间前后30天）
     */
    private void setOptionData() {
        if (mDayList.size() == 0 || mHourList.size() == 0 || mMiniteList.size() == 0) {
            //天
            for (int i = -15; i < 15; i++) {
                if (i == 0) {
                    mDayList.add("今天");
                } else
                    mDayList.add(TimeUtil.getLastDateStr(i)); //字符串
                mDateList.add(TimeUtil.getLastDate(i)); //date日期
            }
            //小时
            for (int i = 0; i < 24; i++) {
                mHourList.add(String.format("%02d", i));
            }
            //分钟
            for (int i = 0; i < 60; i++) {
                mMiniteList.add(String.format("%02d", i));
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(SortEvent messageEvent) {
        if (mOutInType == 1)
            mExpendCommonList = SPUtil.getCommonList(this, 1);
        if (mOutInType == 2)
            mIncomeList = SPUtil.getCommonList(this, 2);
        //末尾自定义条目添加
        addDefineToEnd(mOutInType);
        //刷新gridview数据
        initPagerWithGridView(CHANGE);
        //mVpAdapter.notifyDataSetChanged();
        Toast.makeText(this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();

        //tv_message.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
