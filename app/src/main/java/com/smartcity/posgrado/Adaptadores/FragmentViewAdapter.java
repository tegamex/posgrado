package com.smartcity.posgrado.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartcity.posgrado.Fragment.GridNFragment;
import com.smartcity.posgrado.Fragment.ImageNFragment;
import com.smartcity.posgrado.Fragment.ListaNFragment;

/**
 * Created by giovanny on 15/04/16.
 */
public class FragmentViewAdapter extends FragmentPagerAdapter {
    //private Context _context;
    public static int totalPage = 3;


    public FragmentViewAdapter(FragmentManager fm) {
        super(fm);
        //_context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
            case 0:
                f = new GridNFragment();
                break;
            case 1:
                f = new ListaNFragment();
                break;
            case 2:
                f = new ImageNFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return totalPage;
    }

}

