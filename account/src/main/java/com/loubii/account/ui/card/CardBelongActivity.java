package com.loubii.account.ui.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loubii.account.R;
import com.loubii.account.adapter.BaseRecycleAdapter;
import com.loubii.account.constants.CardRes;
import com.loubii.account.constants.Extra;
import com.loubii.account.ui.avtivity.BaseToolBarActivity;

import butterknife.BindView;

public class CardBelongActivity extends BaseToolBarActivity implements BaseRecycleAdapter.OnItemClickListener {

    @BindView(R.id.rv_bank_belong)
    RecyclerView mRvBankBelong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_belong;
    }

    @Override
    protected void initView() {
        setTitle("所属银行");
        initRecycleView();

    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRvBankBelong.setLayoutManager(manager);
        CardBelongAdapter adapter = new CardBelongAdapter(mContext, CardRes.getAdapterData());
        adapter.setOnItemClickListener(this);
        mRvBankBelong.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        setMyResult(position);
        finish();
        //ToastUtil.showShort(position +"");
    }

    private void setMyResult(int position) {
        Intent intent = new Intent();
        intent.putExtra(Extra.CARD_RES_INDEX, position);
        setResult(Extra.resultCode.CARD_BELONG, intent);
    }

    @Override
    public void onBackPressed() {
        setMyResult(-1);
        super.onBackPressed();

    }
}
