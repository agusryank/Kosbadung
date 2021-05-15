package com.example.kosbadung;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    String latitude, longtitude, kost_name;
    LatLng location_device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        latitude = getIntent().getStringExtra("latitude");
        longtitude = getIntent().getStringExtra("longtitude");
        kost_name = getIntent().getStringExtra("kost_name");

    }
    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Log.d("HOME", "OnMapReady Triger");
        mMap = googleMap;
        LatLng kost_location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longtitude));
        mMap.addMarker(new MarkerOptions().position(kost_location).title(kost_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kost_location));
        boundsLocation(kost_location);
    }
    private void boundsLocation(LatLng latlng){
        if(!(location_device == null)){
            LatLngBounds latLngBounds = new LatLngBounds(latlng, latlng);
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 25));
        }else {
            if(!isFinishing()){
                Log.e(TAG, "boundsLocation: lokasi tidak ditemukan");
            }
        }
    }
}