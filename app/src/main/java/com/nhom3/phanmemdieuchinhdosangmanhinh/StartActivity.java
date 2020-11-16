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
		setContentView(R.layout.activity_start);

		ImageButton btnLogin;
		btnLogin = (ImageButton) findViewById(R.id.imb_power);

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(StartActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});



	}
}

