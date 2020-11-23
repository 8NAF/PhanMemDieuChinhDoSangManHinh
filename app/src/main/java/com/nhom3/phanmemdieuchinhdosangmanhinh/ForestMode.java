package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

//3300K
public class ForestMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 190;
	}

	@Override
	public int getBlue() {
		return 126;
	}

	@Override
	public int getColorTemperature() {
		return 3300;
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.forest_mode);
	}
}
