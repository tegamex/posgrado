package com.smartcity.posgrado;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kevin on 19/04/16.
 */
public class FacultadesListFragment extends NewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.facultades_list_fragment, container, false);

        ListView listaEdificios = (ListView) v.findViewById(R.id.lista_edificios);
        final ArrayAdapter adaptador = new BuildingArrayAdapter(v.getContext(), Arrays.asList(getResources().getStringArray(getResources().getIdentifier("facultades", "array", getActivity().getPackageName()))));
        listaEdificios.setAdapter(adaptador);
        listaEdificios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)adaptador.getItem(position);
                Bundle arguments = new Bundle();
                arguments.putString("layout", item);
                Fragment fragment = new FacultadesDetailFragment();
                fragment.setArguments(arguments);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                        .replace(R.id.principal, fragment).addToBackStack(null).commit();
            }
        });
        return v;
    }

    public class BuildingArrayAdapter<T> extends ArrayAdapter<T>{
        public BuildingArrayAdapter(Context context, List<T> objects){
            super(context, 0, objects);
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View listItemView = convertView;
            if (null == convertView) {
                listItemView = inflater.inflate(
                        R.layout.vista_facultad,
                        parent,
                        false);
            }
            //Obteniendo instancias de los elementos
            TextView titulo = (TextView)listItemView.findViewById(R.id.nombre);
            ImageView icono = (ImageView)listItemView.findViewById(R.id.icono);
            String item = (String) getItem(position);
            titulo.setText(getResources().getString(getResources().getIdentifier("nombre_facultad_" + item, "string", getActivity().getPackageName())));
            icono.setImageResource(getResources().getIdentifier(item, "drawable", getActivity().getPackageName()));
            return listItemView;
        }
    }
}
