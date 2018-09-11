package com.loubii.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.loubii.account.R;
import com.loubii.account.app.AccountApplication;
import com.loubii.account.bean.AccountModel;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.util.TimeUtil;
import com.loubii.account.util.ToastUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 左滑删除RecyclerView Adapter
 *
 * @author luo
 * @date 2017/8/14
 */
public class BillSwipeAdapter extends SwipeableUltimateViewAdapter<AccountModel> {

    private List<AccountModel> mAccountList;
    private OnDeleteListener mDeleteListener;

    public BillSwipeAdapter(List<AccountModel> mAccountList) {
        super(mAccountList);
        this.mAccountList = mAccountList;
    }

    @Override
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, AccountModel data, int position) {
        super.withBindHolder(holder, data, position);
        ItemHoleder itemHoleder = (ItemHoleder) holder;

        float count = mAccountList.get(position).getCount();
        String note = mAccountList.get(position).getNote();
        String remark = mAccountList.get(position).getRemark();

        itemHoleder.tvClassifyMoney.setText(count + "");
        itemHoleder.tvClassify.setText(mAccountList.get(position).getDetailType());
        itemHoleder.ivClassify.setImageResource(mAccountList.get(position).getPicRes());
        if ( TextUtils.isEmpty(note) && TextUtils.isEmpty(remark)) {
            itemHoleder.tvClassifyDescribe.setVisibility(View.GONE);
        } else {
            itemHoleder.tvClassifyDescribe.setText(note + "," +remark);
        }
//        itemHoleder.tvClassifyMoney.setText(mAccountList.get(position).getCount() + "");
//        itemHoleder.tvClassify.setText(mAccountList.get(position).getDetailType());
    }

    @Override
    protected int getNormalLayoutResId() {
        return R.layout.item_bill_list_swipe;
    }

    @Override
    protected UltimateRecyclerviewViewHolder newViewHolder(final View view) {
        final ItemHoleder itemHoleder = new ItemHoleder(view);
        itemHoleder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(itemHoleder.getPosition());
                //removeAt(itemHoleder.getPosition());

            }
        });

        return itemHoleder;
    }

    private void deleteItem(int position) {
        DBManager dbManager = AccountApplication.getDbManager();
        boolean isDetele = dbManager.delete(mAccountList.get(position));
        if (isDetele) {
            removeAt(position); //移除条目
            mDeleteListener.onDelete(); //删除回调
            ToastUtil.showShort("删除条目:" + position);
        } else
            ToastUtil.showShort("删除失败");
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void removeNotifyExternal(int pos) {
        closeItem(pos);
    }

    @Override
    public long generateHeaderId(int position) {
        return getDay(mAccountList.get(position).getTime());
        //return new Long( (long) position);
    }

    private long getDay(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stick_header, parent, false);
        return  new HeaderHoleder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int pos) {
        HeaderHoleder headerHoleder = (HeaderHoleder) holder;
        String time = TimeUtil.date2String(mAccountList.get(pos).getTime(), "MM月dd日");
        String week = TimeUtil.getWeekByDate(mAccountList.get(pos).getTime());
        headerHoleder.tvStickyDay.setText(time);
        headerHoleder.tvStickyWeek.setText(week);
        //Logger.e(generateHeaderId(position)+ "  -- > " + position);
        float sumMoney = 0f;
        for (int i = pos; i < mAccountList.size(); i++) {
            Date date = mAccountList.get(i).getTime();
            //从当前day往后循环判断，如果day相同则表示为同一天，否则跳出循环
            if (getDay(date) == generateHeaderId(pos)) {
                sumMoney += mAccountList.get(i).getCount();
            } else
                break;
        }
        int type = mAccountList.get(pos).getOutIntype();
        String strType;
        if (type == 1)
            strType = "支出：" + sumMoney;
        else
            strType = "收入：" + sumMoney;
        headerHoleder.tvStickyExpend.setVisibility(View.GONE);
        headerHoleder.tvStickyIncome.setText(strType);
        //tvStickyDay.setText(mAccountList.get(pos));
    }

    class ItemHoleder extends UltimateRecyclerviewViewHolder {

        ImageView ivClassify;
        TextView tvClassify;
        TextView tvClassifyDescribe;
        TextView tvClassifyMoney;
        LinearLayout llDelete;

        public ItemHoleder(View itemView) {
            super(itemView);
            ivClassify = (ImageView) itemView.findViewById(
                    R.id.iv_classify);
            tvClassify = (TextView) itemView.findViewById(R.id.tv_classify);
            tvClassifyDescribe = (TextView) itemView.findViewById(R.id.tv_classify_describe);
            tvClassifyMoney = (TextView) itemView.findViewById(R.id.tv_classify_money);
            llDelete = (LinearLayout) itemView.findViewById(R.id.ll_delete);
        }

    }


    class HeaderHoleder extends UltimateRecyclerviewViewHolder {

        TextView tvStickyDay;
        TextView tvStickyWeek;
        TextView tvStickyExpend;
        TextView tvStickyIncome;

        public HeaderHoleder(View itemView) {
            super(itemView);
            tvStickyDay = (TextView) itemView.findViewById(R.id.tv_sticky_day);
            tvStickyWeek = (TextView) itemView.findViewById(R.id.tv_sticky_week);
            tvStickyExpend = (TextView) itemView.findViewById(R.id.tv_sticky_expend);
            tvStickyIncome = (TextView) itemView.findViewById(R.id.tv_sticky_income);
        }
    }

    public interface OnDeleteListener{
        void onDelete();
    }

    public void setOnDeleteListener(OnDeleteListener deleteListener) {
        this.mDeleteListener = deleteListener;
    }
}
