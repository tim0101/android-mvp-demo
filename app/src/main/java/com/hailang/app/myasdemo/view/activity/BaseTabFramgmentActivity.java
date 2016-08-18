package com.hailang.app.myasdemo.view.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailang.app.myasdemo.R;
import com.hailang.app.myasdemo.view.widget.TabPageIndicator;

import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

public abstract class BaseTabFramgmentActivity extends AppCompatActivity {
	protected ViewPager mViewPager;
	protected TabsAdapter mTabsAdapter;
	protected TabPageIndicator tabPageIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentLayout());
		initView();
		if (savedInstanceState != null) {
			tabPageIndicator.setCurrentItem(savedInstanceState.getInt("tab"));
		}
	}

	public abstract int getContentLayout();
	public void setPagers(int page) {
		mViewPager.setOffscreenPageLimit(page);
	}

	protected void initView() {

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mViewPager.setAdapter(mTabsAdapter);

		tabPageIndicator = (TabPageIndicator)findViewById(R.id.indicator);
		tabPageIndicator.setViewPager(mViewPager);
	}

	protected void setCurrentTab(int index) {
		tabPageIndicator.setCurrentItem(index);
	}
	protected void setCurrentPage(int index) {
		mViewPager.setCurrentItem(index);
	}
	protected void addTab(String tag,String title,
			Class<? extends Fragment> f, Bundle b) {
		mTabsAdapter.addTab(tag,title,f, b);
	}
//
//	protected void addTab(String title, int icon, String tag,
//			Class<? extends Fragment> f, Bundle b) {
//		mTabsAdapter.addTab(
//				mTabHost.newTabSpec(tag).setIndicator(
//						getTabItemView(title, icon)), f, b);
//	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", tabPageIndicator.getCurrentItem());
	}

	/**
	 * This is a helper class that implements the management of tabs and all
	 * details of connecting a ViewPager with associated TabHost. It relies on a
	 * trick. Normally a tab host has a simple API for supplying a View or
	 * Intent that each tab will show. This is not sufficient for switching
	 * between pages. So instead we make the content part of the tab host 0dp
	 * high (it is not shown) and the TabsAdapter supplies its own dummy view to
	 * show as the tab content. It listens to changes in tabs, and takes care of
	 * switch to the correct paged in the ViewPager whenever the selected tab
	 * changes.
	 */
	public class TabsAdapter extends FragmentPagerAdapter {
		private final Context mContext;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(FragmentActivity activity,
				ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mViewPager = pager;
		}

		public void addTab(String tag,String title,Class<?> clss, Bundle args) {
			tabPageIndicator.addTab(getTabItemView(tag,title));
			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		Map<String, Fragment> fMap = new HashMap<String, Fragment>();

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			Fragment f;
			if (fMap.get(info.tag) == null) {
				f = Fragment.instantiate(mContext, info.clss.getName(),
						info.args);
				fMap.put(info.tag, f);
			} else {
				f = fMap.get(info.tag);
			}
			return f;
		}

	}

	protected abstract View getTabItemView(String tag,String title);

//	protected abstract View getTabItemView(String text, int icon);

}
