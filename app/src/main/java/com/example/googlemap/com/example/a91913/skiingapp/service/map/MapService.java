package com.example.googlemap.com.example.a91913.skiingapp.service.map;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public interface MapService {

    public Boolean isMapReady();
    public Boolean addMarker(double lat, double lng, String title);
    public void getLastLocation(OnSuccessListener listener, OnFailureListener failureListener);
    public void startLocationUpdates(LocationCallback locationCallback);
    public void stopLocationUpdates(LocationCallback locationCallback);

}
