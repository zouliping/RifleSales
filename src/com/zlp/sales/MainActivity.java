package com.zlp.sales;

import com.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends BaseActivity {

	private Fragment mContent;

	public MainActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager manager = getSupportFragmentManager();

		if (savedInstanceState != null) {
			mContent = manager.getFragment(savedInstanceState, "mContent");
		}
		if (mContent == null) {
			mContent = new SendDataFragment();
		}

		// …Ë÷√above view
		setContentView(R.layout.content_frame);
		manager.beginTransaction().replace(R.id.content_frame, mContent)
				.commit();

		// …Ë÷√behind view
		setBehindContentView(R.layout.menu_frame);
		manager.beginTransaction()
				.replace(R.id.menu_frame, new BehindMenuFragment()).commit();

		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	/**
	 * «–ªªfragment
	 * 
	 * @param fragment
	 */
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}
}
