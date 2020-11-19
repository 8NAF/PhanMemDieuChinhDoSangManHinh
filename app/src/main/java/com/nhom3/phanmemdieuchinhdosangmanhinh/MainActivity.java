package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
	//region Attributes

	Toolbar tlbMain;

	DrawerLayout dwlMain;
	NavigationView ngvMain;

	TabLayout tloMain;
	TabItem titHome;
	TabItem titWallpaper;

	SwitchMaterial swtOnOff;
	SeekBar skbAlpha;
	SeekBar skbRed;
	SeekBar skbGreen;
	SeekBar skbBlue;

	private SharedMemory mSharedMemory;
	private CountDownTimer mCountDownTimer;

	//endregion
	//region Override Methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		//Ánh xạ các view vào các attribute
		this.mapped();

		//Khởi tạo giá trị ban đầu cho các seek bar, switch và mSharedMemory
		this.initialize();

		this.setSupportActionBar(this.tlbMain);
		//Đặt null để NavigationView cho phép thay đổi icon của các menu item
		this.ngvMain.setItemIconTintList(null);

		this.addOrSetListener();

	}

	@Override
	protected void onResume() {
		super.onResume();
		this.tloMain.getTabAt(0).select();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1234) { //Trùng với số trong phương thức onCheckedChanged
			if (resultCode == Activity.RESULT_OK) {
				Intent i = new Intent(MainActivity.this, ScreenFilterService.class);
				startService(i);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	//endregion
	//region Helper Methods

	protected void mapped() {
		tlbMain = this.findViewById(R.id.tlb_main);

		dwlMain = this.findViewById(R.id.dwl_main);
		ngvMain = this.findViewById(R.id.ngv_main);

		tloMain = this.findViewById(R.id.tlo_main);
		titHome = this.findViewById(R.id.tit_home);
		titWallpaper = this.findViewById(R.id.tit_wallpaper);

		swtOnOff = this.findViewById(R.id.swt_on_off);
		skbAlpha = this.findViewById(R.id.skbAlpha);
		skbRed = this.findViewById(R.id.skbRed);
		skbGreen = this.findViewById(R.id.skbGreen);
		skbBlue = this.findViewById(R.id.skbBlue);
	}

	void initialize() {

		mSharedMemory = new SharedMemory(this);

		skbAlpha.setProgress(mSharedMemory.getAlpha());
		skbRed.setProgress(mSharedMemory.getRed());
		skbGreen.setProgress(mSharedMemory.getGreen());
		skbBlue.setProgress(mSharedMemory.getBlue());

		swtOnOff.setText(mSharedMemory.getTextSwitch());
		if (swtOnOff.getText().equals(getString(R.string.on))) {
			//Method setChecked sẽ không có hiệu lực nếu switch có chứa sự kiện OnCheckedChangeListener
			//Vì vậy cần set sự kiện OnCheckedChangeListener null trước khi call setChecked
			//Sau đó set lại sự kiện cho switch
			swtOnOff.setOnCheckedChangeListener(null);
			swtOnOff.setChecked(true);
			swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());
		}
	}

	void addOrSetListener() {
		this.ngvMain.setNavigationItemSelectedListener(new ngvMain_NavigationItemSelectedListener());
		this.dwlMain.addDrawerListener(new dwlMain_DrawerListener());
		this.tloMain.addOnTabSelectedListener(new tloMain_OnTabSelectedListener());

		Color_OnSeekBarChangeListener changeListener = new Color_OnSeekBarChangeListener();
		skbAlpha.setOnSeekBarChangeListener(changeListener);
		skbRed.setOnSeekBarChangeListener(changeListener);
		skbGreen.setOnSeekBarChangeListener(changeListener);
		skbBlue.setOnSeekBarChangeListener(changeListener);

		swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());
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

			if (tab.getPosition() == 1) {
				Intent intent = new Intent(MainActivity.this, WallpaperActivity.class);
				MainActivity.this.startActivity(intent);
			}
		}

		@Override
		public void onTabUnselected(TabLayout.Tab tab) { }
		@Override
		public void onTabReselected(TabLayout.Tab tab) { }
	}

	class ngvMain_NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			return false;
		}
	}

	class Color_OnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			mSharedMemory.setAlpha(skbAlpha.getProgress());
			mSharedMemory.setRed(skbRed.getProgress());
			mSharedMemory.setGreen(skbGreen.getProgress());
			mSharedMemory.setBlue(skbBlue.getProgress());

			if (swtOnOff.isChecked()) {
				Intent intent = new Intent(MainActivity.this, ScreenFilterService.class);
				MainActivity.this.startService(intent);
			}


		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) { }

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) { }
	}

	class swtOnOff_OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			int id = isChecked ? R.string.on : R.string.off;
			String text = getString(id);
			buttonView.setText(text);
			mSharedMemory.setTextSwitch(text);

			Intent intent = new Intent(MainActivity.this, ScreenFilterService.class);

			if (! isChecked) {
				stopService(intent);
				return;
			}

			//Từ API 23 trở lên, một ứng dụng không thể nằm đè lên ứng dụng khác mà không cần quyền
			//Vì vậy ta cần thông báo cho người dùng cấp quyền này
			if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(MainActivity.this)) {
				Intent promptTheUserToGrant= new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
						Uri.parse("package:" + getPackageName()));
				startActivityForResult(promptTheUserToGrant, 1234); //Số ngẫu nhiên
				return;
			}

			startService(intent);
		}
	}

	//endregion
}

