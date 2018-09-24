package com.personal.poryto.derro.Home.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.personal.poryto.derro.Home.Adapter.BrandSpinnerAdapter;
import com.personal.poryto.derro.Home.Viewmodel.MainViewModel;
import com.personal.poryto.derro.R;
import com.personal.poryto.derro.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String TAG = "pory";

    List<Integer> logolist = new ArrayList<>();
    ActivityMainBinding binding;
    GoogleMap googleMap;
    MapFragment mapFragment;
    MainViewModel viewModel;
    List<MarkerOptions> markerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        markerList = new ArrayList<>();
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        logolist.add(R.drawable.pipay_logo);
        logolist.add(R.drawable.smartluy_logo);
        logolist.add(R.drawable.wing_logo);

        binding.appCompatSpinner.setAdapter(new BrandSpinnerAdapter(this, R.layout.brand_logo_spinner_viewholder, logolist));
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.toString());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    private void MoveCamera(LatLng latLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 23));
    }

    private void AddMarkerOnMap(LatLng latLng, @Nullable String title){
        markerList.add(new MarkerOptions().position(latLng).title(title));
        this.googleMap.addMarker(markerList.get(markerList.size()-1));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        MoveCamera(new LatLng(11.555976,104.905498));
        googleMap.setOnMapClickListener(point -> {
            Log.d(TAG, "onMapReady: "+point.longitude+" "+point.latitude+ "     point = "+point.toString());
            AddMarkerOnMap(point,"hello");
        });
        viewModel.getSnapShotLiveData().observe(this, dataSnapshot -> {
            for (DataSnapshot i : dataSnapshot.getChildren()) {
                com.personal.poryto.derro.Home.Model.Place place = i.getValue(com.personal.poryto.derro.Home.Model.Place.class);
                Log.d(TAG, "onMapReady: " + i.toString());
                Log.d(TAG, "onMapReady: " + place.toString());
                AddMarkerOnMap(new LatLng(place.getLatitude(), place.getLongitude()),place.getName());
            }
        });
//        viewModel.getListPlaces().observe(this, places -> {
//            for (com.personal.poryto.derro.Home.Model.Place i : places) {
//                com.personal.poryto.derro.Home.Model.Place place = i;
//                Log.d(TAG, "onMapReady: " + i.toString());
//                Log.d(TAG, "onMapReady: " + place.toString());
//                AddMarkerOnMap(new LatLng(place.getLatitude(), place.getLongitude()),place.getName());
//            }
//        });

    }
}
