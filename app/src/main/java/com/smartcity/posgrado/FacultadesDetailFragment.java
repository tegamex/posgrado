package com.smartcity.posgrado;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class FacultadesDetailFragment extends NewFragment {
    String facu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.facultades_detail_fragment, container, false);
        facu=getArguments().getString("layout");
        CollapsingToolbarLayout collapser = (CollapsingToolbarLayout)v.findViewById(R.id.collapser);
        collapser.setTitle(facu.toUpperCase());
        loadImageParallax(v, getResources().getIdentifier(facu, "drawable", getActivity().getPackageName()));
        ((TextView) v.findViewById(R.id.nombre_facultad)).setText(getResources().getString(getResources().getIdentifier("nombre_facultad_" + facu, "string", getActivity().getPackageName())));
        ((TextView) v.findViewById(R.id.info_facultad)).setText(getResources().getString(getResources().getIdentifier("info_facultad_" + facu, "string", getActivity().getPackageName())));
        EscuelaArrayAdapter adapter=new EscuelaArrayAdapter(v.getContext(), Arrays.asList(getResources().getStringArray(getResources().getIdentifier("escuela_facultad_" + facu, "array", getActivity().getPackageName()))));
        ListView lv=((ListView) v.findViewById(R.id.lista_escuelas));
        lv.setAdapter(adapter);
        //setListViewHeightBasedOnChildren(lv);
        v.findViewById(R.id.irfacu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(getResources().getIdentifier("pagina_facultad_" + facu, "string", getActivity().getPackageName()))));
                startActivity(intent);
            }
        });
        return v;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RadioGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    private void loadImageParallax(View v,int id){
        ImageView image = (ImageView)v.findViewById(R.id.image_paralax);
        image.setImageResource(id);
    }

    class EscuelaArrayAdapter<T> extends ArrayAdapter<T> {

        public EscuelaArrayAdapter(Context context, List<T> objects){
            super(context, 0, objects);
        }

        public View getView(final int position, final View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View listItemView = convertView;
            if (null == convertView) {
                listItemView = inflater.inflate(
                        R.layout.vista_escuela,
                        parent,
                        false);
            }
            //Obteniendo instancias de los elementos
            TextView titulo = (TextView)listItemView.findViewById(R.id.titulo_escuela);
            ImageView icono = (ImageView)listItemView.findViewById(R.id.imagen_escuela);
            String item = (String) getItem(position);
            titulo.setText(getResources().getString(getResources().getIdentifier("nombre_escuela_" + item, "string", getActivity().getPackageName())));
            //icono.setImageResource(fragmento.getResources().getIdentifier(item,"drawable",fragmento.getActivity().getPackageName()));
            return listItemView;
        }
    }

}

