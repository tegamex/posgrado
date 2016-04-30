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

    public int [] prgmImages={R.drawable.investigadores,R.drawable.hackathon,R.drawable.rector,R.drawable.starup,
            R.drawable.ppk,R.drawable.castaneda};
    public String [] prgmNameList={"Investigadores de la UNI crean aplicación para monitorear TBC","1º Hackathon por la Inclusion Educativa",
            "ASOCIACIÓN DE UNIVERSIDADES PÚBLICAS DEL PERÚ - ANUPP","Grupo de la UNI es reconocida por su propuesta en el Star Up de San Isidro",
            "PPK gana elecciones presidenciales en Peru-Nebraska","Bolsa de cemento reemplaza Castañeda en Concejo Metropolitano y nadie se da cuenta",
            };
    public String[] descrip={
            "Investigadores de la Facultad de Ciencias - UNI  crean aplicación para monitorear efectividad del tratamiento para pacientes que padecen TBC.",
            "Concluyo la primera Hackathon realiza en las Instalaciones de CTIC donde jovenes de la UNI desarrolaron prototipos ganadores",
            "Ayer fue un día histórico que ha permitido consolidar la Asociación Nacional de Universidades Públicas del Perú, la misma que se convertirá en un interlocutor válido entre el estado y la sociedad, asimismo, esta entidad defenderá los intereses de la universidad pública y sus integrantes.",
            "#UNI #StartupUNI #CTICUNI #Emprendedor, si tienes una idea innovadora y estás interesado en participar, inscribe tu plan de negocio. Planifica la fecha de entrega de tu proyecto para que lo envíes a tiempo y no te pierdas la oportunidad de alcanzar tu crecimiento empresarial. ",
            "La National Office of Electoral Processes (NOEP) informó esta tarde que con el 99,0% de las preferencias electorales el candidato presidencial Pedro Pablo Kuczynski se erigiría Presidente Constitucional del Territorio de Ultramar Peru-Nebraska (The Peruvian).",
            "Esta es la verdadera razón por la que no se escuchan declaraciones del alcalde a pesar de la situaciones que vive la ciudad. "


    };
    ListView lv;
    public ListaNFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n_lista, container, false);
        lv=(ListView)rootView.findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(getActivity(), prgmNameList,prgmImages,descrip,R.layout.element_list,R.id.imagenLista,R.id.textEList,R.id.tContenido));
        return rootView;
    }
}
