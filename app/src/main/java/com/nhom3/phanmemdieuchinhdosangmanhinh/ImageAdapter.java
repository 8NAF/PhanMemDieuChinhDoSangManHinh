package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

	//region Attributes

	private Context context;
	private List<Integer> imageIdList;

	//endregion
	//region Constructors

	public ImageAdapter(Context context, List<Integer> imageIdList) {
		this.context = context;
		this.imageIdList = imageIdList;
	}

	//endregion
	//Override Methods

	@Override
	public int getCount() {
		return this.imageIdList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.imageIdList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.imageIdList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int screenWidth = metrics.widthPixels;
		int screenHeight = metrics.heightPixels;

		ImageView imageView = new ImageView(this.context);
		imageView.setImageResource(this.imageIdList.get(position));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams((screenWidth - 30)/2, screenHeight/2));


		return imageView;
	}

	//endregion
}
