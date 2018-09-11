package com.loubii.account.ui.avtivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.loubii.account.R;
import com.loubii.account.adapter.AddSortAdapter;
import com.loubii.account.bean.RecycleClassifyPagerBean;
import com.loubii.account.constants.ClassifyExpendRes;
import com.loubii.account.constants.ClassifyIncomeRes;
import com.loubii.account.constants.Extra;
import com.loubii.account.constants.SPkeys;
import com.loubii.account.event.SortEvent;
import com.loubii.account.event.UserDefineEvent;
import com.loubii.account.ui.drag.ItemDragHelperCallback;
import com.loubii.account.util.CompareUtil;
import com.loubii.account.util.SPUtil;
import com.loubii.account.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 分类排序、添加新分类
 */
public class AddSortActivity extends BaseToolBarActivity {

    @BindView(R.id.rv_sort)
    RecyclerView mRvSort;
    //@BindView(R.id.tv_sort)
    MenuItem mTvSort;
    private List<RecycleClassifyPagerBean> mCommonList;
    private List<RecycleClassifyPagerBean> mOtherList;
    private AddSortAdapter mSortAdapter;
    private int mAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mAccountType = getIntent().getIntExtra(Extra.ACCOUNT_TYPE, 1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_sort;
    }

    @Override
    protected void initView() {
        //mTvSort.setVisibility(View.VISIBLE);
        setTitle(mAccountType == 1 ? "支出" : "收入");
        initRecyclerView();
        //注册事件
        EventBus.getDefault().register(mContext);
    }

    private void initRecyclerView() {
        //数据
        initRecycleData();
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRvSort.setLayoutManager(manager);
        //拖拽帮助类
        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRvSort);
        //拖拽adapter
        mSortAdapter = new AddSortAdapter(this, helper, mCommonList, mOtherList);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mSortAdapter.getItemViewType(position);
                return viewType == AddSortAdapter.TYPE_MY || viewType == AddSortAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRvSort.setAdapter(mSortAdapter);

        mSortAdapter.setOnEditModeListener(new AddSortAdapter.OnEditModeListener() {
            @Override
            public void onEditMode(boolean isChecked) {
                if (isChecked)
                    mTvSort.setTitle("完成");
            }
        });

        mSortAdapter.setOnAddClickListener(new AddSortAdapter.OnAddClickListener() {
            @Override
            public void onAddClick() {
                startActivity(new Intent(mContext, AddUserDefineActivity.class));

            }
        });

        mSortAdapter.setOnMyChannelItemClickListener(new AddSortAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(AddSortActivity.this, mCommonList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecycleData() {
        mCommonList = SPUtil.getCommonList(this, mAccountType);
        mOtherList = SPUtil.getOtherList(this, mAccountType);
        String[] names = new String[0];
        int[] icons = new int[0];
        int[] iconsGray = new int[0];
        if (mCommonList.size() == 0) {
            if (mAccountType == Extra.ACCOUNT_TYPE_EXPEND) {
                names = ClassifyExpendRes.NAMES;
                icons = ClassifyExpendRes.ICONS;
                iconsGray = ClassifyExpendRes.ICONS_GRAY;
            } else if (mAccountType == Extra.ACCOUNT_TYPE_INCOME) {
                names = ClassifyIncomeRes.NAMES;
                icons = ClassifyIncomeRes.ICONS;
                iconsGray = ClassifyIncomeRes.ICONS_GRAY;
            }
            //常用分类列表
            mCommonList = new ArrayList<>();
            RecycleClassifyPagerBean commonBean;
            for (int i = 0; i < names.length; i++) {
                commonBean = new RecycleClassifyPagerBean();
                commonBean.setId(i);
                commonBean.setName(names[i]);
                commonBean.setIconRes(icons[i]);
                commonBean.setIconResGray(iconsGray[i]);
                mCommonList.add(commonBean);
            }
        }

        if (mOtherList.size() == 0) {
            if (mAccountType == Extra.ACCOUNT_TYPE_EXPEND) {
                //names = ClassifyExpendRes.NAM;
                icons = ClassifyExpendRes.ICONS_OTHER;
                iconsGray = ClassifyExpendRes.ICONS_OTHER_GRAY;
            } else if (mAccountType == Extra.ACCOUNT_TYPE_INCOME) {
                //names = ClassifyIncomeRes.NAMES;
                icons = ClassifyIncomeRes.ICONS_OTHER;
                iconsGray = ClassifyIncomeRes.ICONS_OTHER_GRAY;
            }

            //其他新增分类列表
            mOtherList = new ArrayList<>();
            RecycleClassifyPagerBean otherBean;
            for (int i = 0; i < icons.length; i++) {
                otherBean = new RecycleClassifyPagerBean();
                otherBean.setId(i);
                otherBean.setName("其他" + i);
                otherBean.setIconRes(icons[i]);
                otherBean.setIconResGray(iconsGray[i]);
                mOtherList.add(otherBean);
            }
            addDefineToEnd();
        }
    }

    private void addDefineToEnd() {
        RecycleClassifyPagerBean otherBean;
        otherBean = new RecycleClassifyPagerBean();
        otherBean.setId(mOtherList.size());
        otherBean.setName("自主添加");
        otherBean.setIconRes(R.drawable.classify_define);
        otherBean.setIconResGray(R.drawable.classify_define);
        mOtherList.add(otherBean);
    }

//    @OnClick({R.id.ll_title_return})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ll_title_return:
//                break;
//
//        }
//    }

    /**
     * 排序完成后保存至sharedprefrence
     */
    private void saveListData() {
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strCommon = gson.toJson(mSortAdapter.getCommonList());
        String strOther = gson.toJson(mSortAdapter.getOtherList());

        if (mAccountType == Extra.ACCOUNT_TYPE_EXPEND) {
            SPUtil.with(this).load().save(SPkeys.EXPAND_COMMOM_LIST, strCommon);
            SPUtil.with(this).load().save(SPkeys.EXPAND_OTHER_LIST, strOther);
        } else if (mAccountType == Extra.ACCOUNT_TYPE_INCOME) {
            SPUtil.with(this).load().save(SPkeys.INCOME_COMMOM_LIST, strCommon);
            SPUtil.with(this).load().save(SPkeys.INCOME_OTHER_LIST, strOther);
        }

    }

    @Override
    public void onBackPressed() {
        if (mSortAdapter.getEditMode() == true) {
            mSortAdapter.cancelEditMode(mRvSort);
            mTvSort.setTitle("排序");
        } else {
            //如果集合size相等则比较集合是否相等
            if ((mCommonList.size() == mSortAdapter.getCommonList().size()) ) {
                if (!CompareUtil.compare(mCommonList, mSortAdapter.getCommonList())) {
                    showSaveDialog();
                } else
                    super.onBackPressed();
            } else {
                showSaveDialog();
            }
            //super.onBackPressed();
        }

    }

    private void showSaveDialog() {
        showAlertDialog("您还没有保存更改，是否离开？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismissAlertDialog();
                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(UserDefineEvent event){
        ToastUtil.showShort(mContext, event.getBean().getName());
        mSortAdapter.notifyItemAddToOther(event.getBean());
        saveListData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finish, menu);
        menu.getItem(0).setTitle("排序");
        mTvSort = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mSortAdapter.getEditMode() == false) {
            mSortAdapter.startEditMode(mRvSort);
            mTvSort.setTitle("完成");
        } else {
            mSortAdapter.cancelEditMode(mRvSort);
            mTvSort.setTitle("排序");
            //如果集合size相等则比较集合是否相等,不相等说明数据排列发生了变化
            if ((mCommonList.size() == mSortAdapter.getCommonList().size()) ) {
                if (!CompareUtil.compare(mCommonList, mSortAdapter.getCommonList())) {
                    saveListData();
                    //发送事件
                    EventBus.getDefault().post(new SortEvent("finish"));
                    finish();
                }
            } else {
                saveListData();
                //发送事件
                EventBus.getDefault().post(new SortEvent("finish"));
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
