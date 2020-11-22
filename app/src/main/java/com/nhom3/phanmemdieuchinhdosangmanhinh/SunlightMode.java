package com.nhom3.phanmemdieuchinhdosangmanhinh;

//4500K
public class SunlightMode implements IColorTemperatureMode{
	@Override
	public int getRed() {
		return 255;
	}

	@Override
	public int getGreen() {
		return 219;
	}

	@Override
	public int getBlue() {
		return 186;
	}

	@Override
	public int getColorTemperature() {
		return 4500;
	}
}
