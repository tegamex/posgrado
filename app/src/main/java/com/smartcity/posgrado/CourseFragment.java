package com.smartcity.posgrado;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jbot on 24/04/16.
 */
public class CourseFragment extends NewFragment {
    String nombre;
    int tipoposgrado;
    TextView nombreCap;
    public CourseFragment(int tipo){
        this.tipoposgrado = tipo;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        nombreCap = (TextView) v.findViewById(R.id.nombreTab);
        if(tipoposgrado == 1) nombre = "Programas de Maestria";
        else if(tipoposgrado==2) nombre = "Programas de Doctorado";
        else if (tipoposgrado ==3) nombre = "Cursos de Diplomado";
        else nombre = "2da Especializacion";
        nombreCap.setText(nombre);
        return v;
    }
}