package com.nhom3.phanmemdieuchinhdosangmanhinh;

//1800K - Candle
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
}
