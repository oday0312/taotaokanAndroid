package com.example.taotaokanAndroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.taotaokanAndroid.gridView.GalleryImageAdapter;
import com.viewpagerindicator.IconPagerAdapter;

class TestFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    protected static final String[] CONTENT = new String[] { "Main", "2",   };
    protected static final int[] ICONS = new int[] {
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            R.drawable.perm_group_location
    };

    private int mCount = CONTENT.length;


    public MainAcitivtyFragment f2;
    public MainAcitivtyFragmentFirstPage f1;
    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
        f1 =  MainAcitivtyFragmentFirstPage.newInstance("",0);
        f2 =  MainAcitivtyFragment.newInstance("",1);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f;
        if (position == 0)
        {
            f =  MainAcitivtyFragmentFirstPage.newInstance("",position);

        }
        else
        {
            f = MainAcitivtyFragment.newInstance("",position);

        }
        return f;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }

    @Override
    public int getIconResId(int index) {
      return ICONS[index % ICONS.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}