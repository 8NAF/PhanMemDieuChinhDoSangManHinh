package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import static android.view.WindowManager.LayoutParams.*;

//TODO: Lần đầu tiên service không chạy
public class ScreenFilterService extends Service {

	//region Attributes

	private SharedMemory sharedMemory;
	private View view;

	//endregion
	//region Constructors

	public ScreenFilterService() { }

	//endregion
	//region Override Methods

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		sharedMemory = new SharedMemory(this);
		view = new LinearLayout(this);
		view.setBackgroundColor(sharedMemory.getColor());

		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		params.width = MATCH_PARENT;
		params.height = getLengthOfLagerDimension() + 200;
		params.type = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ? TYPE_APPLICATION_OVERLAY : TYPE_PHONE;
		params.flags = FLAG_NOT_TOUCHABLE | FLAG_NOT_FOCUSABLE | FLAG_LAYOUT_NO_LIMITS;
		params.format = PixelFormat.TRANSLUCENT;

		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.addView(view, params);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		view.setBackgroundColor(sharedMemory.getColor());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.removeView(view);
	}

	//endregion
	//region Helper Methods

	private static int getLengthOfLagerDimension() {
		int width = Resources.getSystem().getDisplayMetrics().widthPixels;
		int height = Resources.getSystem().getDisplayMetrics().heightPixels;

		return Math.max(width, height);
	}

	//endregion
}
