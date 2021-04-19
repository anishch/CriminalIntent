package com.example.criminalintent;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import static android.widget.CompoundButton.*;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE ="DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private static final int REQUEST_DATE = 0;
    private final static int REQUEST_TIME= 1;


    private EditText mTitleField;
    private Crime mCrime;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private EditText mCOVIDViolator;

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //mCrime = new Crime();
        View v = inflater.inflate(R.layout.fragment_crime, container,
                false);
        mCOVIDViolator = (EditText) v.findViewById(R.id.crime_perpetrator);
        mCOVIDViolator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        mDateButton = (Button)
                v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       FragmentManager manager =
                                                               getFragmentManager();
                                                       DatePickerFragment dialog =
                                                               DatePickerFragment
                                                                       .newInstance(mCrime.getDate());
                                                       dialog.setTargetFragment(CrimeFragment.this,
                                                               REQUEST_DATE);
                                                       dialog.show(manager, DIALOG_DATE);
                                                   }
                                               });
        mTimeButton = (Button) v.findViewById(R.id.crime_time);
        //mTimeButton.setText(mCrime.getTime().toString());
        mTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager =
                        getFragmentManager();
                TimePickerFragment dialog =
                        TimePickerFragment
                                .newInstance(mCrime.getTime());

                dialog.setTargetFragment(CrimeFragment.this,
                        REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });
        mSolvedCheckBox =
                (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        FragmentManager fm = getFragmentManager();
        Fragment fragment =
                fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,
                            fragment)
                    .commit();
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            mDateButton.setText(mCrime.getDate().toString());
        }
        if (requestCode == REQUEST_TIME){
            Time time = (Time) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mCrime.setTime(time.getHours(), time.getMinutes(), time.getSeconds());
        }
    }


}