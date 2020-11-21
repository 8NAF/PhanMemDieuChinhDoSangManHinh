package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;

import java.util.ArrayList;

public class WallpaperActivity extends AppCompatActivity {

	GridView grvWallpaper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallpaper);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.wallpaper));

		this.mapped();
		this.grvWallpaper.setAdapter(getImageAdaptor());
	}

	void mapped() {
		this.grvWallpaper = this.findViewById(R.id.grv_wallpaper);
	}

	ImageAdapter getImageAdaptor() {

		ArrayList<Integer> imageIdList = new ArrayList<>();
		imageIdList.add(R.drawable.wallpaper1);
		imageIdList.add(R.drawable.wallpaper2);
		imageIdList.add(R.drawable.wallpaper3);
		imageIdList.add(R.drawable.wallpaper4);
		imageIdList.add(R.drawable.wallpaper5);
		imageIdList.add(R.drawable.wallpaper6);
		imageIdList.add(R.drawable.wallpaper7);

		return new ImageAdapter(this, imageIdList);
	}
}