package com.loubii.account.ui.avtivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.suke.widget.SwitchButton;
import com.loubii.account.R;
import com.loubii.account.util.TimeUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountRemindAddActivity extends BaseToolBarActivity {

    @BindView(R.id.rel_remind_period)
    RelativeLayout mRelRemindPeriod;
    @BindView(R.id.rel_remind_time)
    RelativeLayout mRelRemindTime;
    @BindView(R.id.switch_vibrate)
    SwitchButton mSwitchVibrate;
    @BindView(R.id.switch_sound)
    SwitchButton mSwitchSound;
    @BindView(R.id.tv_period)
    TextView mTvPeriod;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.et_remind_content)
    EditText mEtRemindContent;
    private TimePickerView mPvPeriod;
    private OptionsPickerView mPvTime;
    private int mSelectedPeriodPosition = 3; //提醒周期pickerview选中条目下标,默认为3(每周)
    private Calendar mSelectedTime = null; //提醒时间pickerview选中条目,默认为3(每周)
    private List<String> mListPeriod = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_remind_add;
    }

    @Override
    protected void initView() {
        setTitle("定时提醒");
    }

    @OnClick({R.id.rel_remind_period, R.id.rel_remind_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_remind_period:
                showPickerViewPeriod(); //提醒周期
                break;
            case R.id.rel_remind_time:
                showPickerViewTime(); //提醒时间
                break;
        }
    }

    private void showPickerViewTime() {
        mPvPeriod = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                mSelectedTime = Calendar.getInstance();
                mSelectedTime.setTime(date);
                mTvTime.setText(TimeUtil.date2String(date, "HH:mm"));
            }
        })
                .setLayoutRes(R.layout.pickerview_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        TextView tvTime = (TextView) v.findViewById(R.id.tv_time);
                        tvTime.setText("时间");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvPeriod.returnData();
                                mPvPeriod.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvPeriod.dismiss();
                            }
                        });
                    }
                })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                //.setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(mSelectedTime == null ? Calendar.getInstance() : mSelectedTime)
                //.setRangDate(startDate, endDate)
                //.setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                //.setDecorView(null)
                .build();
        mPvPeriod.show();
    }

    private List<String> getPeriodData() {
        mListPeriod.add("仅一次");
        mListPeriod.add("每个工作日");
        mListPeriod.add("每个周末(六、日)");
        mListPeriod.add("每周");
        mListPeriod.add("每月");
        return mListPeriod;
    }

    private void showPickerViewPeriod() {
        mPvTime = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                mSelectedPeriodPosition = options1;
                mTvPeriod.setText(mListPeriod.get(options1));
            }
        })
                .setLayoutRes(R.layout.pickerview_date_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        TextView tvTime = (TextView) v.findViewById(R.id.tv_time);
                        tvTime.setText("时间");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvTime.returnData();
                                mPvTime.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvTime.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(mSelectedPeriodPosition)
                .build();

        mPvTime.setPicker(getPeriodData());//添加数据
        mPvTime.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPvPeriod != null && mPvPeriod.isShowing())
            mPvPeriod.dismiss();
        if (mPvTime != null && mPvTime.isShowing())
            mPvTime.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
