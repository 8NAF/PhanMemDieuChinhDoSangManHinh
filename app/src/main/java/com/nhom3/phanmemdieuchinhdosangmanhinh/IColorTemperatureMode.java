package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

import androidx.annotation.NonNull;

public interface IColorTemperatureMode {
	int getRed();
	int getGreen();
	int getBlue();
	String getColorTemperature();
	@NonNull
	String getName(Context context);
}
