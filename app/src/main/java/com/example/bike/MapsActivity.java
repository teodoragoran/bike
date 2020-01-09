package com.example.bike;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<IncidentBean> incidents;

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        client = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        boolean permissionGranted = ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(permissionGranted) {
           Log.d("permission", "yes");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }

        AsyncTask fetchIncidents = new FetchIncidents().execute();
        String incidentsString = "";
        try {
            incidentsString = fetchIncidents.get().toString();
            JSONParser parser = new JSONParser((incidentsString));
            incidents =  parser.getIncidentsList();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), IncidentActivity.class);
                startActivity(intent);
            }
        });

        client.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng currentPosition = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(currentPosition).title("HOME")).showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
                CameraPosition cameraHome = new CameraPosition.Builder()
                        .target(currentPosition)
                        .zoom(17)
                        .build();

                mMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraHome));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    client.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            LatLng currentPosition = new LatLng(location.getLatitude(),location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(currentPosition).title("HOME"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
                        }
                    });
                }
            }
        }
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < incidents.size(); i++) {
            IncidentBean incident = incidents.get(i);
            LatLng incidentPosition = new LatLng(incident.getLatitude(),incident.getLongitude());
            mMap.addMarker(new MarkerOptions().position(incidentPosition).title(incident.getType())).showInfoWindow();
        }

    }
}
