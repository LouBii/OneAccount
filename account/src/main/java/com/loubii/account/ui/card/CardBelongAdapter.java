package com.loubii.account.ui.card;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.adapter.BaseRecycleAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author luo
 * @date 2017/9/1
 */
public class CardBelongAdapter extends BaseRecycleAdapter {
    private final List<Map<String, Object>> mData;
    private Context mContext;

    public CardBelongAdapter(Context context, List<Map<String, Object>> adapterData) {
        this.mData = adapterData;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_card_belong, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final ItemViewHolder itemHoleder = (ItemViewHolder) holder;
        itemHoleder.ivBank.setImageResource((int) mData.get(position).get("img"));
        itemHoleder.tvBankName.setText((String) mData.get(position).get("name"));
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder, int layoutPosition) {
        ((ItemViewHolder) holder).ivBankChecked.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBank;
        ImageView ivBankChecked;
        TextView tvBankName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ivBank = (ImageView) itemView.findViewById(R.id.iv_bank);
            ivBankChecked = (ImageView) itemView.findViewById(R.id.iv_bank_checked);
            tvBankName = (TextView) itemView.findViewById(R.id.tv_bank_name);
        }
    }


}
