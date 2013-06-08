package com.zlp.sales;import java.util.ArrayList;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.support.v4.app.Fragment;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.AdapterView.OnItemClickListener;import android.widget.ListView;import com.zlp.adapter.SendDataAdapter;import com.zlp.entity.Production;/** * 发送销售数据 *  * @author zouliping *  */public class SendDataFragment extends Fragment {	private Context mContext;	private ArrayList<Production> productionList;	private View mainView;	private ListView productionListView;	private SendDataAdapter adapter;	@Override	public View onCreateView(LayoutInflater inflater, ViewGroup container,			Bundle savedInstanceState) {		mainView = inflater.inflate(R.layout.send_sale_data_layout, null);		mContext = getActivity();		initData();		initViews();		return mainView;	}	/**	 * 初始化必要的数据	 */	private void initData() {		productionList = new ArrayList<Production>();		initList();	}	/**	 * 初始化必要的view	 */	private void initViews() {		productionListView = (ListView) mainView				.findViewById(R.id.send_sale_data_list);		adapter = new SendDataAdapter(mContext, productionList);		productionListView.setAdapter(adapter);		productionListView.setOnItemClickListener(listener);	}	/**	 * 初始化商品信息	 */	private void initList() {		Production production1 = new Production(R.drawable.lock, "Locks",				"Locks ...");		Production production2 = new Production(R.drawable.stock, "Stocks",				"Stocks ...");		Production production3 = new Production(R.drawable.barrel, "Barrels",				"Barrels ...");		productionList.add(production1);		productionList.add(production2);		productionList.add(production3);	}	private OnItemClickListener listener = new OnItemClickListener() {		@Override		public void onItemClick(AdapterView<?> parent, View view, int position,				long id) {			Production tmp = productionList.get(position);			Intent intent = new Intent(mContext,					DisplayProductionActivity.class);			Bundle bundle = new Bundle();			bundle.putInt("production_view", tmp.getPicRes());			bundle.putString("production_name", tmp.getName());			bundle.putString("production_desc", tmp.getDesc());			Log.d("data", tmp.getPicRes() + tmp.getName() + tmp.getDesc());			intent.putExtras(bundle);			startActivity(intent);		}	};}