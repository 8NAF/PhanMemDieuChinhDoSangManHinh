package com.nhom3.phanmemdieuchinhdosangmanhinh;

import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    //region Attributes

    private TabLayout tabLayout;
    private ViewPager viewPager;

    //endregion
    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mapped();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
            this.getSupportFragmentManager(),
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        this.viewPager.setAdapter(viewPagerAdapter);
        this.tabLayout.setupWithViewPager(viewPager);
    }

    //endregion
    //region Helper Methods

    protected void mapped() {
        this.tabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        this.viewPager = (ViewPager) this.findViewById(R.id.view_pager);
    }

    //endregion
}

