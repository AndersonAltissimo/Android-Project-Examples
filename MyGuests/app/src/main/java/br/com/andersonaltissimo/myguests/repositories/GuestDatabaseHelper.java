package br.com.andersonaltissimo.myguests.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.andersonaltissimo.myguests.constants.DatabaseConstants;

public class GuestDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERION = 1;
    private static final String DATABASE_NAME = "MYGUEST.db";

    private static final String SQL_CREATE_TABLE_GUEST =
            "create table " + DatabaseConstants.GUEST.TABLE_NAME + " ("
            + DatabaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
            + DatabaseConstants.GUEST.COLUMNS.NAME + " string, "
            + DatabaseConstants.GUEST.COLUMNS.PRESENCE + " integer);";

    private static final String DROP_TABLE_GUEST = "DROP TABLE IF EXISTS "
            + DatabaseConstants.GUEST.TABLE_NAME;


    public GuestDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_GUEST);
        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }
}
