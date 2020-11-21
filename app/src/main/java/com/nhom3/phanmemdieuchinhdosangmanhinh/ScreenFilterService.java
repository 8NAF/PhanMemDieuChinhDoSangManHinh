package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

//TODO: Lần đầu tiên service không chạy

public class ScreenFilterService extends Service {

	private SharedMemory mSharedMemory;
	private View mView;

	public ScreenFilterService() { }

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

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.width = MATCH_PARENT;
		layoutParams.height = getLengthOfLagerDimension() + 200;
		layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
		layoutParams.flags =
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
		layoutParams.format = PixelFormat.TRANSLUCENT;

		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.addView(mView, layoutParams);
	}


	public static int getLengthOfLagerDimension() {
		int width = Resources.getSystem().getDisplayMetrics().widthPixels;
		int height = Resources.getSystem().getDisplayMetrics().heightPixels;

		return Math.max(width, height);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, getString(R.string.start_service), Toast.LENGTH_SHORT).show();
		mView.setBackgroundColor(mSharedMemory.getColor());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, getString(R.string.stop_service), Toast.LENGTH_SHORT).show();
		super.onDestroy();
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		assert windowManager != null;
		windowManager.removeView(mView);
	}
}
