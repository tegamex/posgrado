package com.smartcity.posgrado.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class CustomAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    int layoutView;
    int imageEle;
    int tituloEle;

    public CustomAdapter(Context mainActivity, String[] prgmNameList, int[] prgmImages, int viewE, int imageEle,int tituloEle) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutView = viewE;
        this.imageEle=imageEle;
        this.tituloEle=tituloEle;
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (null == convertView) {
            rowView = inflater.inflate(
                    layoutView,
                    parent,
                    false);
        }
        ((TextView) rowView.findViewById(tituloEle)).setText(result[position]);
        ((ImageView) rowView.findViewById(imageEle)).setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
