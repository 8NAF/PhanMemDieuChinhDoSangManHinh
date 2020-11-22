package com.nhom3.phanmemdieuchinhdosangmanhinh;

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
	public int getColorTemperature() {
		return 2700;
	}
}
