package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class WallpaperActivity extends AppCompatActivity {

	GridView grvWallpaper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallpaper);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.wallpaper));

		this.mapped();
		//this.grvWallpaper.setAdapter(new ImageAdapter(this));
	}

	void mapped() {
		//this.grvWallpaper = this.findViewById(R.id.grv_wallpaper);
	}
}