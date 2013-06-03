package com.zlp.sales;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BehindMenuFragment extends ListFragment {

	private Resources res;
	private String[] menuStrings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		res = getActivity().getResources();
		menuStrings = new String[] { res.getString(R.string.send_sale_data),
				res.getString(R.string.check_now),
				res.getString(R.string.check_history),
				res.getString(R.string.about) };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				menuStrings);
		setListAdapter(adapter);
	}

	/**
	 * ÇÐ»»Fragment
	 * 
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null) {
			return;
		}

		if (getActivity() instanceof MainActivity) {
			MainActivity main = (MainActivity) getActivity();
			main.switchContent(fragment);
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new SendDataFragment();
			break;
		case 1:
			fragment = new CheckNowFragment();
			break;
		case 2:
			fragment = new CheckHistoryFragment();
			break;
		case 3:
			fragment = new AboutFragment();
			break;
		}

		if (fragment != null) {
			switchFragment(fragment);
		}
	}
}
