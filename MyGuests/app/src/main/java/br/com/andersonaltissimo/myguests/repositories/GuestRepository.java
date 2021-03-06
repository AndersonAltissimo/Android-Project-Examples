package br.com.andersonaltissimo.myguests.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.andersonaltissimo.myguests.constants.DatabaseConstants;
import br.com.andersonaltissimo.myguests.constants.GuestConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.entities.GuestCount;

public class GuestRepository {
    private static GuestRepository INSTANCE;
    private GuestDatabaseHelper guestDatabaseHelper;

    private GuestRepository(Context context) {
        this.guestDatabaseHelper = new GuestDatabaseHelper(context);
    }

    public static synchronized GuestRepository getInstance(Context context) {
        if (INSTANCE == null) {
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
        } catch (Exception e) {
            return false;
        }
    }

    public List<Guest> getGuestByQuery(String query) {
        List<Guest> lst = new ArrayList<>();

        try {
            SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Guest guest = new Guest();
                    guest.setId(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID)));
                    guest.setName(cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME)));
                    guest.setConfirmed(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)));

                    lst.add(guest);
                }
            }

            if (cursor != null) {
                cursor.close();
            }

        } catch (Exception e) {
            return lst;
        }

        return lst;
    }

    public Guest load(int id) {
        Guest guest = new Guest();

        try {
            SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getReadableDatabase();

            String[] projection = {
                    DatabaseConstants.GUEST.COLUMNS.ID,
                    DatabaseConstants.GUEST.COLUMNS.NAME,
                    DatabaseConstants.GUEST.COLUMNS.PRESENCE
            };

            String selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?";

            String[] selectionArgs = {
                    String.valueOf(id)
            };

            Cursor cursor = sqLiteDatabase.query(DatabaseConstants.GUEST.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                guest.setId(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID)));
                guest.setName(cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME)));
                guest.setConfirmed(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)));
            }

            if (cursor != null) {
                cursor.close();
            }

            return guest;

        } catch (Exception e) {
            return guest;
        }
    }

    public boolean update(Guest guest) {
        try {
            SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.getName());
            values.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmed());

            String selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?";

            String[] selectionArgs = {
                    String.valueOf(guest.getId())
            };

            sqLiteDatabase.update(DatabaseConstants.GUEST.TABLE_NAME, values, selection, selectionArgs);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(int id) {
        try {

            SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getWritableDatabase();

            String whereClause = DatabaseConstants.GUEST.COLUMNS.ID + " = ?";

            String[] whereArgs = {
                    String.valueOf(id)
            };

            sqLiteDatabase.delete(DatabaseConstants.GUEST.TABLE_NAME, whereClause, whereArgs);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public GuestCount loadDashboard() {

        GuestCount guestCount = new GuestCount(0, 0, 0);

        try {

            guestCount.setPresentCount(this.countGuestInformation(GuestConstants.CONFIRMATION.PRESENT));
            guestCount.setAbsentCount(this.countGuestInformation(GuestConstants.CONFIRMATION.ABSENT));
            guestCount.setAllInvitedCount(this.countGuestInformationTotal());

        } catch (Exception e) {
            return guestCount;
        }
        return guestCount;
    }

    private int countGuestInformationTotal() {
        SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getReadableDatabase();

        String query = "select count(*) from " + DatabaseConstants.GUEST.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return cursor.getInt(0);
        }
        return 0;
    }

    private int countGuestInformation(int tipo) {

        SQLiteDatabase sqLiteDatabase = this.guestDatabaseHelper.getReadableDatabase();

        String query = "select count(*) from " + DatabaseConstants.GUEST.TABLE_NAME +
                " where " + DatabaseConstants.GUEST.COLUMNS.PRESENCE + " = " + tipo;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return cursor.getInt(0);
        }

        return 0;
    }
}
