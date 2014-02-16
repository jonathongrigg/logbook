package com.jonathongrigg.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new OverviewFragment())
                    .addToBackStack(null)
                    .commit();
        }

        EntryDatabaseHandler db = new EntryDatabaseHandler(this);
        /*db.tidyUpDatabase();
        Log.d("Insert: ", "Inserting ..");
        db.addEntry(new Entry(1, 100, 102, "Melissa", "Multi-lane, Highway", "Fine", "Medium"));
        db.addEntry(new Entry(2, 1000, 1020, "Andrew", "Multi-lane, Highway", "Fine", "Medium"));
        db.addEntry(new Entry(3, 600, 702, "Lee", "Multi-lane, Highway", "Fine", "Medium"));*/

        Log.d("Reading: ", "Reading all entries ..");
        List<Entry> entries = db.getAllEntries();
        for (Entry e : entries) {
            Log.d("Entry: ", "ID: " + String.valueOf(e.getId()) + " Start: " + String.valueOf(e.getStartTimeDate()) +
                    " End: " + String.valueOf(e.getEndTimeDate()) + " Supervisor: " + e.getSupervisor() + " Road: " + e.getRoadConditions() +
                    " Weather: " + e.getWeatherConditions() + " Traffic: " + e.getTrafficConditions());
        }
    }

}
