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
	public String getColorTemperature() {
		return "3200K";
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.night_mode);
	}
}
