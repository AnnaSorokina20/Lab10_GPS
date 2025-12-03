package com.example.lab10_gps;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        EditText editLat = findViewById(R.id.editLat);
        EditText editLng = findViewById(R.id.editLng);
        Button btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            String sLat = editLat.getText().toString();
            String sLng = editLng.getText().toString();

            if (sLat.isEmpty() || sLng.isEmpty()) {
                Toast.makeText(this, "Введіть координати!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double lat = Double.parseDouble(sLat);
                double lng = Double.parseDouble(sLng);

                LatLng position = new LatLng(lat, lng);

                map.clear();
                map.addMarker(new MarkerOptions().position(position).title("Обране місце"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 14f));

            } catch (Exception e) {
                Toast.makeText(this, "Некоректні координати!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
