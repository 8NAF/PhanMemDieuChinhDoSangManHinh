package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;

public class CandleMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 126;
	}

	@Override
	public int getBlue() {
		return 0;
	}

	@Override
	public int getColorTemperature() {
		return 1800;
	}

	@Override
	public String getName(Context context) {
		return context.getString(R.string.candle_mode);
	}
}
