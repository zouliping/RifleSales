package com.zlp.utils;

public class AppKeys {

	public static String uname = "";

	public static final String HTTP_START = "http://10.0.2.2:8000";
	public static final String LOGIN_URL = HTTP_START + "/loginAndroid?uname=$uname&pwd=";
	public static final String SEND_SALES_DATA_URL = HTTP_START + "/checkinAndroid?uname=$uname&location=$location&count=$count&pname=";;
	public static final String GET_CUR_DATA_URL = "";
}
