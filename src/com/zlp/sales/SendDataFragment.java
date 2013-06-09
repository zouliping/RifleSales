package com.zlp.sales;import java.util.ArrayList;import android.app.ProgressDialog;import android.content.Context;import android.content.Intent;import android.os.AsyncTask;import android.os.Bundle;import android.support.v4.app.Fragment;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.View.OnClickListener;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.AdapterView.OnItemClickListener;import android.widget.Button;import android.widget.ListView;import android.widget.Toast;import com.zlp.adapter.SendDataAdapter;import com.zlp.entity.Production;import com.zlp.utils.AppKeys;import com.zlp.utils.Utils;/** * 发送销售数据 *  * @author zouliping *  */public class SendDataFragment extends Fragment {	private Context mContext;	private ArrayList<Production> productionList;	private View mainView;	private ListView productionListView;	private SendDataAdapter adapter;	private Boolean canSale = true;	private Button btn_js; // 结算	private String result;	@Override	public View onCreateView(LayoutInflater inflater, ViewGroup container,			Bundle savedInstanceState) {		mainView = inflater.inflate(R.layout.send_sale_data_layout, null);		mContext = getActivity();		initData();		initViews();		return mainView;	}	/**	 * 初始化必要的数据	 */	private void initData() {		productionList = new ArrayList<Production>();		initList();	}	/**	 * 初始化必要的view	 */	private void initViews() {		productionListView = (ListView) mainView				.findViewById(R.id.send_sale_data_list);		adapter = new SendDataAdapter(mContext, productionList);		productionListView.setAdapter(adapter);		productionListView.setOnItemClickListener(listener);		btn_js = (Button) mainView.findViewById(R.id.send_sale_data_btn);		btn_js.setOnClickListener(new OnClickListener() {			@Override			public void onClick(View v) {				canSale = false;				new GetCommissionTask().execute();			}		});	}	private class GetCommissionTask extends AsyncTask<String, Integer, String> {		ProgressDialog dlg;		@Override		protected void onPreExecute() {			dlg = new ProgressDialog(mContext);			dlg.setTitle("Get Commission");			dlg.setMessage("please wait for a monent...");			dlg.setCancelable(false);			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);			dlg.show();		}		@Override		protected String doInBackground(String... params) {			result = Utils.sendData2Server(AppKeys.GET_COMMISSION_URL.replace(					"$uname", AppKeys.uname) + "true");			Log.e("get re", result);			return result;		}		@Override		protected void onPostExecute(String result) {			dlg.dismiss();			if ("1".equals(result)) {				Toast.makeText(mContext, "get commission success",						Toast.LENGTH_SHORT).show();			} else {				Toast.makeText(mContext, "failure", Toast.LENGTH_SHORT).show();			}		}	}	/**	 * 初始化商品信息	 */	private void initList() {		Production production1 = new Production(				R.drawable.lock,				"lock",				"Lock is the part that hits the primer or firing pin or explodes the percussion cap and causes the gun to fire.");		Production production2 = new Production(				R.drawable.stock,				"stock",				"Stock is a component of the general rifle whose benefits cannot be overestimated.");		Production production3 = new Production(				R.drawable.barrel,				"barrel",				"Barrel is one of the main component parts of firearms and usually made of heat-resisting, uneasily-deformed metal tube.");		productionList.add(production1);		productionList.add(production2);		productionList.add(production3);	}	private OnItemClickListener listener = new OnItemClickListener() {		@Override		public void onItemClick(AdapterView<?> parent, View view, int position,				long id) {			Production tmp = productionList.get(position);			Intent intent = new Intent(mContext,					DisplayProductionActivity.class);			Bundle bundle = new Bundle();			bundle.putInt("production_view", tmp.getPicRes());			bundle.putString("production_name", tmp.getName());			bundle.putString("production_desc", tmp.getDesc());			bundle.putBoolean("can_sale", canSale);			Log.d("data", tmp.getPicRes() + tmp.getName() + tmp.getDesc());			intent.putExtras(bundle);			startActivity(intent);		}	};}