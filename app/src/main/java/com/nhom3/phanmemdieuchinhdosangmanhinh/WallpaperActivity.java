package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
		this.setListener();
	}

	private void mapped() {
		this.grvWallpaper = this.findViewById(R.id.grv_wallpaper);
	}

	private ImageAdapter getImageAdaptor() {

		ArrayList<Integer> imageIdList = new ArrayList<>();
		imageIdList.add(R.drawable.wallpaper1);
		imageIdList.add(R.drawable.wallpaper2);
		imageIdList.add(R.drawable.wallpaper3);
		imageIdList.add(R.drawable.wallpaper4);
		imageIdList.add(R.drawable.wallpaper5);
		imageIdList.add(R.drawable.wallpaper6);
		imageIdList.add(R.drawable.wallpaper7);
		imageIdList.add(R.drawable.wallpaper8);
		imageIdList.add(R.drawable.wallpaper9);
		imageIdList.add(R.drawable.wallpaper10);

		return new ImageAdapter(this, imageIdList);
	}

	private void setListener() {
		this.grvWallpaper.setOnItemClickListener(new grvWallpaper_OnItemClickListener());
	}

	private class grvWallpaper_OnItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			Intent intent = new Intent(WallpaperActivity.this, WallpaperDetailActivity.class);
			intent.putExtra("id_image", id);
			startActivity(intent);
		}
	}

}