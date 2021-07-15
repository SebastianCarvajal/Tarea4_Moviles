package com.example.mapasdegoogle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener{

    GoogleMap mapa;
    int tipoVista;
    LatLng[] puntos = new LatLng[4];
    int cpuntos;

    double[] latitudes = new double[5];
    double[] longitudes = new double[5];
    String[] facultades = new String[5];
    String[] _facultades = new String[5];
    String[] decano = new String[5];
    String[] logo = new String[5];
    private View popup = null;

    //Nombre de la Facultad, Decano, Ubicación, Logo.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tipoVista=1;
        cpuntos=0;

        latitudes[0] = -1.012599;
        Toast.makeText(MainActivity.this, String.valueOf(latitudes[0]), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;

        mapa.getUiSettings().setZoomControlsEnabled(true);

        mapa.setOnMapClickListener(this);

        posicionarMapa();
        agregarPuntos();


    }

    public void CambiarTipoMapa(View v){
        mapa.setMapType(tipoVista);
        tipoVista = tipoVista<4?tipoVista+1:1;
    }

    public void posicionarMapa(){
        LatLng uteq = new LatLng(-1.0128684338088096, -79.46930575553893);

        CameraPosition camPos = new CameraPosition.Builder()
                .target(uteq)
                .zoom(17)
                .bearing(0)      //noreste arriba
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
    }

    public void agregarPuntos(){
        LatLng latLng = new LatLng(-1.012906, -79.469415);
        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Enfermeria")
                .snippet("No definido\n" + "Latitud: " + latLng.latitude + "\n" + "Longitud: " + latLng.longitude));
        latLng = new LatLng(-1.012599, -79.470603);
        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Facultad de Ciencias de la Ingenieria")
                .snippet("Ing. Washington Chiriboga, M.Sc.\n" + "Latitud: " + latLng.latitude + "\n" + "Longitud: " + latLng.longitude));

        latLng = new LatLng(-1.012665, -79.471100);
        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Facultad de Ciencias Ambientales")
                .snippet("Ing. Jose Elias Cuasquer\n" + "Latitud: " + latLng.latitude + "\n" + "Longitud: " + latLng.longitude));

        latLng = new LatLng(-1.012158, -79.470125);
        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Facultad de Ciencias Empresariales")
                .snippet("Ing. Mariela Andrade, PhD.\n" + "Latitud: " + latLng.latitude + "\n" + "Longitud: " + latLng.longitude));

        latLng = new LatLng(-1.081423, -79.502991);
        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Facultad de Ciencias Agropecuarias")
                .snippet("Ing. Gozalo Matute Leon\n" + "Latitud: " + latLng.latitude + "\n" + "Longitud: " + latLng.longitude));


        mapa.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){
            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                return null;
            }

            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                if(popup == null){
                    popup = getLayoutInflater().inflate(R.layout.popupmaps, null);
                }


                ImageView imagen = (ImageView) popup.findViewById(R.id.imagen);
                TextView titulo = (TextView) popup.findViewById(R.id.title);
                TextView snipped = (TextView) popup.findViewById(R.id.snippet);
                if(marker.getId().equals("m0")){
                    imagen.setImageResource(R.drawable.enfer);
                }else if(marker.getId().equals("m1")){
                    imagen.setImageResource(R.drawable.fci);
                }
                else if(marker.getId().equals("m2")){
                    imagen.setImageResource(R.drawable.fca);
                }
                else if(marker.getId().equals("m3")){
                    imagen.setImageResource(R.drawable.fce);
                }else{
                    imagen.setImageResource(R.drawable.agro);
                }
                titulo.setText(marker.getTitle());
                snipped.setText(marker.getSnippet());

                return popup;
            }
        });
        mapa.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
}