package com.example.googlemap.com.example.a91913.skiingapp.service.map.impl;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.googlemap.com.example.a91913.skiingapp.service.map.MapService;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapServiceImpl implements OnMapReadyCallback, MapService {

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private Boolean isMapReady = false;

    // prepare client
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private LocationRequest locationRequest;

    // client
    private FusedLocationProviderClient fusedLocationClient;


    public MapServiceImpl(SupportMapFragment mapFragment, int interval, int fastestInterval, int accuracy) {

        // getMapAsync(this) -> onMapReady
        this.mapFragment = mapFragment;
        this.mapFragment.getMapAsync(this);

        // prepare client
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        // LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest = this.createLocationRequest(interval, fastestInterval, accuracy);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this.mapFragment.getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        final SupportMapFragment finalMapFragment = this.mapFragment;
        task.addOnSuccessListener(finalMapFragment.getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
            }
        });

        task.addOnFailureListener(finalMapFragment.getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(finalMapFragment.getActivity(),
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

        // client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.mapFragment.getActivity());

    }

    private LocationRequest createLocationRequest(long interval, long fastestInterval, int priority) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(fastestInterval);
        locationRequest.setPriority(priority);
        return locationRequest;
    }

    @Override
    public Boolean isMapReady() {
        return isMapReady;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        isMapReady = true;
        Log.e("tag", "isMapReady=true");
    }

    @Override
    public void getLastLocation(OnSuccessListener successListener, OnFailureListener failureListener) {
        fusedLocationClient.getLastLocation().addOnSuccessListener(mapFragment.getActivity(), successListener);
        fusedLocationClient.getLastLocation().addOnFailureListener(mapFragment.getActivity(), failureListener);
    }

    @Override
    public void startLocationUpdates(LocationCallback locationCallback) {
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    @Override
    public void stopLocationUpdates(LocationCallback locationCallback) {
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public Boolean addMarker(double lat, double lng, String title) {
        Log.e("tag", "addMarker");
        if (!isMapReady) {
            return false;
        }
        LatLng sydney = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(sydney).title(title));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        return true;
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(mapFragment.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mapFragment.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(mapFragment.getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }


}
