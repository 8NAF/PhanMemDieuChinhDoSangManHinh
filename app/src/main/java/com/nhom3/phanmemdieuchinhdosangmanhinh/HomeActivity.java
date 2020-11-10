package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		ImageView myBackGround = (ImageView) findViewById(R.id.imv_background);
		myBackGround.setImageResource(R.drawable.nighlight);

		ImageButton btnLogin;
		btnLogin = (ImageButton) findViewById(R.id.imb_power);

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HomeActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

	}
}

