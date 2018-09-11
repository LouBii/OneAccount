package com.loubii.account.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.loubii.account.R;
import com.loubii.account.adapter.BaseRecycleAdapter;
import com.loubii.account.adapter.CardAdapter;
import com.loubii.account.bean.CardBean;
import com.loubii.account.constants.Extra;
import com.loubii.account.event.CardAddFinishEvent;
import com.loubii.account.ui.card.CardAddActivity;
import com.loubii.account.ui.card.CardDetailActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 银行卡
 */
public class FragmentCard extends BaseEventFragment{
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ll_add_deposit)
    LinearLayout mLlAddDeposit;
//    @BindView(R.id.ll_add_credit)
//    LinearLayout mLlAddCredit;
    @BindView(R.id.rv_card)
    RecyclerView mRvCard;
    private String mParam1;
    private String mCardType;
    private ArrayList<CardBean> mCardList;
    private CardAdapter mAdapter;


    public FragmentCard() {
        // Required empty public constructor
    }


    public static FragmentCard newInstance(String param1) {
        FragmentCard fragment = new FragmentCard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    protected void initData() {
        //getCardList();
    }

    @Override
    protected void initView(View view) {
        // TODO: 2017/9/5 获取银行卡列表
        //储蓄卡已绑定
        if (true) {
            mLlAddDeposit.setVisibility(View.GONE);
        }
        mRvCard.setVisibility(View.VISIBLE);
        initRecycleView();
        setListener();
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == mCardList.size() - 1) { //添加信用卡
                    Intent intent = new Intent(getActivity(), CardAddActivity.class);
                    intent.putExtra(Extra.CARD_TYPE, Extra.CARD_CREDIT);
                    startActivity(intent);
                } else if (position == 0){ //储蓄卡详情
                    Intent intent = new Intent(getActivity(), CardDetailActivity.class);
                    intent.putExtra(Extra.CARD_TYPE, Extra.CARD_DEPOSIT);
                    startActivity(intent);
                } else { //信用卡详情
                    Intent intent = new Intent(getActivity(), CardDetailActivity.class);
                    intent.putExtra(Extra.CARD_TYPE, Extra.CARD_CREDIT);
                    startActivity(intent);
                }
            }
        });
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRvCard.setLayoutManager(linearLayoutManager);
        mAdapter = new CardAdapter(context, getCardData());
        mRvCard.setAdapter(mAdapter);
    }

    private List<CardBean> getCardData() {
        mCardList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            CardBean cardBean = new CardBean();
            cardBean.setCardName("民生银行");
            mCardList.add(cardBean);
        }
        for (int i=0; i<3; i++) {
            CardBean cardBean = new CardBean();
            cardBean.setCardName("中信银行");
            mCardList.add(cardBean);
        }
        return mCardList;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card;
    }


    @OnClick({R.id.ll_add_deposit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_add_deposit:
                intent = new Intent(getActivity(), CardAddActivity.class);
                intent.putExtra(Extra.CARD_TYPE, Extra.CARD_DEPOSIT);
                startActivity(intent);
                break;
//            case R.id.ll_add_credit:
//                intent = new Intent(getActivity(), CardAddActivity.class);
//                intent.putExtra(Extra.CARD_TYPE, Extra.CARD_CREDIT);
//                startActivity(intent);
//                break;
        }
    }

//    @Override
//    public void onEvent(BaseEvent event) {
//        Logger.e( ((CardAddFinishEvent)event).getMessage().getStringExtra(Extra.CARD_ID));
//    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(CardAddFinishEvent event) {
        Intent intent = event.getMessage();
        int cardType = intent.getIntExtra(Extra.CARD_TYPE, -1);
        String cardId = intent.getStringExtra(Extra.CARD_ID);
        if (cardType == Extra.CARD_DEPOSIT) {
            mLlAddDeposit.setVisibility(View.GONE);
            mRvCard.setVisibility(View.VISIBLE);
            CardBean cardBean = new CardBean();
            cardBean.setCardType(cardType + "");
            cardBean.setCardId(cardId);
            mCardList.add(cardBean);
            mAdapter.notifyDataSetChanged();
        }

        //Logger.e(intent.getStringExtra(Extra.CARD_ID));
    }
}
