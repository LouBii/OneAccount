package com.loubii.account.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.constants.CenterRes;
import com.loubii.account.ui.avtivity.BalanceActivity;
import com.loubii.account.ui.avtivity.BudgetActivity;
import com.loubii.account.ui.avtivity.RemindManagerActivity;
import com.loubii.account.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 单选模式
 *
 * @author luo
 * @date 2017/8/14
 */
public class CenterAdapter extends BaseRecycleAdapter{


    private static final int TYPE_END = 1;
    private static final int TYPE_ITEM = 0;
    private static int TYPE = TYPE_ITEM;


    private Context context;


    public CenterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_center, parent, false));
        else if (viewType == TYPE_END) {
            TextView textView = new TextView(context);
            textView.setText("退出登录");
            textView.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            textView.setTextSize(15);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, DensityUtil.dip2px(30), 0, 0);
            textView.setLayoutParams(layoutParams);
            return new EndHoleder(textView);
            //return new EndHoleder(LayoutInflater.from(context).inflate(R.layout.item_card_add, parent, false));
        }

        else
            return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        switch (getItemViewType(position)) {
            case TYPE_END:

                break;
            case TYPE_ITEM:
                ItemHoleder itemHoleder = (ItemHoleder) holder;
                itemHoleder.mTvCenter.setText(CenterRes.NAMES[position]);
                itemHoleder.mIvCenter.setImageResource(CenterRes.ICONS[position]);
                itemHoleder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0 :
                                startActivity(BalanceActivity.class);
                                break;
                            case 1 :
                                //startActivity(BalanceActivity.class);
                                break;
                            case 2 :
                                //startActivity(BalanceActivity.class);
                                break;
                            case 3 :
                                startActivity(RemindManagerActivity.class);
                                break;
                            case 4 :
                                startActivity(BudgetActivity.class);
                                break;
                            default :
                                break;
                        }
                    }
                });
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return CenterRes.NAMES.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == CenterRes.NAMES.length)
            TYPE = TYPE_END;
        else
            TYPE = TYPE_ITEM;
        return TYPE;
    }



    private void startActivity(Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }


    class ItemHoleder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_center)
        ImageView mIvCenter;
        @BindView(R.id.tv_center)
        TextView mTvCenter;
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
