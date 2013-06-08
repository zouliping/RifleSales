package com.zlp.sales;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zlp.utils.AppKeys;
import com.zlp.utils.Utils;

/**
 * 登录
 * 
 * @author zouliping
 * 
 */
public class LoginActivity extends Activity {

	private Context mContext = LoginActivity.this;

	private EditText et_name;
	private EditText et_pwd;

	private Button btn_login;

	private String uname;
	private String upwd;
	private String result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.setNoTitleAndNoStatusBarScreen(LoginActivity.this);
		setContentView(R.layout.login_layout);

		initViews();
	}

	/**
	 * 初始化必要的View
	 */
	private void initViews() {
		et_name = (EditText) findViewById(R.id.login_name);
		et_pwd = (EditText) findViewById(R.id.login_pwd);
		btn_login = (Button) findViewById(R.id.login_btn);
		btn_login.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_btn:
				getNameAndPwd();
				new LoginTask().execute();
				break;
			default:
				break;
			}
		}

	};

	/**
	 * 获取账户和密码
	 */
	private void getNameAndPwd() {
		uname = et_name.getText().toString();
		upwd = et_pwd.getText().toString();

		if (("".equals(uname)) || ("".equals(upwd))) {
			Toast.makeText(mContext, "username or password can not be null",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 登录task
	 * 
	 * @author zouliping
	 * 
	 */
	private class LoginTask extends AsyncTask<String, Integer, String> {

		ProgressDialog dlg;

		@Override
		protected void onPreExecute() {
			dlg = new ProgressDialog(mContext);
			dlg.setTitle("Login");
			dlg.setMessage("please wait for a monent...");
			dlg.setCancelable(false);
			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dlg.show();

		}

		@Override
		protected String doInBackground(String... params) {
			LoginActivity.this.result = Utils.sendData2Server(AppKeys.LOGIN_URL
					.replace("$uname", uname) + upwd);
			Log.e("login re", result);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			dlg.dismiss();
			if ("-1".equals(LoginActivity.this.result)) {
				Toast.makeText(mContext, "Login failed! Please try again",
						Toast.LENGTH_SHORT).show();
			} else {
				AppKeys.uname = uname;
				Toast.makeText(mContext, "Login successfully",
						Toast.LENGTH_SHORT).show();
				LoginActivity.this.finish();
				Intent intent = new Intent(mContext, MainActivity.class);
				startActivity(intent);
			}
		}
	}
}
