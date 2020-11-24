package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BlueLightAdapter extends FragmentStatePagerAdapter {



	public BlueLightAdapter(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch (position) {

			case 1:
				return new SecondBlueFilterFragment();

			case 0:
			default:
				return new FirstBlueLightFragment();
		}
	}

	@Override
	public int getCount() {
		return 2;
	}
}
