package com.loubii.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.bean.RemarkBean;

import java.util.List;

/**
 * 单选模式
 *
 * @author luo
 * @date 2017/8/14
 */
public class AddRemarkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_END = 1;
    private static final int TYPE_ITEM = 0;
    private static int TYPE = TYPE_ITEM;
    private final List<RemarkBean> mList;
    private Context context;

    private int mSelectedPos = -1;
    private OnItemChekedListener onItemChekedListener;

    private OnEndClickListener onEndClickListener;

    public AddRemarkAdapter(Context context, List<RemarkBean> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_add_remark, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemHoleder itemHoleder = (ItemHoleder) holder;
        itemHoleder.tvRemark.setText(mList.get(position).getName());

        switch (getItemViewType(position) ) {
            case TYPE_END :
                itemHoleder.tvRemark.setTextColor(context.getResources().getColor(R.color.color_dash));
                itemHoleder.tvRemark.setBackgroundResource(R.drawable.remark_text_dash_bg);
                break;
            case TYPE_ITEM :
                itemHoleder.tvRemark.setSelected(mList.get(position).isChecked());
                break;
            default :
                break;
        }

        itemHoleder.tvRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = itemHoleder.getAdapterPosition();
                if (getItemViewType(pos)  == TYPE_END) {
                    onEndClickListener.onEndClick();
                } else {
                    RemarkBean remarkBean = mList.get(pos);
                    //点击后取反当前位置
                    boolean selected = !remarkBean.isChecked();
                    remarkBean.setChecked(selected);
                    notifyItemChanged(pos);
                }

            }
        });
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

    public interface OnEndClickListener {
        void onEndClick();
    }

    public void setOnEndClickListener(OnEndClickListener onEndClickListener) {
        this.onEndClickListener = onEndClickListener;
    }


    public interface OnItemChekedListener {
        void onItemChecked(int position);
    }

    public void setOnItemCheckedListener(OnItemChekedListener onItemChekedListener) {
        this.onItemChekedListener = onItemChekedListener;
    }


    class ItemHoleder extends RecyclerView.ViewHolder {
        //RelativeLayout itemClassify;
        TextView tvRemark;

        public ItemHoleder(View itemView) {
            super(itemView);
            //itemClassify = (RelativeLayout) itemView.findViewById(R.id.item_classify);
            tvRemark = (TextView) itemView.findViewById(R.id.tv_remark);
        }
    }

}
