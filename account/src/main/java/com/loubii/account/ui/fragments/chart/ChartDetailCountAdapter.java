package com.loubii.account.ui.fragments.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.adapter.BaseRecycleAdapter;
import com.loubii.account.util.NumUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author luo
 * @date 2017/8/14
 */
public class ChartDetailCountAdapter extends BaseRecycleAdapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static int TYPE = TYPE_ITEM;
    private final List<ChartDataBean> mChartList;

    private Context context;

    public ChartDetailCountAdapter(Context context, List<ChartDataBean> list) {
        this.context = context;
        this.mChartList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.item_chart_count_header, parent, false));
        } else
            return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_chart_count, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        switch (getItemViewType(position)) {
            case TYPE_HEAD:

                break;
            case TYPE_ITEM:
                int pos = position - 1; //减去header布局
                final ItemHolder itemHoleder = (ItemHolder) holder;
                itemHoleder.mIvClassify.setImageResource(mChartList.get(pos).getImgRes());
                itemHoleder.mTvClassifyName.setText(mChartList.get(pos).getName());
                itemHoleder.mTvClassifyCount.setText(mChartList.get(pos).getCount() + "笔");
                String percent = NumUtil.getTwoPointFloat(mChartList.get(pos).getPrecent() * 100);
                itemHoleder.mTvClassifyPercent.setText(percent + "%");
                itemHoleder.mTvClassifyTotal.setText(mChartList.get(pos).getTotal() + "");
                //startProgressBarAnimation(itemHoleder, (int)(Math.random() * 100));
                float progress = NumUtil.getPointFloat(mChartList.get(pos).getPrecent(), 2);
                itemHoleder.mPbClassify.setProgress((int) ( progress * 100));
                break;
            default:
                break;
        }
    }

    /**
     * pregressbar动画 (卡顿)
     *
     * @param itemHoleder
     * @param progress
     */
    private void startProgressBarAnimation(final ItemHolder itemHoleder, int progress) {
        ValueAnimator animator = ValueAnimator.ofInt(0, progress);
        animator.setDuration(1000l);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                itemHoleder.mPbClassify.setProgress(value);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChartList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            TYPE = TYPE_HEAD;
        else
            TYPE = TYPE_ITEM;
        return TYPE;
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_header)
        TextView mTvHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_classify)
        ImageView mIvClassify;
        @BindView(R.id.tv_classify_name)
        TextView mTvClassifyName;
        @BindView(R.id.tv_classify_count)
        TextView mTvClassifyCount;
        @BindView(R.id.tv_classify_percent)
        TextView mTvClassifyPercent;
        @BindView(R.id.tv_classify_total)
        TextView mTvClassifyTotal;
        @BindView(R.id.pb_classify)
        ProgressBar mPbClassify;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
