package net.alhazmy13.hijridatepickerexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.legacy.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    ViewPager viewPager;
    PickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new PickerAdapter(getFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<adapter.getCount();i++) tabLayout.getTabAt(i).setText(adapter.getTitle(i));
    }

    private class PickerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 3;
        Fragment timePickerFragment;
        Fragment datePickerFragment;
        Fragment hijriPickerFragment;

        public PickerAdapter(FragmentManager fm) {
            super(fm);
            timePickerFragment = new TimePickerFragment();
            datePickerFragment = new GregorianDatePickerFragment();
            hijriPickerFragment = new HijriDatePickerFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return timePickerFragment;
                case 1:
                    return datePickerFragment;
                case 2:
                    return hijriPickerFragment;
                default:
                    return datePickerFragment;
            }
        }

        public int getTitle(int position) {
            switch(position) {
                case 0:
                    return R.string.tab_title_time;
                case 1:
                    return R.string.tab_title_gregorian_date;
                case 2:
                    return R.string.tab_title_hijri_date;
                default:
                    return R.string.tab_title_gregorian_date;
            }
        }
    }
}
