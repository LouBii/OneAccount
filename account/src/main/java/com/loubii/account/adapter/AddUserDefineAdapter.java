package com.loubii.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.loubii.account.R;
import com.loubii.account.bean.RecycleClassifyPagerBean;

import java.util.List;

/**
 * 单选模式
 * @author luo
 * @date 2017/8/14
 */
public class AddUserDefineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<RecycleClassifyPagerBean> mList;
    private Context context;

    private int mSelectedPos = -1;
    private OnItemChekedListener onItemChekedListener;

    public AddUserDefineAdapter(Context context, List<RecycleClassifyPagerBean> list) {
        this.context = context;
        this.mList = list;
        // 设置数据集时，找到默认选中的pos
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                mSelectedPos = i;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_add_userdefine, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ItemHoleder itemHoleder = (ItemHoleder) holder;
        itemHoleder.ivClassify.setImageResource(mList.get(position).getIconRes());
        if (mList.get(position).isChecked())
            itemHoleder.ivChecked.setVisibility(View.VISIBLE);
        else
            itemHoleder.ivChecked.setVisibility(View.INVISIBLE);

        itemHoleder.itemClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notifyItemChanged() 定向刷新两个视图
                //如果勾选的不是已经勾选状态的Item
                if (mSelectedPos != position) {
                    //选中回调
                    onItemChekedListener.onItemChecked(position);
                    //先取消上个item的勾选状态
                    mList.get(mSelectedPos).setChecked(false);
                    notifyItemChanged(mSelectedPos);
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mList.get(mSelectedPos).setChecked(true);
                    notifyItemChanged(mSelectedPos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemChekedListener {
        void onItemChecked(int position);
    }
    public void setOnItemCheckedListener(OnItemChekedListener onItemChekedListener) {
        this.onItemChekedListener= onItemChekedListener;
    }


    class ItemHoleder extends RecyclerView.ViewHolder {
        RelativeLayout itemClassify;
        ImageView ivClassify;
        ImageView ivChecked;

        public ItemHoleder(View itemView) {
            super(itemView);
            itemClassify = (RelativeLayout) itemView.findViewById(R.id.item_classify);
            ivClassify = (ImageView) itemView.findViewById(R.id.iv_classify);
            ivChecked = (ImageView) itemView.findViewById(R.id.iv_checked);
        }
    }

}
