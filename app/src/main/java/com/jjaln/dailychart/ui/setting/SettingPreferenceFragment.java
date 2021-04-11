package com.jjaln.dailychart.ui.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.view.View;
import android.widget.BaseAdapter;

import com.jjaln.dailychart.R;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences prefs;



    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        prefs.registerOnSharedPreferenceChangeListener(prefListener);

        Preference prf = getPreferenceScreen();
        


    }// onCreate

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
        }
    };

}