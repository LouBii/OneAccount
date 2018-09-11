package com.loubii.account.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.bean.CardBean;
import com.loubii.account.constants.CardRes;
import com.loubii.account.constants.Extra;
import com.loubii.account.ui.card.CardRemindActivity;
import com.loubii.account.ui.card.TransferActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 单选模式
 *
 * @author luo
 * @date 2017/8/14
 */
public class CardAdapter extends BaseRecycleAdapter {


    private static final int TYPE_END = 1;
    private static final int TYPE_ITEM = 0;
    private static int TYPE = TYPE_ITEM;
    private final List<CardBean> mList;

    private Context context;


    public CardAdapter(Context context, List<CardBean> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false));
        else if (viewType == TYPE_END)
            return new EndHoleder(LayoutInflater.from(context).inflate(R.layout.item_card_add, parent, false));
        else
            return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        switch (getItemViewType(position)) {
            case TYPE_END:
                EndHoleder endHoleder = (EndHoleder) holder;
                //endHoleder.tvRemark.setTextColor(context.getResources().getColor(R.color.color_dash));
                break;
            case TYPE_ITEM:
                ItemHoleder itemHoleder = (ItemHoleder) holder;
                if (position == 0) {
                    itemHoleder.mTvTransfer.setText("换卡");
                    itemHoleder.mIvAlarm.setVisibility(View.GONE);
                }
                itemHoleder.mTvBandcardName.setText(mList.get(position).getCardName());
                itemHoleder.itemView.setBackgroundResource(CardRes.getCardBg(mList.get(position).getCardName()));
                itemHoleder.mTvTransfer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, TransferActivity.class);
                        intent.putExtra(Extra.CARD_ID, mList.get(position).getCardId());
                        context.startActivity(intent);
                    }
                });
                itemHoleder.mIvAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CardRemindActivity.class);
                        intent.putExtra(Extra.CARD_REMIND_START_TYPE, "FragmentCard");
                        intent.putExtra(Extra.CARD_ID, mList.get(position).getCardId());
                        context.startActivity(intent);
                    }
                });
                //itemHoleder.tvRemark.setSelected(mList.get(position).isChecked());
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size() - 1)
            TYPE = TYPE_END;
        else
            TYPE = TYPE_ITEM;
        return TYPE;
    }


    class ItemHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_bandcard)
        ImageView mIvBandcard;
        @BindView(R.id.tv_bandcard_name)
        TextView mTvBandcardName;
        @BindView(R.id.tv_bandcard_type)
        TextView mTvBandcardType;
        @BindView(R.id.tv_bandcard_id)
        TextView mTvBandcardId;
        @BindView(R.id.tv_transfer)
        TextView mTvTransfer;
        @BindView(R.id.iv_alarm)
        ImageView mIvAlarm;

        public ItemHoleder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //tv = (TextView) itemView.findViewById(R.id.tv_bandcard_name);
        }
    }

    class EndHoleder extends RecyclerView.ViewHolder {
        public EndHoleder(View itemView) {
            super(itemView);
        }
    }

}
