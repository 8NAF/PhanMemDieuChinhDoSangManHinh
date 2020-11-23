package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

//4500K
public class SunlightMode implements IColorTemperatureMode{
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 219;
	}

	@Override
	public int getBlue() {
		return 186;
	}

	@Override
	public int getColorTemperature() {
		return 4500;
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.sunlight_mode);
	}
}
