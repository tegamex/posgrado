package com.smartcity.posgrado.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smartcity.posgrado.Adaptadores.CustomAdapter;
import com.smartcity.posgrado.Eventos.EventoAdapter;
import com.smartcity.posgrado.Eventos.NEvento;
import com.smartcity.posgrado.R;

import java.util.ArrayList;

/**
 * Created by giovanny on 30/04/16.
 */
public class EventoNFragment extends Fragment{

    ArrayList<Object> evento = new ArrayList<>();

    ListView lv;
    public EventoNFragment() {
        evento.add("Hoy");
        evento.add(new NEvento(R.drawable.qhatuni,"QhatUNI","Mi, May 12 at 12:00 PM","Explanada Biblioteca Central"));
        evento.add("Esta Semana");
        evento.add(new NEvento(R.drawable.fiee,"IEEE Capitulo Computer Science","Vi, May 18 at 8:00 PM","Auditorio Facultad de Ingeniería Eléctrica, Electrónica y Telecomunicaciones"));
        evento.add(new NEvento(R.drawable.fc,"Cienkana","Sa, May 20 at 12:00 PM","Losita Ciencias"));

//Vi, May 18 at 8:00 PM
        evento.add("Proximamente");
        evento.add(new NEvento(R.drawable.figmm,"Tono Minero","Lu, Jun 15 at 13:00 PM","Jaula Civiles"));
        evento.add(new NEvento(R.drawable.estadio,"Final Campeonato Estudiantes UNI","Vi, Jun 20 at 4:00 PM","Estadio de la Universidad Nacional de Ingenieria"));
        evento.add(new NEvento(R.drawable.figmm,"Tonozo FIQT ","Lu, Jul 05 at 13:00 PM","Explanada de la facultad de FIQT"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n_evento, container, false);
        lv=(ListView)rootView.findViewById(R.id.elisview);
        lv.setAdapter(new EventoAdapter(getActivity(),evento));

        return rootView;
    }
}
