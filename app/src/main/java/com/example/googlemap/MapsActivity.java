package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.googlemap.com.example.a91913.skiingapp.service.map.MapService;
import com.example.googlemap.com.example.a91913.skiingapp.service.map.impl.MapServiceImpl;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity {

    private MapService mapService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // init map
        // if in fragment, SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapService = new MapServiceImpl(mapFragment, 3000, 3000, LocationRequest.PRIORITY_HIGH_ACCURACY);

        // wait for map to be ready
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // add marker
                mapService.addMarker(-34, 151, "My Location", MapService.ZOOM_LEVEL_STREET);

                // get current location
                mapService.getLastLocation(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        Log.e("tag", "getCurrentLocation success");
                        if (location != null) {
                            // Logic to handle location object
                            mapService.addMarker(location.getLatitude(), location.getLongitude(), "My Location", MapService.ZOOM_LEVEL_STREET);
                        }
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("tag", "failure");
                        Log.e("tag", e.getMessage());
                    }
                });

                // start location update
                mapService.startLocationUpdates(new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        Log.e("tag", "startLocationUpdates result");
                        if (locationResult == null) {
                            return;
                        }
                        for (Location location : locationResult.getLocations()) {
                            // Update UI with location data
                            mapService.addMarker(location.getLatitude(), location.getLongitude(), "My Location", MapService.ZOOM_LEVEL_STREET);
                            location.getAltitude();
                            location.getSpeed();
                            location.distanceTo(location); // pass starting location into it
                        }
                    }

                    ;
                });

            }
        }, 2000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapService.stopLocationUpdates(new LocationCallback() {
        });
    }


}
