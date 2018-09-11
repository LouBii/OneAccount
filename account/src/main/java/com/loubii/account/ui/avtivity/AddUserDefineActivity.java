package com.loubii.account.ui.avtivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.loubii.account.R;
import com.loubii.account.adapter.AddUserDefineAdapter;
import com.loubii.account.bean.RecycleClassifyPagerBean;
import com.loubii.account.constants.ClassifyExpendRes;
import com.loubii.account.event.UserDefineEvent;
import com.loubii.account.util.ToastUtil;
import com.loubii.account.view.BaseToolbar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddUserDefineActivity extends BaseActivity {

    @BindView(R.id.rv_classify)
    RecyclerView mRvClassify;
    @BindView(R.id.iv_classify)
    ImageView mIvClassify;
    @BindView(R.id.et_classify_name)
    EditText mEtClassifyName;
    @BindView(R.id.toolbar)
    BaseToolbar mToolbar;
    private List<RecycleClassifyPagerBean> mIconList;
    private AddUserDefineAdapter mDefineAdapter;
    private int mCheckedPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_user_define;
    }

    @Override
    protected void initView() {
        initRecycleData();
        initRecycleView();
        initToolbar();

    }

    private void initToolbar() {
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
        mToolbar.setCenterTitle("新增支出分类");
        mToolbar.setSettingText("完成");
        mToolbar.setOnSettingTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !TextUtils.isEmpty(mEtClassifyName.getText().toString())) {
                    RecycleClassifyPagerBean bean = mIconList.get(mCheckedPos);
                    bean.setName(mEtClassifyName.getText().toString().trim());
                    //消息推送
                    EventBus.getDefault().post(new UserDefineEvent(bean));
                    mContext.finish();
                } else
                    ToastUtil.showShort(mContext, "请输入名称");
            }
        });
    }

    private void initRecycleView() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRvClassify.setLayoutManager(manager);
        //拖拽adapter
        mDefineAdapter = new AddUserDefineAdapter(this, mIconList);
        mDefineAdapter.setOnItemCheckedListener(new AddUserDefineAdapter.OnItemChekedListener() {
            @Override
            public void onItemChecked(int position) {
                mCheckedPos = position;
                mIvClassify.setImageResource(mIconList.get(position).getIconRes());
            }
        });
        mRvClassify.setAdapter(mDefineAdapter);
    }

    private void initRecycleData() {
        mIconList = new ArrayList<>();
        RecycleClassifyPagerBean commonBean;
        for (int i = 0; i < ClassifyExpendRes.ICONS.length; i++) {
            commonBean = new RecycleClassifyPagerBean();
            commonBean.setId(i);
            commonBean.setIconRes(ClassifyExpendRes.ICONS[i]);
            commonBean.setIconResGray(ClassifyExpendRes.ICONS_GRAY[i]);
            if (i == 0)
                commonBean.setChecked(true);
            else
                commonBean.setChecked(false);
            mIconList.add(commonBean);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
