package com.smartcity.posgrado.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartcity.posgrado.R;

/**
 * Created by giovanny on 15/04/16.
 */
public class ImageNFragment extends Fragment {

    public ImageNFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_n_image, container, false);
    }
}
