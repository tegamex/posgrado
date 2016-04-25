package com.smartcity.posgrado.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smartcity.posgrado.Adaptadores.CustomAdapter;
import com.smartcity.posgrado.R;

/**
 * Created by giovanny on 15/04/16.
 */
public class ListaNFragment extends Fragment {

    public static int [] prgmImages={R.drawable.dead,R.drawable.logo_uni,R.drawable.dead,R.drawable.logo_uni};
    public static String [] prgmNameList={"Let Us C","c++","JAVA","Python"};
    ListView lv;
    public ListaNFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n_lista, container, false);
        lv=(ListView)rootView.findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(getActivity(), prgmNameList,prgmImages,R.layout.element_list,R.id.imagenLista,R.id.textEList));
        return rootView;
    }
}
