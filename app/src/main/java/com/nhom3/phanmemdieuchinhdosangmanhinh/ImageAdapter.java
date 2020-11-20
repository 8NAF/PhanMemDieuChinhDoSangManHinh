package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	Context context;
	int[] imageArray = {
		R.drawable.wallpaper1,
		R.drawable.wallpaper2,
		R.drawable.wallpaper3,
		R.drawable.wallpaper4,
		R.drawable.wallpaper5,
		R.drawable.wallpaper6,
		R.drawable.wallpaper7,
	};

	public ImageAdapter(Context context) {
		this.context = context;
	}


	@Override
	public int getCount() {
		return this.imageArray.length;
	}

	@Override
	public Object getItem(int position) {
		return this.imageArray[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imageView = new ImageView(this.context);
		imageView.setImageResource(this.imageArray[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));

		return imageView;
	}
}
