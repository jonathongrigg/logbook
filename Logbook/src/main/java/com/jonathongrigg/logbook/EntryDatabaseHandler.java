package com.jonathongrigg.logbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EntryDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "logbook";
    private static final String TABLE_ENTRIES = "entries";

    private static final String KEY_ID = "id";
    private static final String KEY_START_TIME_DATE = "start_time_date";
    private static final String KEY_END_TIME_DATE = "end_time_date";
    private static final String KEY_SUPERVISOR = "supervisor";
    private static final String KEY_ROAD_CONDITIONS = "road_conditions";
    private static final String KEY_WEATHER_CONDITIONS = "weather_conditions";
    private static final String KEY_TRAFFIC_CONDITIONS = "traffic_conditions";

    public EntryDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "(" + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_START_TIME_DATE + " TEXT," + KEY_END_TIME_DATE +
                " TEXT," + KEY_SUPERVISOR + " TEXT," + KEY_ROAD_CONDITIONS + " TEXT," +
                KEY_WEATHER_CONDITIONS + " TEXT," + KEY_TRAFFIC_CONDITIONS + " TEXT)";
        db.execSQL(CREATE_ENTRIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        onCreate(db);
    }

    public void tidyUpDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        onCreate(db);
    }

    public void addEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_START_TIME_DATE, entry.getStartTimeDate());
        values.put(KEY_END_TIME_DATE, entry.getEndTimeDate());
        values.put(KEY_SUPERVISOR, entry.getSupervisor());
        values.put(KEY_ROAD_CONDITIONS, entry.getRoadConditions());
        values.put(KEY_WEATHER_CONDITIONS, entry.getWeatherConditions());
        values.put(KEY_TRAFFIC_CONDITIONS, entry.getTrafficConditions());

        db.insert(TABLE_ENTRIES, null, values);
        db.close();
        Log.d("addEntry: ", "ID " + entry.getId() + " Supervisor " + entry.getSupervisor());
    }

    public Entry getEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ENTRIES, new String[] { KEY_ID, KEY_START_TIME_DATE,
                KEY_END_TIME_DATE, KEY_SUPERVISOR, KEY_ROAD_CONDITIONS, KEY_WEATHER_CONDITIONS,
                KEY_TRAFFIC_CONDITIONS }, KEY_ID + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Entry entry = new Entry(Integer.parseInt(cursor.getString(0)),
                Long.parseLong(cursor.getString(1)), Long.parseLong(cursor.getString(2)),
                cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6));

        return entry;
    }

    public List<Entry> getAllEntries() {
        List<Entry> entryList = new ArrayList<Entry>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ENTRIES, null);

        if (cursor.moveToFirst()) {
            do {
                Entry entry = new Entry(Integer.parseInt(cursor.getString(0)),
                        Long.parseLong(cursor.getString(1)), Long.parseLong(cursor.getString(2)),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6));
                entryList.add(entry);
                Log.d("getAllEntries: ", "ID " + entry.getId() + " Supervisor " + entry.getSupervisor());
            } while (cursor.moveToNext());
        }

        return entryList;
    }

    public int getEntriesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ENTRIES, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_START_TIME_DATE, entry.getStartTimeDate());
        values.put(KEY_END_TIME_DATE, entry.getEndTimeDate());
        values.put(KEY_SUPERVISOR, entry.getSupervisor());
        values.put(KEY_ROAD_CONDITIONS, entry.getRoadConditions());
        values.put(KEY_WEATHER_CONDITIONS, entry.getWeatherConditions());
        values.put(KEY_TRAFFIC_CONDITIONS, entry.getTrafficConditions());

        return db.update(TABLE_ENTRIES, values, KEY_ID + "=?", new String[] {
                String.valueOf(entry.getId()) });
    }

    public void deleteEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENTRIES, KEY_ID + "=?", new String[] { String.valueOf(entry.getId()) });
        db.close();
    }
}
