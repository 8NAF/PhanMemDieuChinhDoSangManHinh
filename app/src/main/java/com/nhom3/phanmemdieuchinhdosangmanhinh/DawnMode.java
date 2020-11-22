package com.nhom3.phanmemdieuchinhdosangmanhinh;

public class DawnMode implements IColorTemperatureMode {
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 138;
	}

	@Override
	public int getBlue() {
		return 18;
	}

	@Override
	public int getColorTemperature() {
		return 2000;
	}
}
