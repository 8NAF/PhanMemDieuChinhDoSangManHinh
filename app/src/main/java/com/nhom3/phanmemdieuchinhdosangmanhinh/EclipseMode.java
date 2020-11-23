package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

//5000K
public class EclipseMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 228;
	}

	@Override
	public int getBlue() {
		return 206;
	}

	@Override
	public String getColorTemperature() {
		return "5000K";
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.eclipse_mode);
	}
}
