package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
	//region Attributes

	DrawerLayout dwlMain;
	ActionBarDrawerToggle toggle;
	NavigationView ngvMain;
	Toolbar tlbMain;

	//endregion
	//region Override Methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.mapped();

		this.setSupportActionBar(this.tlbMain);
		this.toggle = new ActionBarDrawerToggle(
			this,
			this.dwlMain,
			this.tlbMain,
			R.string.open,
			R.string.close
		);
		this.dwlMain.addDrawerListener(toggle);
		this.toggle.setDrawerIndicatorEnabled(true);
		this.toggle.syncState();
		this.toggle.getDrawerArrowDrawable().setColor(this.getColor(R.color.white));
	}

	//endregion
	//region Helper Methods

	protected void mapped() {
		this.tlbMain = (Toolbar) this.findViewById(R.id.tlb_main);
		this.dwlMain = (DrawerLayout) this.findViewById(R.id.dwl_main);
		this.ngvMain = (NavigationView) this.findViewById(R.id.ngv_main);
	}

	//endregion
}

