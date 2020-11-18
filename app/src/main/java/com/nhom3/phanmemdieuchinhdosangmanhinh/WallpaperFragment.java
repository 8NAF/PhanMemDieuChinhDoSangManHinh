package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WallpaperFragment extends Fragment {

	//region No Use

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	public WallpaperFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment WallpaperFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static WallpaperFragment newInstance(String param1, String param2) {
		WallpaperFragment fragment = new WallpaperFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	//endregion
	//region Attributes

	Button btnSetWallpaper;
	GridView grvWallpaper;

	//endregion
	//region Override Methods

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_wallpaper,container,false);
		this.mapped(view);

		//Todo: Cần thay đổi mảng string thành danh sách các hình ảnh
		String[] array = {"A", "B", "C"};
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
			this.getActivity(),
			android.R.layout.simple_list_item_1,
			array
		);
		this.grvWallpaper.setAdapter(arrayAdapter);

		return view;
	}

	//endregion
	//region Helper Methods

	protected void mapped(View view) {
		this.grvWallpaper = view.findViewById(R.id.grv_wallpaper);
		this.btnSetWallpaper = view.findViewById(R.id.btn_set_wallpaper);
	}

	//endregion


}