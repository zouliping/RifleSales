package com.zlp.sales;

import android.app.Activity;
import android.content.Intent;
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

import com.zlp.utils.Utils;

/**
 * 展示商品详细信息页面
 * 
 * @author zouliping
 * 
 */
public class DisplayProductionActivity extends Activity {

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

}
