package com.zlp.sales;import android.content.Context;import android.os.Bundle;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;public class AboutFragment extends Fragment {	private Context mContext;	private View mainView;	private TextView tvAbout;	@Override	public View onCreateView(LayoutInflater inflater, ViewGroup container,			Bundle savedInstanceState) {		mainView = inflater.inflate(R.layout.about_layout, null);		mContext = getActivity();		initViews();		return mainView;	}	private void initViews() {		tvAbout = (TextView) mainView.findViewById(R.id.about_tv);	}}