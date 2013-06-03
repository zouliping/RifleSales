package com.zlp.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	private String pname = null;
	private String pdesc = null;

	private TextView tv_name;
	private TextView tv_desc;
	private ImageView iv_pic;

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

		tv_name.setText(pname);
		tv_desc.setText(pdesc);
		iv_pic.setImageResource(resId);
	}
}
