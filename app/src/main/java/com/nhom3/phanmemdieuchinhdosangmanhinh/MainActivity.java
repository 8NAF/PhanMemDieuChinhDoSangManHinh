package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Locale;

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

	private ImageButton imbMoon;
	private ImageButton imbCandle;
	private ImageButton imbIncandescentLamp;
	private ImageButton imbFluorescentLamp;
	private ImageButton imbSunrise;
	private ImageButton imbEclipse;
	private ImageButton imbForest;
	private ImageButton imbSunlight;

	private TextView txvColorTemperature;
	private TextView txvIntensity;

	private SharedMemory mSharedMemory;

	private static final int OVERLAY_PERMISSION_CODE = 0;
	private static final int SELECT_LANGUAGE = 1;

	private static final HashMap<Integer, IColorTemperatureMode> mapMode;

	static {

			mapMode = new HashMap<>();

			mapMode.put(R.id.imb_moon, new NightMode());
			mapMode.put(R.id.imb_candle, new CandleMode());
			mapMode.put(R.id.imb_incandescent_lamp, new IncandescentMode());
			mapMode.put(R.id.imb_fluorescent_lamp, new FluorescentMode());
			mapMode.put(R.id.imb_sunrise, new DawnMode());
			mapMode.put(R.id.imb_eclipse, new EclipseMode());
			mapMode.put(R.id.imb_forest, new ForestMode());
			mapMode.put(R.id.imb_sunlight, new SunlightMode());
	}

	//endregion
	//region Override Methods


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setLocale();
		this.setContentView(R.layout.activity_main);

		//Ánh xạ các view vào các attribute
		this.mapped();

		//Khởi tạo cho các seek bar và mSharedMemory
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

		if (canNotDrawOverlays()) {
			String offText = getString(R.string.off);

			swtOnOff.setChecked(false);
			swtOnOff.setText(offText);
			mSharedMemory.setStateSwitch(false);
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
			case OVERLAY_PERMISSION_CODE:
				if (Settings.canDrawOverlays(this))
					startScreenFilterService();
				else {
					swtOnOff.setOnCheckedChangeListener(null);
					swtOnOff.setChecked(false);
					swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());

					mSharedMemory.setStateSwitch(false);
				}
				break;

			case SELECT_LANGUAGE:
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				finish();
				startActivity(intent);
				break;
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

		imbMoon = findViewById(R.id.imb_moon);
		imbCandle = findViewById(R.id.imb_candle);
		imbIncandescentLamp = findViewById(R.id.imb_incandescent_lamp);
		imbFluorescentLamp = findViewById(R.id.imb_fluorescent_lamp);
		imbSunrise = findViewById(R.id.imb_sunrise);
		imbEclipse = findViewById(R.id.imb_eclipse);
		imbForest = findViewById(R.id.imb_forest);
		imbSunlight = findViewById(R.id.imb_sunlight);

		txvColorTemperature = findViewById(R.id.txv_color_temperature);
		txvIntensity = findViewById(R.id.txv_intensity);
	}

	private void initialize() {

		mSharedMemory = new SharedMemory(this);
		skbIntensity.setProgress(mSharedMemory.getAlpha());
		txvIntensity.setText(mSharedMemory.getAlpha() + "");

		initializeSelectedImageButton();
		initializeColorTemperatureTextView();
		initializeSwitchOnOff();
	}

	private void initializeSwitchOnOff() {

		if (canNotDrawOverlays()) {
			String offText = getString(R.string.off);

			swtOnOff.setChecked(false);
			swtOnOff.setText(offText);
			mSharedMemory.setStateSwitch(false);
			return;
		}

		boolean isChecked = mSharedMemory.getStateSwitch();
		String text = (isChecked) ? getString(R.string.on) : getString(R.string.off);

		swtOnOff.setText(text);
		if (isChecked) {
			swtOnOff.setChecked(true);
			startScreenFilterService();
		}
	}

	private void initializeSelectedImageButton() {

		int selectedId = mSharedMemory.getIdImageButtonSelected();

		ImageButton previouslySelectedButton = findViewById(selectedId);
		previouslySelectedButton.setBackgroundResource(R.color.blue_500);
	}


	private void initializeColorTemperatureTextView() {

		int id = mSharedMemory.getIdImageButtonSelected();
		IColorTemperatureMode mode = mapMode.get(id);
		txvColorTemperature.setText(mode.getColorTemperature());
	}

	private void addOrSetListener() {
		this.ngvMain.setNavigationItemSelectedListener(new ngvMain_NavigationItemSelectedListener());
		this.dwlMain.addDrawerListener(new dwlMain_DrawerListener());
		this.tloMain.addOnTabSelectedListener(new tloMain_OnTabSelectedListener());

		skbIntensity.setOnSeekBarChangeListener(new skbIntensity_OnSeekBarChangeListener());
		swtOnOff.setOnCheckedChangeListener(new swtOnOff_OnCheckedChangeListener());

		imbMoon.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbCandle.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbIncandescentLamp.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbFluorescentLamp.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbSunrise.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbEclipse.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbForest.setOnClickListener(new ColorTemperatureMode_OnClickListener());
		imbSunlight.setOnClickListener(new ColorTemperatureMode_OnClickListener());
	}

	private void startScreenFilterService() {
		Intent intent = new Intent(this, ScreenFilterService.class);
		startService(intent);

		IColorTemperatureMode mode = mapMode.get(mSharedMemory.getIdImageButtonSelected());
		assert mode != null;
		Toast.makeText(
				this,
				mode.getName(this),
				Toast.LENGTH_SHORT
		).show();
	}

	private void stopScreenFilterService() {
		Intent intent = new Intent(this, ScreenFilterService.class);
		stopService(intent);
	}

	private boolean canNotDrawOverlays() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
				!Settings.canDrawOverlays(MainActivity.this);
	}

	private void setLocale() {

		mSharedMemory = new SharedMemory(this);
		String languageCode = mSharedMemory.getLanguageCode();

		Locale locale = new Locale(languageCode);
		Locale.setDefault(locale);
		Configuration configuration = new Configuration();
		configuration.locale = locale;

		Resources resources = getResources();
		resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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


			switch (item.getItemId()) {
				case R.id.mni_language: {
					Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
					startActivityForResult(intent, SELECT_LANGUAGE);
					break;
				}

				case R.id.mni_about_us: {
					Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
					startActivity(intent);
					break;
				}

				case R.id.mni_blue_light: {
					Intent intent = new Intent(MainActivity.this, BlueLightActivity.class);
					startActivity(intent);
					break;
				}

			}



			return true;
		}
	}

	class skbIntensity_OnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			int process = skbIntensity.getProgress();

			mSharedMemory.setAlpha(process);
			txvIntensity.setText(process + "");

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

			int idText = isChecked ? R.string.on : R.string.off;
			String text = getString(idText);

			buttonView.setText(text);
			mSharedMemory.setStateSwitch(isChecked);

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

	private class ColorTemperatureMode_OnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			int idPreviousSelected = mSharedMemory.getIdImageButtonSelected();

			ImageButton currentButton = (ImageButton) v;
			ImageButton previousButton = findViewById(idPreviousSelected);

			currentButton.setBackgroundResource(R.color.blue_500);
			if (currentButton != previousButton) {

				previousButton.setBackgroundResource(R.color.blue_sky);
				mSharedMemory.setIdImageButtonSelected(currentButton.getId());

				IColorTemperatureMode mode = mapMode.get(currentButton.getId());
				assert mode != null;
				mSharedMemory.setColorTemperatureMode(mode);
				txvColorTemperature.setText(mode.getColorTemperature());
			}

			if (swtOnOff.isChecked())
				startScreenFilterService();
		}
	}

	//endregion
}

