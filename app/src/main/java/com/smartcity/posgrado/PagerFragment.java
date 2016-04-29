package com.smartcity.posgrado;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartcity.posgrado.Adaptadores.FragmentViewAdapter;

public class PagerFragment extends NewFragment {
    private ViewPager _mViewPager;
    private FragmentViewAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("mensaje","se crea pager 1");
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Log.i("mensaje","se crea pager 2");
        setUpView(v);

        Log.i("mensaje","se crea pager 3");
        setTab();

        Log.i("mensaje","se crea pager 4");

        Log.i("mensaje","se crea pager 5");
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i("mensaje","se crea view de pager");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setUpView(View v) {
        _mViewPager = (ViewPager) v.findViewById(R.id.imageviewPager);
        _adapter = new FragmentViewAdapter(getChildFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
    }
    private void setTab() {
        _mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int position) { }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {  }
            @Override
            public void onPageSelected(int position) {
            }
        });
    }
}
