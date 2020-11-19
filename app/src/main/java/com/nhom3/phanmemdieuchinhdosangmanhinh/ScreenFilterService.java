package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ScreenFilterService extends Service {

	private SharedMemory mSharedMemory;
	private View mView;

	public ScreenFilterService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mSharedMemory = new SharedMemory(this);
		mView = new LinearLayout(this);
		mView.setBackgroundColor(mSharedMemory.getColor());

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
				280,
				PixelFormat.TRANSLUCENT
		);
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.addView(mView, layoutParams);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mView.setBackgroundColor(mSharedMemory.getColor());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.removeView(mView);
	}
}
