package com.loubii.account.ui.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.constants.CardRes;
import com.loubii.account.constants.ColorParms;
import com.loubii.account.constants.Extra;
import com.loubii.account.event.CardAddFinishEvent;
import com.loubii.account.ui.avtivity.BaseToolBarActivity;
import com.loubii.account.util.BankInfoBean;
import com.loubii.account.util.DataUtil;
import com.loubii.account.util.ToastUtil;
import com.loubii.account.view.BankCardEditText;
import com.loubii.account.view.DeleteEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class CardAddActivity extends BaseToolBarActivity {

    @BindView(R.id.et_card_id)
    BankCardEditText mEtCardId;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.tv_card_belong)
    TextView mTvCardBelong;
    @BindView(R.id.iv_card)
    ImageView mIvCard;
    @BindView(R.id.tv_card_type)
    TextView mTvCardType;
    @BindView(R.id.rel_choose_bank)
    RelativeLayout mRelChooseBank;
    @BindView(R.id.et_name)
    DeleteEditText mEtName;
    @BindView(R.id.et_peopleid)
    DeleteEditText mEtPeopleid;
    @BindView(R.id.et_phone_num)
    DeleteEditText mEtPhoneNum;
    @BindView(R.id.rl_phone_num)
    RelativeLayout mRlPhoneNum;
    @BindView(R.id.tv_refund)
    TextView mTvRefund;
    @BindView(R.id.rl_refund)
    RelativeLayout mRlRefund;
    @BindView(R.id.tv_charge_off)
    TextView mTvChargeOff;
    @BindView(R.id.rl_charge_off)
    RelativeLayout mRlChargeOff;
    @BindView(R.id.et_limit)
    DeleteEditText mEtLimit;
    @BindView(R.id.rl_limit)
    RelativeLayout mRlLimit;
    @BindView(R.id.lin_credit_card)
    LinearLayout mLinCreditCard;
    @BindView(R.id.cb_agree)
    CheckBox mCbAgree;
    @BindView(R.id.tv_service)
    TextView mTvService;
    private int mCardType;
    private String mBankBelong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_add;
    }

    @Override
    protected void initView() {
        initTitle();
        setListener();
    }

    private void setListener() {
        mEtCardId.setOnCardPatternListener(new BankCardEditText.OnCardPatternListener() {
            @Override
            public void onCardPattern(String name) {
                mBankBelong = name;
                if (!TextUtils.isEmpty(name)) {
                    setBankBelong(name);
                } else {
                    mIvCard.setVisibility(View.GONE);
                    mTvCardBelong.setText(getResources().getString(R.string.card_add_choose_belong_bank));
                }

            }
        });
    }

    /**
     * 所属银行
     * @param name
     */
    private void setBankBelong(String name) {
        mTvCardBelong.setText(name);
        mTvCardBelong.setTextColor(getResources().getColor(R.color.colorTextCardAddEdittext));
        mIvCard.setVisibility(View.VISIBLE);
        CardRes.setImgByName(name, mIvCard);
    }

    private void initTitle() {
        mCardType = getIntent().getIntExtra(Extra.CARD_TYPE, -1);
        setTitle(mCardType == Extra.CARD_DEPOSIT ? "添加储蓄卡" : "添加信用卡");
        if (mCardType == Extra.CARD_CREDIT) {
            mRlPhoneNum.setVisibility(View.GONE);
            mLinCreditCard.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.rel_choose_bank, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_choose_bank:
                if (TextUtils.isEmpty(mBankBelong)) {
                    if (mEtCardId.length() > 15) {
                        mBankBelong = new BankInfoBean(mEtCardId.getBankCardText()).getBankName();
                        if (!TextUtils.isEmpty(mBankBelong))
                            setBankBelong(mBankBelong);
                        else
                            startActivityForResult(new Intent(mContext, CardBelongActivity.class), Extra.requestCode.CARD_BELONG);
                    } else
                        startActivityForResult(new Intent(mContext, CardBelongActivity.class), Extra.requestCode.CARD_BELONG);
                }
                break;
            case R.id.bt_next:
                if ( !checkText()) {
                    Intent intent = new Intent();
                    intent.putExtra(Extra.CARD_TYPE, mCardType);
                    intent.putExtra(Extra.CARD_ID, mEtCardId.getBankCardText());
                    //消息推送
                    EventBus.getDefault().post(new CardAddFinishEvent(intent));
                    finish();
                    //startActivity(new Intent(mContext));
                }
                break;
        }
    }

    private boolean checkText() {
        boolean isTextRight = true;
        String mCardId = mEtCardId.getBankCardText();
        String mCardBelong = mTvCardBelong.getText().toString().trim();
        String mName = mEtName.getText().toString().trim();
        String mPeopleId = mEtPeopleid.getText().toString().trim();
        String mNumPhone = "";
        if (mCardType == Extra.CARD_DEPOSIT)
            mNumPhone = mEtPhoneNum.getText().toString().trim();
//                mDate = et_useful_date.getText().toString().trim();
//                mSafeMa = et_safe_ma.getText().toString().trim();


        if (!DataUtil.isChinese(mName))
            isTextRight = mEtName.toWrongModel();
        if (!DataUtil.isBankCard(mCardId))
            isTextRight = mEtCardId.toWrongModel();
        if (!DataUtil.isPhone(mNumPhone))
            isTextRight = mEtPhoneNum.toWrongModel();
        if (!DataUtil.isIdentity(mPeopleId))
            isTextRight = mEtPeopleid.toWrongModel();
        if (mCardBelong.equals("请选择所属银行")) {
            mTvCardBelong.setTextColor(ColorParms.COLOR_EDITTEXT_DELETE_WRONG);
        }
        if (!mCbAgree.isChecked()) {
            ToastUtil.showShort("请勾选服务协议");
        }
        return isTextRight;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Extra.resultCode.CARD_BELONG) {
            int cardResIndex = data.getIntExtra(Extra.CARD_RES_INDEX, -1);
            if (cardResIndex != -1) {
                setBankBelong(CardRes.getCardName(cardResIndex));
            }
        }
    }
}
