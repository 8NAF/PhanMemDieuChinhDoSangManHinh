package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

	private int getValue(String prop, int def) {
		return sharedPreferences.getInt(prop, def);
	}
	private void setValue(String value, int v) {
		sharedPreferences.edit().putInt(value, v).apply();
	}

	public int getAlpha() {
		return getValue("alpha", 0x33);
	}
	public int getRed() {
		return getValue("red", 0);
	}
	public int getGreen() {
		return getValue("green", 0);
	}
	public int getBlue() {
		return getValue("blue", 0);
	}
	public int getColor() {
		return Color.argb(getAlpha(), getRed(), getGreen(), getBlue());
	}
	public String getTextSwitch() {
		String defaultValue = context.getString(R.string.off);
		return sharedPreferences.getString("text_switch", defaultValue);
	}

	public void setAlpha(int val) {
		setValue("alpha", val);
	}
	public void setRed(int val) {
		setValue("red", val);
	}
	public void setGreen(int val) {
		setValue("green", val);
	}
	public void setBlue(int val) {
		setValue("blue", val);
	}
	public void setTextSwitch(String text) {
		sharedPreferences.edit().putString("text_switch", text).apply();
	}

}
