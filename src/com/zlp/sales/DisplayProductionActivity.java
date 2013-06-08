package com.zlp.sales;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zlp.utils.AppKeys;
import com.zlp.utils.Utils;

/**
 * 展示商品详细信息页面
 * 
 * @author zouliping
 * 
 */
public class DisplayProductionActivity extends Activity {

	private Context mContext = DisplayProductionActivity.this;

	private int resId = 0;
	private int pcount = 1;
	private String pname = null;
	private String pdesc = null;

	private TextView tv_name;
	private TextView tv_desc;
	private ImageView iv_pic;
	private Button btn_add;
	private Button btn_sub;
	private Button btn_send;
	private EditText et_count;
	private EditText et_location;

	private Boolean canSale = true;
	private String result = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.setNoTitleAndNoStatusBarScreen(DisplayProductionActivity.this);
		setContentView(R.layout.display_production_layout);

		initData();
		initViews();

	}

	/**
	 * 初始化必要的数据
	 */
	private void initData() {
		Intent intent = getIntent();
		resId = intent.getIntExtra("production_view", 0);
		pname = intent.getStringExtra("production_name");
		pdesc = intent.getStringExtra("production_desc");
		canSale = intent.getBooleanExtra("can_sale", true);
		Log.d("get data", resId + pname + pdesc);
	}

	/**
	 * 初始化必要的views
	 */
	private void initViews() {
		tv_name = (TextView) findViewById(R.id.display_production_tv1);
		tv_desc = (TextView) findViewById(R.id.display_production_tv2);
		iv_pic = (ImageView) findViewById(R.id.display_production_iv);

		et_count = (EditText) findViewById(R.id.display_production_et2);
		et_count.addTextChangedListener(watcher);
		et_count.setText(pcount + "");
		et_location = (EditText) findViewById(R.id.display_production_et1);

		btn_add = (Button) findViewById(R.id.display_production_btn3);
		btn_sub = (Button) findViewById(R.id.display_production_btn2);
		btn_send = (Button) findViewById(R.id.display_production_btn4);
		btn_add.setOnClickListener(listener);
		btn_sub.setOnClickListener(listener);
		btn_send.setOnClickListener(listener);
		btn_sub.setEnabled(false);

		tv_name.setText(pname);
		tv_desc.setText(pdesc);
		iv_pic.setImageResource(resId);

		if (canSale == false) {
			btn_send.setEnabled(false);
		}
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.display_production_btn2:
				pcount = calcDishNum(pcount, btn_add, btn_sub, '-');
				et_count.setText(pcount + "");
				break;
			case R.id.display_production_btn3:
				pcount = calcDishNum(pcount, btn_add, btn_sub, '+');
				et_count.setText(pcount + "");
				break;
			case R.id.display_production_btn4:
				new SenDataTask().execute();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 监听数量editText的变化
	 */
	private TextWatcher watcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			try {
				int tmp = Integer.parseInt(s.toString());
				if (tmp < 0) {
					throw new NumberFormatException();
				}
				pcount = calcDishNum(tmp, btn_add, btn_sub, 'm');
			} catch (Exception e) {
			}
		}
	};

	/**
	 * 设置商品数量及计算商品数量，使能增加、减少按钮
	 * 
	 * @param currentNum
	 * @param addBtn
	 * @param subBtn
	 * @param flag
	 * @return
	 */
	private int calcDishNum(int currentNum, Button addBtn, Button subBtn,
			char flag) {
		int piece = 1;
		if (flag == '+') {
			int tmp = currentNum + piece;
			if (tmp > piece) {
				subBtn.setEnabled(true);
			}
			return tmp;
		} else if (flag == '-') {
			if (currentNum <= piece) {
				subBtn.setEnabled(false);
				return currentNum;
			}
			int tmp = currentNum - piece;
			if (tmp <= piece) {
				subBtn.setEnabled(false);
			}
			return tmp;
		} else if (flag == 'm') {
			if (currentNum <= piece) {
				subBtn.setEnabled(false);
			} else {
				subBtn.setEnabled(true);
			}
			return currentNum;
		} else {
			return currentNum;
		}
	}

	/**
	 * 修改可卖的数量
	 */
	private void changeNum() {
		if (AppKeys.stock.equals(pname)) {
			AppKeys.stockNum -= pcount;
		} else if (AppKeys.lock.equals(pname)) {
			AppKeys.lockNum -= pcount;
		} else if (AppKeys.barrel.equals(pname)) {
			AppKeys.barrelNum -= pcount;
		}
	}

	private class SenDataTask extends AsyncTask<String, Integer, String> {

		ProgressDialog dlg;

		@Override
		protected void onPreExecute() {
			dlg = new ProgressDialog(mContext);
			dlg.setTitle("Send Data");
			dlg.setMessage("please wait for a monent...");
			dlg.setCancelable(false);
			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dlg.show();

		}

		@Override
		protected String doInBackground(String... params) {
			DisplayProductionActivity.this.result = Utils
					.sendData2Server(AppKeys.SEND_SALES_DATA_URL
							.replace("$uname", AppKeys.uname)
							.replace("$location",
									et_location.getText().toString())
							.replace("$count", pcount + "")
							+ pname);
			Log.e("send re", result);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			dlg.dismiss();
			if ("1".equals(DisplayProductionActivity.this.result)) {
				Toast.makeText(mContext, "send success", Toast.LENGTH_SHORT)
						.show();
				changeNum();
				DisplayProductionActivity.this.finish();
			} else {
				Toast.makeText(mContext, "failure", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
