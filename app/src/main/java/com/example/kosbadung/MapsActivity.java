package com.example.kosbadung;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.kosbadung.Util.GetLocation;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
//    String latitude, longtitude;
    public static final int PERMISSIONS_REQUEST = 1;
    LatLng location_device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        latitude = getIntent().getStringExtra("latitude");
//        longtitude = getIntent().getStringExtra("longtitude");
        permissionCheck();

    }
    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Log.d("HOME", "OnMapReady Triger");
        mMap = googleMap;
        if (mMap != null) {
            mMap.setMaxZoomPreference(18);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(1000);
            locationRequest.setFastestInterval(5000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            SettingsClient settingsClient = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
            task.addOnSuccessListener(this, locationSettingsResponse -> {
                new GetLocation(MapsActivity.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));
                if (location_device != null) {
                    boundsLocation(30);
                } else {
                    if(!isFinishing()){
                        Log.e(TAG, "onMapReady: lokasi tidak diemukan");
                    }
                }
            });
            task.addOnFailureListener(this, e -> {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        Log.d("HOME", "ERROR onMapReady");
                        e1.printStackTrace();
                    }
                }
            });
        }
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longtitude));
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Lokasi Kost"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        LatLng bali = new LatLng(-8.8007748,115.1749558);
//        mMap.addMarker(new MarkerOptions().position(bali).title("Lokasi Kost"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(bali));
    }
    private void boundsLocation(int zoom){
        new GetLocation(MapsActivity.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));
        if(!(location_device == null)){
            LatLngBounds latLngBounds = new LatLngBounds(location_device, location_device);
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, zoom));
        }else {
            if(!isFinishing()){
                Log.e(TAG, "boundsLocation: lokasi tidak ditemukan");
            }
        }
    }
    private void permissionCheck() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm != null) {
            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d("HOME", "Permission needed");
            }
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                Log.d("HOME", "permission ok");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
            }
            new GetLocation(MapsActivity.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));

            FragmentManager myFM = getSupportFragmentManager();
            final SupportMapFragment mapFragment = (SupportMapFragment) myFM.findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(MapsActivity.this);
            } else {
                Log.d("HOME", "mapFragment null");
            }
        }
    }
}