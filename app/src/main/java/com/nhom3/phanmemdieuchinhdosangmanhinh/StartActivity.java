package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

public class StartActivity extends AppCompatActivity {
	private TabLayout tabLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_start);

		ImageButton imbPower = (ImageButton) findViewById(R.id.imb_power);
		imbPower.setOnClickListener(new imbPower_OnClickListener());
	}

	class imbPower_OnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(StartActivity.this, MainActivity.class);
			StartActivity.this.startActivity(intent);
			StartActivity.this.finish();

		}
	}

}

