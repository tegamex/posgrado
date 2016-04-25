package com.smartcity.posgrado.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.smartcity.posgrado.Adaptadores.CustomAdapter;
import com.smartcity.posgrado.R;

/**
 * Created by giovanny on 22/04/16.
 */
public class GridNFragment extends Fragment {
    public static int [] prgmImages={R.drawable.dead,R.drawable.xmen,R.drawable.dead,R.drawable.logo_uni};
    public static String [] prgmNameList={"Deapool :3","X-men","Beca Stander","Beca Alianza"};

    GridView gv;
    public GridNFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n_becas, container, false);
        gv=(GridView)rootView.findViewById(R.id.gridView);
        gv.setAdapter(new CustomAdapter(getActivity(), prgmNameList,prgmImages,R.layout.element_grid,R.id.imagenGrid,R.id.textEGrid));
        return rootView;
    }
}
