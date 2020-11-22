package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class WallpaperDetailActivity extends AppCompatActivity {

	Button btnSetWallpaper;
	ImageView imvWallpaper;
	int idImageClicked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallpaper_detail);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.wallpaper));

		this.mapped();
		this.setListener();

		idImageClicked = (int) getIntent().getLongExtra("id_image", 0L);
		this.imvWallpaper.setImageResource(idImageClicked);
	}

	private void mapped() {
		this.btnSetWallpaper = this.findViewById(R.id.btn_set_wallpaper);
		this.imvWallpaper = this.findViewById(R.id.imv_wallpaper);
	}


	private void setListener() {
		this.btnSetWallpaper.setOnClickListener(new btnSetWallpaper_OnClickListener());
	}

	private class btnSetWallpaper_OnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
			try {
				wallpaperManager.setResource(idImageClicked);
				Toast.makeText(WallpaperDetailActivity.this, getString(R.string.set_success), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(WallpaperDetailActivity.this, getString(R.string.set_failed), Toast.LENGTH_SHORT).show();
			}
		}
	}

}