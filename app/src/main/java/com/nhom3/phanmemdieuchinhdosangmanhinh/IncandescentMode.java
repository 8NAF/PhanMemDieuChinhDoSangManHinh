package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

public class IncandescentMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 169;
	}

	@Override
	public int getBlue() {
		return 87;
	}

	@Override
	public String getColorTemperature() {
		return "2700K";
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.incandescent_mode);
	}
}
