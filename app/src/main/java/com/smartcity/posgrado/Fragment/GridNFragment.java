package com.smartcity.posgrado.Fragment;

import android.content.res.Configuration;
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
    public int [] prgmImages={R.drawable.faua,R.drawable.fc,R.drawable.fia,R.drawable.fic,R.drawable.fiee,R.drawable.fieecs,R.drawable.figmm,
            R.drawable.fiis,R.drawable.fim,R.drawable.fip,R.drawable.fiqt};
    public String [] prgmNameList={"Facultad de Arquitectura, Urbanismo y Artes","Facultad de Ciencias","Facultad de Ingeniería Ambiental",
            "Facultad de Ingeniería Civil","Facultad de Ingeniería Eléctrica y Electrónica","Facultad de Ingeniería Económica, Estadística y CCSS",
            "Facultad de Ingeniería Geológica, Minera y Metalúrgica","Facultad de Ingeniería Industrial y de Sistemas",
            "Facultad de Ingeniería Mecánica","Facultad de Ingeniería de Petróleo, Gas Natural y Petroquímica",
            "Facultad de Ingeniería Química y Textil"};

    GridView gv;
    public GridNFragment() {
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n_becas, container, false);
        gv=(GridView)rootView.findViewById(R.id.gridView);
        gv.setAdapter(new CustomAdapter(getActivity(), prgmNameList,prgmImages,new String[]{},R.layout.element_grid,R.id.imagenGrid,R.id.textEGrid,R.id.tContenido));
        return rootView;
    }
}
