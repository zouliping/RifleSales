package com.zlp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlp.entity.Production;
import com.zlp.sales.R;

/**
 * 商品信息的adapter
 * 
 * @author zouliping
 * 
 */
public class SendDataAdapter extends BaseAdapter {

	private ArrayList<Production> productionList;
	private Context mContext;

	public SendDataAdapter(Context mContext,
			ArrayList<Production> productionList) {
		this.mContext = mContext;
		this.productionList = productionList;
	}

	@Override
	public int getCount() {
		return productionList.size();
	}

	@Override
	public Object getItem(int position) {
		return productionList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.production_item_layout, null);
			holder = new ItemHolder();
			holder.iv_pic = (ImageView) convertView
					.findViewById(R.id.production_item_iv1);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.production_item_tv1);
			holder.tv_desc = (TextView) convertView
					.findViewById(R.id.production_item_tv2);
			holder.iv_add = (ImageView) convertView
					.findViewById(R.id.production_item_iv2);
			convertView.setTag(holder);
		} else {
			holder = (ItemHolder) convertView.getTag();
		}

		Production tmp = productionList.get(position);
		holder.iv_pic.setImageResource(tmp.getPicRes());
		holder.tv_name.setText(tmp.getName());
		holder.tv_desc.setText(tmp.getDesc());
		holder.iv_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// try {
				// if (Integer.parseInt(Utils
				// .sendData2Server(AppKeys.SEND_SALES_DATA_URL)) != -1) {
				// Toast.makeText(mContext, "发送成功", Toast.LENGTH_SHORT)
				// .show();
				// } else {
				// Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT)
				// .show();
				// }
				// } catch (Exception e) {
				// e.printStackTrace();
				// Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
				// }
			}
		});
		return convertView;
	}

	private class ItemHolder {
		ImageView iv_pic;
		TextView tv_name;
		TextView tv_desc;
		ImageView iv_add;
	}

}
