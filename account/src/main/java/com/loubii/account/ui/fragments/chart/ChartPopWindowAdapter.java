package com.loubii.account.ui.fragments.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;
import com.loubii.account.bean.AccountModel;

import java.util.List;

/**
 * @author luo
 * @date 2017/9/18
 */
public class ChartPopWindowAdapter extends BaseAdapter {

    private Context mContext;
    private List<AccountModel> mPopList;

    public ChartPopWindowAdapter(Context context,List<AccountModel> popWindowBeanList) {
        this.mContext = context;
        this.mPopList = popWindowBeanList;
    }

    @Override
    public int getCount() {
        return mPopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView ==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chart_popup, null);
            holder = new ViewHolder();
            holder.mTvClassify = (TextView) convertView.findViewById(R.id.tv_classify);
            holder.mIvClassify = (ImageView) convertView.findViewById(R.id.iv_classify);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvClassify.setText(mPopList.get(position).getDetailType());
        holder.mIvClassify.setImageResource(mPopList.get(position).getPicRes());
        return convertView;
    }

    class ViewHolder {
        TextView mTvClassify;
        ImageView mIvClassify;
    }
}
