package com.smartcity.posgrado.Eventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcity.posgrado.R;

import java.util.ArrayList;

/**
 * Created by giovanny on 30/04/16.
 */
public class EventoAdapter extends BaseAdapter {
    private ArrayList<Object> eventoArray;
    private LayoutInflater inflater;
    private static final int TYPE_evento = 0;
    private static final int TYPE_DIVIDER = 1;
    Context context;

    public EventoAdapter(Context context, ArrayList<Object> personArray) {
        this.context=context;
        this.eventoArray = personArray;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventoArray.size();
    }

    @Override
    public Object getItem(int position) {
        return eventoArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        // TYPE_PERSON and TYPE_DIVIDER
        return 2;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == TYPE_evento);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof NEvento) {
            return TYPE_evento;
        }

        return TYPE_DIVIDER;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_evento:
                    convertView = inflater.inflate(R.layout.element_evento, parent, false);
                    break;
                case TYPE_DIVIDER:
                    convertView = inflater.inflate(R.layout.row_header, parent, false);
                    break;
            }
        }

        switch (type) {
            case TYPE_evento:
                NEvento evento = (NEvento)getItem(position);
                TextView etitulo = (TextView)convertView.findViewById(R.id.teTitulo);
                TextView etfehora = (TextView)convertView.findViewById(R.id.teFeHora);
                TextView etlugar = (TextView)convertView.findViewById(R.id.teLugar);
                ImageView eimagen = (ImageView)convertView.findViewById(R.id.ieImagen);
                etitulo.setText(evento.getTitulo());
                etfehora.setText(evento.getFeHora());
                etlugar.setText(evento.getLugar());
                eimagen.setImageResource(evento.getImagen());
                break;
            case TYPE_DIVIDER:
                TextView title = (TextView)convertView.findViewById(R.id.headerTitle);
                String titleString = (String)getItem(position);
                title.setText(titleString);
                break;
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                NEvento evento = (NEvento)getItem(position);
                Toast.makeText(context, "You Clicked " + evento.getTitulo(), Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

}
