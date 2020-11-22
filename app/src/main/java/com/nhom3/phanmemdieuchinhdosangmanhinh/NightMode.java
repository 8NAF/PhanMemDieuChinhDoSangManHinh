package com.nhom3.phanmemdieuchinhdosangmanhinh;

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
}
