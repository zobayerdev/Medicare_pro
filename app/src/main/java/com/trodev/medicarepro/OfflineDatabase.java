package com.trodev.medicarepro;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineDatabase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*create offline data view mood*/
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

}
