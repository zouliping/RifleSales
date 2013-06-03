package com.zlp.utils;

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
}
