package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
	//region Attributes

	ImageButton imbPower;

	//endregion
	//region Override Methods

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_start);

		this.mapped();
		this.setListener();
	}

	//endregion
	//region Helper Methods

	protected void mapped() {
		this.imbPower = this.findViewById(R.id.imb_power);
	}
	protected void setListener() {
		this.imbPower.setOnClickListener(new imbPower_OnClickListener());
	}

	//endregion
	//region Listener Classes

	class imbPower_OnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(StartActivity.this, MainActivity.class);
			StartActivity.this.startActivity(intent);
			StartActivity.this.finish();
		}
	}

	//endregion
}

