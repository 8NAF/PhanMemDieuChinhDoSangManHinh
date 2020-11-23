package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LanguageActivity extends AppCompatActivity {

	RadioGroup rdgLanguage;
	SharedMemory sharedMemory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language);

		getSupportActionBar().setTitle(getString(R.string.language));

		sharedMemory = new SharedMemory(this);
		RadioButton radioButton = findViewById(sharedMemory.getIdRadioButtonSelected());
		radioButton.setChecked(true);

		rdgLanguage = findViewById(R.id.rdg_language);
		rdgLanguage.setOnCheckedChangeListener((group, checkedId) -> {

			SharedMemory sharedMemory = new SharedMemory(LanguageActivity.this);
			sharedMemory.setIdRadioButtonSelected(checkedId);

			finish();
		});
	}
}