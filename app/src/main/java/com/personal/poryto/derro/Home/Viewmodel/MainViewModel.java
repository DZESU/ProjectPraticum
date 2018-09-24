package com.personal.poryto.derro.Home.Viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.personal.poryto.derro.Home.Model.Place;
import com.personal.poryto.derro.Home.Repositry.FirebaseQueryLiveData;
import com.personal.poryto.derro.Home.Repositry.PlaceRepositery;

import java.util.List;

/**
 * Created by Pory Sovann on 9/22/2018.
 */
public class MainViewModel extends ViewModel {
    private final static String TAG = "pory";

    private PlaceRepositery repositery;
    MutableLiveData<List<Place>> listPlaces;
    FirebaseQueryLiveData liveData;

    public MainViewModel() {
        liveData =  new FirebaseQueryLiveData();
        repositery = new PlaceRepositery();
        listPlaces = new MutableLiveData<>();
    }

    public LiveData<DataSnapshot> getSnapShotLiveData(){
        for (DataSnapshot i : liveData.getValue().getChildren()) {
            com.personal.poryto.derro.Home.Model.Place place = i.getValue(com.personal.poryto.derro.Home.Model.Place.class);
            Log.d(TAG, "onMapReady: " + i.toString());
            Log.d(TAG, "onMapReady: " + place.toString());
            listPlaces.getValue().add(place);
        }
        return liveData;
    }

    public LiveData<List<Place>> getListPlaces() {
//            Log.d(TAG, "onMapReady: " + liveData.getValue().toString());
//        for (DataSnapshot i : liveData.getValue().getChildren()) {
//            com.personal.poryto.derro.Home.Model.Place place = i.getValue(com.personal.poryto.derro.Home.Model.Place.class);
//            Log.d(TAG, "onMapReady: " + place.toString());
//            listPlaces.getValue().add(place);
//        }
        return listPlaces;
    }

    public void RequestPalaces(){
//       listPlaces = repositery.getAll();
//        for (DataSnapshot issue : li.getChildren()) {
//            Place place = issue.getValue(Place.class);
//            listPlaces.getValue().add(place);
//            // do something with the individual "issues"
//            Log.d(TAG, "list: "+listPlaces.getValue().toString());
//        }
    }


}
