package com.smartcity.posgrado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smartcity.posgrado.Adaptadores.FragmentViewAdapter;

public class PagerFragment extends NewFragment {
    private ViewPager _mViewPager;
    private FragmentViewAdapter _adapter;
    private ImageView _btn1, _btn2, _btn3;
    int x=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("mensaje","se crea pager");
        View v=null;
        if(x==0) {
            v = inflater.inflate(R.layout.fragment_main, container, false);
            setUpView(v);
            setTab();
            onCircleButtonClick();
        }
        else return super.onCreateView(inflater,container,savedInstanceState);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i("mensaje","se crea view de pager");
        super.onViewCreated(view, savedInstanceState);
    }
    private void onCircleButtonClick() {

        _btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btn1.setImageResource(R.drawable.uni_selected);
                _mViewPager.setCurrentItem(0);
            }
        });

        _btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btn2.setImageResource(R.drawable.uni_selected);
                _mViewPager.setCurrentItem(1);
            }
        });
        _btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btn3.setImageResource(R.drawable.uni_selected);
                _mViewPager.setCurrentItem(2);
            }
        });
    }


    private void setUpView(View v) {
        _mViewPager = (ViewPager) v.findViewById(R.id.imageviewPager);
        _adapter = new FragmentViewAdapter(getFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton(v);
    }
    private void setTab() {
        _mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int position) { }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {  }
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                _btn1.setImageResource(R.drawable.logo_uni);
                _btn2.setImageResource(R.drawable.logo_uni);
                _btn3.setImageResource(R.drawable.logo_uni);
                btnAction(position);
            }
        });
    }
    private void btnAction(int action) {
        switch (action) {
            case 0:
                _btn1.setImageResource(R.drawable.uni_selected);
                break;
            case 1:
                _btn2.setImageResource(R.drawable.uni_selected);
                break;
            case 2:
                _btn3.setImageResource(R.drawable.uni_selected);
                break;
        }
    }
    private void initButton(View v) {
        _btn1 = (ImageView) v.findViewById(R.id.btn1);
        _btn1.setImageResource(R.drawable.uni_selected);
        _btn2 = (ImageView) v.findViewById(R.id.btn2);
        _btn3 = (ImageView) v.findViewById(R.id.btn3);
    }
}
