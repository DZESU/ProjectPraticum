package com.personal.poryto.derro.Home.Repositry;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.personal.poryto.derro.Base.Repositery;
import com.personal.poryto.derro.Home.Model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pory Sovann on 9/22/2018.
 */
public class PlaceRepositery implements Repositery{
    private final static String TAG = "pory";

    DatabaseReference rootReference;
    DatabaseReference placeReference;
    DatabaseReference idReference;
    StorageReference storageReference;

    public PlaceRepositery(){
        storageReference = FirebaseStorage.getInstance().getReference();
        rootReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public LiveData get(int id) {
        return null;
    }

    @Override
    public LiveData<List<Place>> getAll(){
        MutableLiveData<List<Place>> listPlaces = new MutableLiveData<>();
        listPlaces.setValue(new ArrayList<>());
        placeReference = rootReference.child("palaces");
        Query query = placeReference.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    Log.d(TAG, "onDataChange: "+dataSnapshot.toString());
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Place place = issue.getValue(Place.class);
                        listPlaces.getValue().add(place);
                        // do something with the individual "issues"
                        Log.d(TAG, "list: "+listPlaces.getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return listPlaces;
    }

}
