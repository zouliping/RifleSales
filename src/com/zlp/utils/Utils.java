package com.zlp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * App的工具类
 * 
 * @author liushuai
 * 
 */
public class Utils {

	/**
	 * 设置全屏无标题栏且无状态栏
	 * 
	 * @param activity
	 */
	public static void setNoTitleAndNoStatusBarScreen(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * 将浮点数的精度控制为小数点后一位
	 * 
	 * @param tmp
	 *            浮点数
	 * @return 符合精度的字符串
	 * 
	 * @author zouliping
	 */
	public static String myFormat(Double tmp) {
		DecimalFormat format = new DecimalFormat("#0.0");
		return format.format(tmp);
	}

	/**
	 * 向服务器发送数据
	 * 
	 * @param key
	 * @return
	 */
	public static String sendData2Server(String key) {
		String result = null;
		try {
			URL url = new URL(key);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				byte[] buf = new byte[2048];
				int count = is.read(buf, 0, buf.length);
				result = new String(buf, 0, count);
				return result;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
