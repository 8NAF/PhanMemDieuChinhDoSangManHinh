package com.nhom3.phanmemdieuchinhdosangmanhinh;

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
}
