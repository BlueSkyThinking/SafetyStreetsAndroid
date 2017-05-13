package com.code4piter.blueskythinking.megapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.code4piter.blueskythinking.megapp.R;

public class FilterActivity extends AppCompatActivity {

    public static final String PREF_DANGER_LEVEL = "DangerLevel";
    public static final String PREF_DISTANCE = "Distance";
    public static final String PREF_SORT_BY = "SortBy";

    Spinner sortSpinner, filter0Spinner, filter1Spinner, filter2Spinner;
    RadioGroup showRg;
    SwitchCompat choiceSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        sortSpinner = (Spinner) findViewById(R.id.sortSpinner);
        filter0Spinner = (Spinner) findViewById(R.id.filter0Spinner);
        filter1Spinner = (Spinner) findViewById(R.id.filter1Spinner);
        filter2Spinner = (Spinner) findViewById(R.id.filter2Spinner);
        showRg = (RadioGroup) findViewById(R.id.showRg);
        choiceSc = (SwitchCompat) findViewById(R.id.switchBtn);
    }

    public void show(View v){
        //getSelectedItemPosition()
        //sortSpinner.setOnItemSelectedListener(onSelectedPositionSort);
        Toast.makeText(this, "sortSpinner = " + sortSpinner.getSelectedItemPosition()
                + " showRg = " + showRg.getCheckedRadioButtonId()
                + " filter0Spinner = " + filter0Spinner.getSelectedItemPosition()
                + " filter1Spinner = " + filter1Spinner.getSelectedItemPosition()
                + " filter2Spinner = " + filter2Spinner.getSelectedItemPosition()
                + " choiceSc = " +choiceSc.isChecked(), Toast.LENGTH_LONG).show();
    }
}
