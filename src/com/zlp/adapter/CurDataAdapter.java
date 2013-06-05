package com.zlp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlp.entity.CurData;
import com.zlp.sales.R;

public class CurDataAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<CurData> curDataList;

	public CurDataAdapter(Context mContext, ArrayList<CurData> curDataList) {
		this.mContext = mContext;
		this.curDataList = curDataList;
	}

	@Override
	public int getCount() {
		return curDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return curDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CurDaTaHolder holder = null;
		if (convertView == null) {
			holder = new CurDaTaHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.cur_data_item, null);
			holder.tv_location = (TextView) convertView
					.findViewById(R.id.cur_data_item_location);
			holder.tv_pname = (TextView) convertView
					.findViewById(R.id.cur_data_item_pname);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.cur_data_item_count);
			convertView.setTag(holder);
		} else {
			holder = (CurDaTaHolder) convertView.getTag();
		}

		CurData tmp = curDataList.get(position);
		holder.tv_location.setText(tmp.getLocation());
		holder.tv_pname.setText(tmp.getPname());
		holder.tv_count.setText(tmp.getCount());

		return convertView;
	}

	private class CurDaTaHolder {
		TextView tv_location;
		TextView tv_pname;
		TextView tv_count;
	}

}
