package com.zlp.sales;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
			Toast.makeText(mContext, "账户或密码不能为空", Toast.LENGTH_SHORT).show();
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
			dlg.setTitle("登录");
			dlg.setMessage("正在登录，请稍后...");
			dlg.setCancelable(false);
			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dlg.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {
				URL url = new URL(AppKeys.LOGIN_URL);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("content-type", "text/html");
				connection.setDoOutput(true);

				JSONArray array = new JSONArray();
				JSONObject jo = new JSONObject();

				jo.put("uname", uname);
				jo.put("upwd", upwd);

				array.put(jo);

				OutputStream os = connection.getOutputStream();
				os.write(array.toString().getBytes("utf-8"));

				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStream is = connection.getInputStream();
					byte[] buf = new byte[2048];
					int count = is.read(buf, 0, buf.length);
					result = new String(buf, 0, count);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			dlg.dismiss();
			
		}
	}
}
