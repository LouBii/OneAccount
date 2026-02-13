package com.loubii.account.ui.avtivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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
import com.loubii.account.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
//    @BindView(R.id.tv_calendar)
//    TextView mTvCalendar;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_time)
    TextView mTvTime;
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

//    private ArrayList<Date> mDateList = new ArrayList<>();

    private ArrayList<String> mDayList = new ArrayList<>();
    private ArrayList<String> mHourList = new ArrayList<>();
    private ArrayList<String> mMiniteList = new ArrayList<>();
//    private TextView tvTime;
    private ViewPagerClassifyAdapter mVpAdapter;

    //备注选中标签
    public List<RemarkBean> mCheckedRemarkList = new ArrayList<>();
    private int mOutInType = 1; //记账类型 1：支出  2：收入
    private int mCurrentSelectPage = 0; //gridview当前选中页
    private int mCurrentSelectPosition = 0; //gridview当前选中条目下标
    private boolean mIsEditMode = false; //是否为编辑模式
    private long mEditBillId = -1;  //账单id 缺省值-1

    private AccountModel mEditBill = null;

//    private int[] timePickerPosArr = new int[3];
//    private Time mSelectTime; //timepicker选中时间
//    private Date mSelectDate;
//    private Calendar mSelectCalendar;
    private LocalDateTime mSelectDateTime = LocalDateTime.now();
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

        // 1. 接收Intent，判断模式
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EDIT_MODE") && intent.getBooleanExtra("EDIT_MODE", false)) {
            mIsEditMode = true;
            mEditBillId = intent.getLongExtra("BILL_ID", -1);
            // 2. 根据ID从数据库查询出待编辑的账单对象
            if (mEditBillId != -1) {
                // 使用 load 方法，参数是主键值 (Long 类型)
                mEditBill = mDbManager.load(mEditBillId);
                if (mEditBill == null) {
                    ToastUtil.showShort(this, "未找到对应的账单数据");
                    finish();
                    return;
                }
            }
        }

//        timePickerPosArr[0] = 15;
//        timePickerPosArr[1] = TimeUtil.getNowDateHour();
//        timePickerPosArr[2] = TimeUtil.getNowDateMinute();
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
        if (mIsEditMode && mEditBill != null) {
            populateEditData();
        }
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
    private void populateEditData(){
        // 1. 回显金额
        mEtCount.setText(String.valueOf(mEditBill.getCount()));
        // 如果自定义键盘支持设置初始值，也需要设置
//        if (mKeyboardUtil != null) {
//            mKeyboardUtil.setEditText(String.valueOf(mEditBill.getCount()));
//        }

        // 2. 回显分类
        // 需要根据分类名称，在 mExpendCommonList 或 mIncomeList 中找到对应的位置
        List<RecycleClassifyPagerBean> currentList = (mOutInType == 1) ? mExpendCommonList : mIncomeList;
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).getName().equals(mEditBill.getDetailType())) {
                mCurrentSelectPosition = i;
                mCurrentSelectPage = i / PAGE_SIZE; // 计算所在页
                // 更新指示图标
                mIvClassify.setImageResource(currentList.get(i).getIconRes());
                // 通知对应页的Adapter更新选中状态（关键且容易遗漏）
                if (mAdapterList != null && mAdapterList.size() > mCurrentSelectPage) {
                    mAdapterList.get(mCurrentSelectPage).onItemSelected(i % PAGE_SIZE);
                }
                // 切换到对应的ViewPager页
                mVpClassify.setCurrentItem(mCurrentSelectPage);
                break;
            }
        }

        // 3. 回显时间
        if (mEditBill.getDateTime() != null) {
            mSelectDateTime = mEditBill.getDateTime();
            mTvDate.setText(mSelectDateTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
            mTvTime.setText(mSelectDateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
//            mTvCalendar.setText(TimeUtil.date2String(mSelectTime, "MM-dd"));
//            mTvDate.setText(TimeUtil.date2String(mSelectDate, "yyyy-MM-dd"));
//            mTvTime.setText(TimeUtil.date2String(mSelectTime,"HH:mm"));
            // 如果需要，也要设置时间选择器的初始选中位置
        }

        // 4. 回显备注
        mStrNote = mEditBill.getNote();
        if (!TextUtils.isEmpty(mStrNote)) {
            mTvRemark.setText(mStrNote);
        }
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
        if (mEditBill != null){
            accountModel = mEditBill;
        }
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

//        Calendar calendar = Calendar.getInstance();
        if (mSelectDateTime == null) {
            accountModel.setDateTime(LocalDateTime.now());
        }else{
            accountModel.setDateTime(mSelectDateTime);
//            calendar.setTime(mSelectDateTime);
        }

        if (!TextUtils.isEmpty(mStrNote))
            accountModel.setNote(mStrNote);
        mDbManager.insertOrReplace(accountModel);
//        mDbManager.insert(accountModel);

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

    @OnClick({R.id.tv_date,R.id.tv_time, R.id.tv_remark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                Log.i("tv_time","click");
                showTimePicker();
                break;
            case R.id.tv_date:
                Log.i("tv_date","click");
//                mKeyboardUtil.hideKeyboard();
                showDatePicker();
//                showDateTimePicker();
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

    private void showTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {

                    LocalDate selectDate = mSelectDateTime.toLocalDate();
                    LocalTime selectTime = LocalTime.of(hourOfDay,minute);
                    mSelectDateTime = LocalDateTime.of(selectDate,selectTime);

                    // 显示选择的时间
                    mTvTime.setText(selectTime.format(DateTimeFormatter.ofPattern("HH:mm")));

                },
                mSelectDateTime.getHour(),
                mSelectDateTime.getMinute(),
//                calendar.get(Calendar.HOUR_OF_DAY),
//                calendar.get(Calendar.MINUTE),
                true // 24小时制
        );
        timePickerDialog.show();

    }

    private void showDatePicker() {
        // 先显示日期选择器
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    LocalTime selectTime = mSelectDateTime.toLocalTime();
                    LocalDate selectDate = LocalDate.of(year,month,dayOfMonth);

                    mSelectDateTime = LocalDateTime.of(selectDate,selectTime);

                    mTvDate.setText(selectDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

//                                Toast.makeText(AddAccountActivity.this, displayStr, Toast.LENGTH_SHORT).show();
                },
                mSelectDateTime.getYear(),
                mSelectDateTime.getMonthValue(),
                mSelectDateTime.getDayOfMonth()
        );

//         设置日期范围（可选）
//         datePickerDialog.getDatePicker().setMinDate(minDate);
//         datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
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
