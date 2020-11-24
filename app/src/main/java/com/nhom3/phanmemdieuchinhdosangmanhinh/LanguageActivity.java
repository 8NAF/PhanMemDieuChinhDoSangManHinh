package com.nhom3.phanmemdieuchinhdosangmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class LanguageActivity extends AppCompatActivity {

	RadioGroup rdgLanguage;
	SharedMemory sharedMemory;

	private static HashMap<String, Integer> mapId;
	private static HashMap<Integer, String> mapLanguageCode;

	static {
		mapId = new HashMap<>();

		mapId.put("vi", R.id.rdb_vn);
		mapId.put("en", R.id.rdb_en);

		mapLanguageCode = new HashMap<>();

		mapLanguageCode.put(R.id.rdb_vn, "vi");
		mapLanguageCode.put(R.id.rdb_en, "en");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language);

		getSupportActionBar().setTitle(getString(R.string.language));

		sharedMemory = new SharedMemory(this);

		int id = mapId.get(sharedMemory.getLanguageCode());

		RadioButton radioButton = findViewById(id);
		radioButton.setChecked(true);

		rdgLanguage = findViewById(R.id.rdg_language);
		rdgLanguage.setOnCheckedChangeListener((group, checkedId) -> {


			String languageCode = mapLanguageCode.get(checkedId);
			sharedMemory.setLanguageCode(languageCode);
			finish();
		});
	}
}