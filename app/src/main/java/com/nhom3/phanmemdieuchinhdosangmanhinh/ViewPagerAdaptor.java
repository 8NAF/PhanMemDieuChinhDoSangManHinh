package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdaptor extends FragmentPagerAdapter {


	private int numberTabs;

	public ViewPagerAdaptor(@NonNull FragmentManager fm, int behavior, int numberTabs) {
		super(fm, behavior);
		this.numberTabs = numberTabs;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {

		switch (position) {
			case 1: return new WallpaperFragment();
			case 0: return new HomeFragment();
		}
		return new HomeFragment();
	}

	@Override
	public int getCount() {
		return this.numberTabs;
	}
}
