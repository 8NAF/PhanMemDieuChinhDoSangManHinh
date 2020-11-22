package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	//region Attributes

	private Toolbar tlbMain;

	private DrawerLayout dwlMain;
	private NavigationView ngvMain;

	private TabLayout tloMain;
	private TabItem titHome;
	private TabItem titWallpaper;

	private SwitchMaterial swtOnOff;
	private SeekBar skbIntensity;

	private SharedMemory mSharedMemory;
	private static final int OVERLAY_PERMISSION_CODE = 0;

	private ImageButton preSelectedImageButton;
	private HashMap<Integer, IColorTemperatureMode> mapColor;
	private HashMap<Integer, String> mapTitle;

	//endregion
	//region Override Methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		//Ánh xạ các view vào các attribute
		this.mapped();

		//Khởi tạo cho các seek bar và mSharedMemory
		//Khởi tạo cho switch không có trong method này
		//Mà nó nằm trong onResume để tránh start service 2 lần không cần thiết
		this.initialize();

		this.setSupportActionBar(this.tlbMain);
		//Đặt null để NavigationView cho phép thay đổi icon của các menu item
		this.ngvMain.setItemIconTintList(null);

		this.addOrSetListener();


		ImageButton imageButton1 = findViewById(R.id.imb_moon);
		ImageButton imageButton2 = findViewById(R.id.imb_candle);
		ImageButton imageButton3 = findViewById(R.id.imb_incandescent_lamp);
		ImageButton imageButton4 = findViewById(R.id.imb_fluorescent_lamp);
		ImageButton imageButton5 = findViewById(R.id.imb_sunrise);
		ImageButton imageButton6 = findViewById(R.id.imb_eclipse);
		ImageButton imageButton7 = findViewById(R.id.imb_forest);
		ImageButton imageButton8 = findViewById(R.id.imb_sunlight);

		mapColor = new HashMap<>();

		mapColor.put(R.id.imb_moon, new NightMode());
		mapColor.put(R.id.imb_candle, new CandleMode());
		mapColor.put(R.id.imb_incandescent_lamp, new IncandescentMode());
		mapColor.put(R.id.imb_fluorescent_lamp, new FluorescentMode());
		mapColor.put(R.id.imb_sunrise, new DawnMode());
		mapColor.put(R.id.imb_eclipse, new EclipseMode());
		mapColor.put(R.id.imb_forest, new ForestMode());
		mapColor.put(R.id.imb_sunlight, new SunlightMode());

		mapTitle = new HashMap<>();

		mapTitle.put(R.id.imb_moon, getString(R.string.night_mode));
		mapTitle.put(R.id.imb_candle, getString(R.string.candle_mode));
		mapTitle.put(R.id.imb_incandescent_lamp, getString(R.string.incandescent_mode));
		mapTitle.put(R.id.imb_fluorescent_lamp, getString(R.string.fluorescent_mode));
		mapTitle.put(R.id.imb_sunrise, getString(R.string.dawn_mode));
		mapTitle.put(R.id.imb_eclipse, getString(R.string.eclipse_mode));
		mapTitle.put(R.id.imb_forest, getString(R.string.forest_mode));
		mapTitle.put(R.id.imb_sunlight, getString(R.string.sunlight_mode));

		SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);

		preSelectedImageButton = findViewById(sharedPreferences.getInt("id", R.id.imv_moon));
		if (preSelectedImageButton == null)
			preSelectedImageButton = imageButton1;
		preSelectedImageButton.setBackgroundResource(R.color.blue_500);


		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageButton imageButton = (ImageButton) v;
				imageButton.setBackgroundResource(R.color.blue_500);
				if (imageButton != preSelectedImageButton) {
					preSelectedImageButton.setBackgroundResource(R.color.blue_sky);
					preSelectedImageButton = imageButton;
				}

				SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("id", Context.MODE_PRIVATE);
				sharedPreferences.edit().putInt("id", imageButton.getId()).apply();

				IColorTemperatureMode mode = mapColor.get(imageButton.getId());
				mSharedMemory.setRed(mode.getRed());
				mSharedMemory.setGreen(mode.getGreen());
				mSharedMemory.setBlue(mode.getBlue());

				if (swtOnOff.isChecked()) {
					Toast.makeText(MainActivity.this, mapTitle.get(imageButton.getId()), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MainActivity.this, ScreenFilterService.class);
					MainActivity.this.startService(intent);
				}

			}
		};

		imageButton1.setOnClickListener(onClickListener);
		imageButton2.setOnClickListener(onClickListener);
		imageButton3.setOnClickListener(onClickListener);
		imageButton4.setOnClickListener(onClickListener);
		imageButton5.setOnClickListener(onClickListener);
		imageButton6.setOnClickListener(onClickListener);
		imageButton7.setOnClickListener(onClickListener);
		imageButton8.setOnClickListener(onClickListener);




	}

	@Override
	protected void onResume() {
		super.onResume();
		this.tloMain.getTabAt(0).select();
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == OVERLAY_PERMISSION_CODE) {
			if (Settings.canDrawOverlays(this))
				startScreenFilterService();
			else {
				swtOnOff.setOnCheckedChangeListener(null);
				swtOnOff.setChecked(false);
				swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());

				mSharedMemory.setTextSwitch(getString(R.string.off));
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	//endregion
	//region Helper Methods

	private void mapped() {
		tlbMain = this.findViewById(R.id.tlb_main);

		dwlMain = this.findViewById(R.id.dwl_main);
		ngvMain = this.findViewById(R.id.ngv_main);

		tloMain = this.findViewById(R.id.tlo_main);
		titHome = this.findViewById(R.id.tit_home);
		titWallpaper = this.findViewById(R.id.tit_wallpaper);

		swtOnOff = this.findViewById(R.id.swt_on_off);
		skbIntensity = this.findViewById(R.id.skb_intensity);
	}

	private void initialize() {

		mSharedMemory = new SharedMemory(this);

		skbIntensity.setProgress(mSharedMemory.getAlpha());

		initializeSwitchOnOff();


	}

	private void initializeSwitchOnOff() {

		if (canNotDrawOverlays()) {

			String offText = getString(R.string.off);

			swtOnOff.setChecked(false);
			swtOnOff.setText(offText);
			mSharedMemory.setTextSwitch(offText);
			return;
		}

		swtOnOff.setText(mSharedMemory.getTextSwitch());
		if (swtOnOff.getText().equals(getString(R.string.on))) {
			swtOnOff.setChecked(true);
			startScreenFilterService();
		}
	}

	private void addOrSetListener() {
		this.ngvMain.setNavigationItemSelectedListener(new ngvMain_NavigationItemSelectedListener());
		this.dwlMain.addDrawerListener(new dwlMain_DrawerListener());
		this.tloMain.addOnTabSelectedListener(new tloMain_OnTabSelectedListener());

		skbIntensity.setOnSeekBarChangeListener(new skbIntensity_OnSeekBarChangeListener());
		swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());
	}

	private void startScreenFilterService() {
		Intent intent = new Intent(this, ScreenFilterService.class);
		startService(intent);
	}

	private void stopScreenFilterService() {
		Intent intent = new Intent(this, ScreenFilterService.class);
		stopService(intent);
	}

	private boolean canNotDrawOverlays() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
				!Settings.canDrawOverlays(MainActivity.this);
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
			this.getDrawerArrowDrawable().setColor(MainActivity.this.getResources().getColor(R.color.white));
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

	class skbIntensity_OnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			mSharedMemory.setAlpha(skbIntensity.getProgress());

			if (swtOnOff.isChecked())
				startScreenFilterService();
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

			if (! isChecked) {
				stopScreenFilterService();
				return;
			}

			if (canNotDrawOverlays()) {
				requestOverlayPermission();
				return;
			}

			startScreenFilterService();
		}

		@RequiresApi(api = Build.VERSION_CODES.M)
		private void requestOverlayPermission() {

			Intent promptTheUserToGrant = new Intent (
				Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
				Uri.parse("package:" + getPackageName())
			);
			startActivityForResult(promptTheUserToGrant, OVERLAY_PERMISSION_CODE);
		}
	}

	//endregion
}

