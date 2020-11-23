package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

//3400K
public class FluorescentMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 193;
	}

	@Override
	public int getBlue() {
		return 132;
	}

	@Override
	public int getColorTemperature() {
		return 3400;
	}

	@NonNull
	@Override
	public String getName(Context context) {
		return context.getString(R.string.fluorescent_mode);
	}
}
