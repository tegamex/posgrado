package com.smartcity.posgrado;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.smartcity.posgrado.Adaptadores.CustomAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapFragment extends NewFragment {

    private GoogleMap mMap;
    private Marker marker,miposicion;
    private LocationManager locationManager;
    private Polyline line;


    LocationListener locationListener;
    SupportMapFragment mMapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.i("mensaje", "se crea mapa");
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        ListView listaEdificios = (ListView) v.findViewById(R.id.lista_edificios);
        final ArrayAdapter adaptador = new BuildingArrayAdapter(v.getContext(), Arrays.asList(getResources().getStringArray(getResources().getIdentifier("facultades", "array", getActivity().getPackageName()))));
        listaEdificios.setAdapter(adaptador);
        listaEdificios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 4) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(-12.0159424, -77.0503581))
                            .zoom(17)
                            .bearing(90)
                            .tilt(30)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    marker = mMap.addMarker(new MarkerOptions().position(new LatLng(-12.0159424, -77.0503581)).title("uni").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                }
                else {
                    String item = (String)adaptador.getItem(position);
                    Bundle arguments = new Bundle();
                    arguments.putString("layout", item);
                    Fragment fragment = new FacultadesDetailFragment();
                    fragment.setArguments(arguments);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                            .replace(R.id.principal, fragment).addToBackStack(null).commit();
                }
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        setUpMapIfNeeded();
        super.onStart();
    }

    @Override
    public void onResume() {
        mMapFragment.onResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapFragment.onPause();
    }

    public void setUpMapIfNeeded(){
        if(mMapFragment==null) {
            mMapFragment = new SupportMapFragment() {
                @Override
                public void onActivityCreated(Bundle savedInstanceState) {
                    Log.i("mensaje","captando mapa");
                    super.onActivityCreated(savedInstanceState);
                    mMap = mMapFragment.getMap();
                    if (mMap != null) {
                        setUpMap();
                    }
                }

            };
            getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();
        }
    }



    private void setUpMap() {

        try{
            mMap.setMyLocationEnabled(true);
        }catch (SecurityException e){
        }
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }catch (SecurityException e){
        }
        CameraPosition cameraPosition= new CameraPosition.Builder()
                .target(new LatLng(0,0))
                .zoom(17)
                .bearing(90)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });
    }


    public void llegar(){
        if(miposicion!=null) {
            String urlTopass = makeURL(miposicion.getPosition().latitude,
                    miposicion.getPosition().longitude, marker.getPosition().latitude,
                    marker.getPosition().longitude);
            new connectAsyncTask(urlTopass).execute();
        }
        else{
            Toast.makeText(getContext(),"Aun no se encuentra su Posicion",Toast.LENGTH_LONG).show();
        }
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            try {
                miposicion.remove();
            }catch (Exception e){
                Log.d("Error","miposicion nulo");
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(loc.getLatitude(),loc.getLongitude()))
                        .zoom(17)
                        .bearing(90)
                        .tilt(30)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
            miposicion=mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude())).title("Yo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }


    public String makeURL(double sourcelat, double sourcelog, double destlat,
                          double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString.append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=walking&alternatives=true");
        return urlString.toString();
    }
    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        connectAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching route, Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null) {
                drawPath(result);
            }
        }
    }
    public class JSONParser {

        InputStream is = null;
        String json = "";

        public JSONParser() {
        }

        public String getJSONFromUrl(String url) {

            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                json = sb.toString();
                is.close();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            return json;

        }
    }

    public void drawPath(String result) {
        if (line != null) {
            mMap.clear();
            miposicion=mMap.addMarker(new MarkerOptions().position(miposicion.getPosition()).title("YO").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            marker = mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }
        try {
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes
                    .getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

            for (int z = 0; z < list.size() - 1; z++) {
                LatLng src = list.get(z);
                LatLng dest = list.get(z + 1);
                line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude),
                                new LatLng(dest.latitude, dest.longitude))
                        .width(5).color(Color.BLUE).geodesic(true));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
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
