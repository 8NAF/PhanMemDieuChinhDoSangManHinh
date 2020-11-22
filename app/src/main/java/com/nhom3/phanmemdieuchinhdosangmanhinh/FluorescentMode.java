package com.nhom3.phanmemdieuchinhdosangmanhinh;

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
}
