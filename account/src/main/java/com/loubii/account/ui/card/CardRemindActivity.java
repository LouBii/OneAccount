package com.loubii.account.ui.card;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.LinearLayout;

import com.suke.widget.SwitchButton;
import com.loubii.account.R;
import com.loubii.account.ui.avtivity.BaseActivity;
import com.loubii.account.util.CalenderRemind;
import com.loubii.account.view.BaseToolbar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.BindView;

public class CardRemindActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    BaseToolbar mToolbar;
    @BindView(R.id.lin_show)
    LinearLayout mLinShow;
    @BindView(R.id.switch_remind)
    SwitchButton mSwitchRemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
//        String startWay = this.getIntent().getStringExtra(Extra.CARD_REMIND_START_TYPE);
//        if (startWay.equals("AlarmReceiver")) {
//            String message = this.getIntent().getStringExtra("msg");
//            int flag = this.getIntent().getIntExtra("flag", 0);
//        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_remind;
    }


    @Override
    protected void initView() {
        setListener();
    }

    private void setListener() {
        mSwitchRemind.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    mLinShow.setVisibility(View.VISIBLE);
                    //CalenderRemind.addAlarm(mContext, System.currentTimeMillis() + 60 * 1000l);
                    addCalender();
                }
                else {
                    mLinShow.setVisibility(View.GONE);
                    //deleteCalender();
                }

            }
        });
    }

    private void deleteCalender() {
        //权限申请
        AndPermission.with(mContext)
                .requestCode(100)
                .permission(
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR
                )
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        CalenderRemind.deleteCalendarEvent(mContext, "test");
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                })
                .start();
        CalenderRemind.deleteCalendarEvent(mContext, "test");
    }

    private void addCalender() {
        //权限申请
        AndPermission.with(mContext)
                .requestCode(200)
                .permission(
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR
                )
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        CalenderRemind.addCalendarEvent(mContext, "test", "test111111", System.currentTimeMillis() + 2 * 60 * 1000l);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                })
                .start();
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            //返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.mipmap.ic_toolbar_left);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setCenterTitle("还款提醒");
        mToolbar.setSettingText("完成");
        mToolbar.setOnSettingTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
