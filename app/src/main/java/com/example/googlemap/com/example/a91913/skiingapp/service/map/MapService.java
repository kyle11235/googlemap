package com.example.googlemap.com.example.a91913.skiingapp.service.map;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public interface MapService {

    public static final int ZOOM_LEVEL_STREET = 15;
    public static final int ZOOM_LEVEL_BLOCK = 20;

    public Boolean isMapReady();
    public void getLastLocation(OnSuccessListener listener, OnFailureListener failureListener);
    public void startLocationUpdates(LocationCallback locationCallback);
    public void stopLocationUpdates(LocationCallback locationCallback);
    public Boolean addMarker(double lat, double lng, String title, int zoomLevel);
    public void zoomTo(int level);

}
