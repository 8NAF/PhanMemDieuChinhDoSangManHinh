package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
	//region Attributes

	DrawerLayout dwlMain;
	NavigationView ngvMain;
	Toolbar tlbMain;
	TabLayout tloMain;
	TabItem titHome;
	TabItem titWallpaper;
	ViewPager vpgMain;
	SwitchMaterial swtOnOff;

	//endregion
	//region Override Methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.mapped();
		this.setSupportActionBar(this.tlbMain);
		this.setAdapter();
		this.setListener();
		this.addListener();
		this.ngvMain.setItemIconTintList(null); //Đặt null để có thể thêm icon cho các menu item
	}

	//endregion
	//region Helper Methods

	protected void mapped() {
		this.dwlMain = this.findViewById(R.id.dwl_main);
		this.tlbMain = this.findViewById(R.id.tlb_main);
		this.ngvMain = this.findViewById(R.id.ngv_main);
		this.tloMain = this.findViewById(R.id.tlo_main);
		this.titHome = this.findViewById(R.id.tit_home);
		this.titWallpaper = this.findViewById(R.id.tit_wallpaper);
		this.vpgMain = this.findViewById(R.id.vpg_main);
		this.swtOnOff = this.findViewById(R.id.swt_on_off);
	}

	void setAdapter() {
		ViewPagerAdaptor viewPagerAdaptor = new ViewPagerAdaptor(
			this.getSupportFragmentManager(),
			FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
			this.tloMain.getTabCount()
		);
		this.vpgMain.setAdapter(viewPagerAdaptor);
	}

	void setListener() {
		this.ngvMain.setNavigationItemSelectedListener(new ngvMain_NavigationItemSelectedListener());
		this.swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());
	}

	void addListener() {
		this.dwlMain.addDrawerListener(new dwlMain_DrawerListener());
		this.tloMain.addOnTabSelectedListener(new tloMain_OnTabSelectedListener());
		this.vpgMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tloMain));
	}

	//endregion
	//region Listener Class

	class dwlMain_DrawerListener extends ActionBarDrawerToggle {

		public dwlMain_DrawerListener() {
			super(
				MainActivity.this,
				MainActivity.this.dwlMain,
				MainActivity.this.tlbMain,
				R.string.open,
				R.string.close
			);

			this.setDrawerIndicatorEnabled(true);
			this.syncState();
			this.getDrawerArrowDrawable().setColor(MainActivity.this.getColor(R.color.white));
		}
	}
	class tloMain_OnTabSelectedListener implements TabLayout.OnTabSelectedListener {

		@Override
		public void onTabSelected(TabLayout.Tab tab) {
			MainActivity.this.vpgMain.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(TabLayout.Tab tab) {

		}

		@Override
		public void onTabReselected(TabLayout.Tab tab) {

		}
	}
	class ngvMain_NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {


		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			return false;
		}
	}
	class swtOnOff_OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			int idOnOff = (isChecked) ? R.string.on : R.string.off;
			String textOnOff = MainActivity.this.getString(idOnOff);
			MainActivity.this.swtOnOff.setText(textOnOff);
		}
	}

	//endregion
}

