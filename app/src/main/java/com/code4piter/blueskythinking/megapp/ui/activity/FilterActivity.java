package com.code4piter.blueskythinking.megapp.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.code4piter.blueskythinking.megapp.R;

import java.util.ArrayList;
import java.util.List;


public class FilterActivity extends Activity {
    public static final String PREF_DISTANCE = "PREF_DISTANCE";
    public static final String PREF_SORT_BY = "PREF_SORT_BY";
    public static final String PREF_SORT_DIRECTION = "PREF_SORT_DIRECTION";
    private CardView cardView, cardView2, cardView3;
    private RadioGroup group, group2;
    private SharedPreferences preferences;
    private Spinner spinner;
    public static final String TAG = FilterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        cardView = (CardView) findViewById(R.id.cardView);
        cardView2 = (CardView) findViewById(R.id.cardView2);
        cardView3 = (CardView) findViewById(R.id.cardView3);
        group = (RadioGroup) cardView.findViewById(R.id.radioGroup);
        group2 = (RadioGroup) cardView2.findViewById(R.id.radioGroup);
        spinner = (Spinner) cardView3.findViewById(R.id.spinner);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        List<String> list = new ArrayList<>();
        for (int i = 5; i <= 40; i += 5) {
            list.add(i + "");
        }
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list));
        boolean sortDirection = preferences.getBoolean(FilterActivity.PREF_SORT_DIRECTION, true);
        int distance = preferences.getInt(FilterActivity.PREF_DISTANCE, 5);
        String sortBy = preferences.getString(FilterActivity.PREF_SORT_BY, "dangerLevel");
        spinner.setSelection(distance/5-1);
        if (sortDirection) {
            group2.check(R.id.ascSort);
        } else {
            group2.check(R.id.dscSort);
        }
        if(sortBy.equals("dangerLevel")){
            group.check(R.id.dangerButton);
        }else{
            group.check(R.id.distanceButton);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = preferences.edit();
        if (group.getCheckedRadioButtonId() == R.id.dangerButton) {
            editor.putString(FilterActivity.PREF_SORT_BY, "dangerLevel");
        } else {
            editor.putString(FilterActivity.PREF_SORT_BY, "distance");
        }
        int distance = spinner.getSelectedItemPosition() * 5 + 5;
        editor.putInt(FilterActivity.PREF_DISTANCE, distance);
        if (group2.getCheckedRadioButtonId() == R.id.ascSort) {
            editor.putBoolean(PREF_SORT_DIRECTION, true);
        } else {
            editor.putBoolean(PREF_SORT_DIRECTION, false);
        }
        editor.commit();
    }
}
