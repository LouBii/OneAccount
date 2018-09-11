package com.loubii.account.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.bean.RecycleClassifyPagerBean;

import java.util.List;

/**
 * @author luo
 * @date 2017/8/18
 */
public class GridPagerAdapter extends BaseAdapter {

    public static final int NO_ITEM_SELECT = -1;
    private Context mContext;
    private List<RecycleClassifyPagerBean> mRecycleList;
    /**
     * 当前viewpager页数
     */
    private int mCurrentPage;
    /**
     * 每页显示条目数
     */
    private int mPageSize;
    /**
     * 当前选中条目位置
     */
    private int mSelectedPosition;

    public GridPagerAdapter(Context context, List<RecycleClassifyPagerBean> recycleList, int currentPage, int pageSize) {
        mContext = context;
        mRecycleList = recycleList;
        mCurrentPage = currentPage;
        mPageSize = pageSize;
        if (mCurrentPage != 0)
            mSelectedPosition = NO_ITEM_SELECT;
    }

    @Override
    public int getCount() {
        return mRecycleList.size() > (mCurrentPage + 1) * mPageSize ? mPageSize : (mRecycleList.size() - mCurrentPage * mPageSize);
    }

    @Override
    public Object getItem(int position) {
        return mRecycleList.get(position + mCurrentPage * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mCurrentPage * mPageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Logger.e("run--------->");
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_classify_pager, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvClassify = (TextView) convertView.findViewById(R.id.tv_classify);
            viewHolder.ivClassify = (ImageView) convertView.findViewById(R.id.iv_classify);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize
         */
        int pos = position + mCurrentPage * mPageSize;
        viewHolder.tvClassify.setText(mRecycleList.get(pos).getName());
        if (mSelectedPosition == position) {
            viewHolder.ivClassify.setImageResource(mRecycleList.get(pos).getIconRes());
            viewHolder.tvClassify.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.ivClassify.setImageResource(mRecycleList.get(pos).getIconResGray());
            viewHolder.tvClassify.setTextColor(mContext.getResources().getColor(R.color.colorTextPagerGray));
        }
        return convertView;
    }

    public void onItemSelected(int position) {
        if (mSelectedPosition == position)
            return;
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

//    /**
//     * 没有条目选中
//     */
//    public void onNoItemSelected() {
//        mSelectedPosition = -1;
//        notifyDataSetChanged();
//    }

    class ViewHolder {
        public TextView tvClassify;
        public ImageView ivClassify;
    }
}
