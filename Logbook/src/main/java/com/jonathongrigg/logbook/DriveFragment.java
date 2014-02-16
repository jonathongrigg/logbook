package com.jonathongrigg.logbook;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class DriveFragment extends Fragment {

    public DriveFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drive, container, false);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.drive, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settings = new Intent(getActivity(), SettingsActivity.class);
                startActivity(settings);
                return true;
            case R.id.action_start:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new DriveFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
