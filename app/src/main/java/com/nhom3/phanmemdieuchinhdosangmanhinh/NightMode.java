package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

//3200K - moon
public class NightMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 187;
	}

	@Override
	public int getBlue() {
		return 120;
	}

	@Override
	public int getColorTemperature() {
		return 3200;
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.night_mode);
	}
}
