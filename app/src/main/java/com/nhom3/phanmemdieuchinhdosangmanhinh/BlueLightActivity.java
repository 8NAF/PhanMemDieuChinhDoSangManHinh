package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class BlueLightActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blue_light);

		ViewPager viewPager =  findViewById(R.id.pager);
		BlueLightAdapter adapter = new BlueLightAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		viewPager.setAdapter(adapter);
		TabLayout tabLayout =  findViewById(R.id.tabDots);
		tabLayout.setupWithViewPager(viewPager, true);


	}
}