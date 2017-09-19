package br.com.andersonaltissimo.myguests.repositories;

import android.content.Context;

public class GuestRepository {
    private static GuestRepository INSTANCE;
    private GuestDatabaseHelper guestDatabaseHelper;

    private GuestRepository(Context context){
        this.guestDatabaseHelper = new GuestDatabaseHelper(context);
    }

    public static synchronized GuestRepository getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new GuestRepository(context);
        }
        return INSTANCE;
    }
}
