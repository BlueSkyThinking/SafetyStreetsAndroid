package com.code4piter.blueskythinking.megapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.code4piter.blueskythinking.megapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

	public static final String PREF_DANGER_LEVEL = "DangerLevel";
	public static final String PREF_DISTANCE = "Distance";
	public static final String PREF_SORT_BY = "SortBy";

	@BindView(R.id.sortSpinner)
	Spinner sortSpinner;
	@BindView(R.id.filter0Spinner)
	Spinner filter0Spinner;
	@BindView(R.id.filter1Spinner)
	Spinner filter1Spinner;
	@BindView(R.id.filter2Spinner)
	Spinner filter2Spinner;
	@BindView(R.id.showRg)
	RadioGroup showRg;
	@BindView(R.id.switchBtn)
	SwitchCompat choiceSc;
	@BindView(R.id.searchButton)
	Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filters);
		ButterKnife.bind(this);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				show();
				Intent intent = new Intent(FilterActivity.this, CamerasActivity.class);
				startActivity(intent);
			}
		});
	}

	public void show() {
		//getSelectedItemPosition()
		//sortSpinner.setOnItemSelectedListener(onSelectedPositionSort);
		Toast.makeText(this, "sortSpinner = " + sortSpinner.getSelectedItemPosition()
				+ " showRg = " + showRg.getCheckedRadioButtonId()
				+ " filter0Spinner = " + filter0Spinner.getSelectedItemPosition()
				+ " filter1Spinner = " + filter1Spinner.getSelectedItemPosition()
				+ " filter2Spinner = " + filter2Spinner.getSelectedItemPosition()
				+ " choiceSc = " + choiceSc.isChecked(), Toast.LENGTH_LONG).show();


	}
}
