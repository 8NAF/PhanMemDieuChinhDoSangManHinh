package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

public class DawnMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 138;
	}

	@Override
	public int getBlue() {
		return 18;
	}

	@Override
	public String getColorTemperature() {
		return "2000K";
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.dawn_mode);
	}
}
