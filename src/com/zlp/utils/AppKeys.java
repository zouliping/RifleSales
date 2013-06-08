package com.zlp.utils;

public class AppKeys {

	public static String uname = "";
	public static Integer stockNum = 80;
	public static Integer lockNum = 70;
	public static Integer barrelNum = 90;

	public static final String stock = "stock";
	public static final String lock = "lock";
	public static final String barrel = "barrel";

	public static final String HTTP_START = "http://219.217.227.89";
	public static final String LOGIN_URL = HTTP_START
			+ "/loginAndroid?uname=$uname&pwd=";
	public static final String SEND_SALES_DATA_URL = HTTP_START
			+ "/checkinAndroid?uname=$uname&location=$location&count=$count&pname=";;
	public static final String GET_CUR_DATA_URL = HTTP_START + "";
	public static final String SEND_GET_COMMISSION_URL = HTTP_START + "";
	public static final String GET_COMMISSION_URL = HTTP_START
			+ "/commissionAndroid?uname=$uname&commission=";
}
