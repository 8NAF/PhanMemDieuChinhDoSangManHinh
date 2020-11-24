package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public class SharedMemory {

	//region Attributes

	private SharedPreferences sharedPreferences;
	private Context context;

	//endregion
	//region Constructors

	public SharedMemory(Context context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences("SCREEN_FILTER", Context.MODE_PRIVATE);
	}

	//endregion
	//region Setters

	private void setIntValue(String value, int v) {
		sharedPreferences.edit().putInt(value, v).apply();
	}

	public void setAlpha(int val) {
		setIntValue("alpha", val);
	}
	private void setRed(int val) {
		setIntValue("red", val);
	}
	private void setGreen(int val) {
		setIntValue("green", val);
	}
	private void setBlue(int val) {
		setIntValue("blue", val);
	}
	public void setStateSwitch(boolean isChecked) {
		sharedPreferences.edit().putBoolean("state_switch", isChecked).apply();
	}
	public void setIdImageButtonSelected(int id) {
		setIntValue("id_image_button", id);
	}
	public void setColorTemperatureMode(IColorTemperatureMode colorTemperatureMode) {
		setRed(colorTemperatureMode.getRed());
		setGreen(colorTemperatureMode.getGreen());
		setBlue(colorTemperatureMode.getBlue());
	}
	public void setLanguageCode(String languageCode) {
		sharedPreferences.edit().putString("language_code", languageCode).apply();
	}

	//endregion
	//region Getters

	private int getValue(String prop, int def) {
		return sharedPreferences.getInt(prop, def);
	}

	public int getColor() {
		return Color.argb(getAlpha(), getRed(), getGreen(), getBlue());
	}
	public int getAlpha() {
		return getValue("alpha", 0x33);
	}
	public int getRed() {
		return getValue("red", new NightMode().getRed());
	}
	public int getGreen() {
		return getValue("green", new NightMode().getGreen());
	}
	public int getBlue() {
		return getValue("blue", new NightMode().getBlue());
	}
	public boolean getStateSwitch() {
		String defaultValue = context.getString(R.string.off);
		return sharedPreferences.getBoolean("state_switch", false);
	}
	public int getIdImageButtonSelected() {
		return getValue("id_image_button", R.id.imb_moon);
	}
	public String getLanguageCode() {
		return sharedPreferences.getString("language_code", "en");
	}

	//endregion


}
