package br.com.andersonaltissimo.myguests.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.andersonaltissimo.myguests.constants.DatabaseConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;

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

    public Boolean insert(Guest guest) {
        try {
            SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.getName());
            values.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmed());

            sqLiteDatabase.insert(DatabaseConstants.GUEST.TABLE_NAME, null, values);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
