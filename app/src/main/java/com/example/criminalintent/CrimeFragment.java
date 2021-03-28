package com.example.criminalintent;

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

import static android.widget.CompoundButton.*;

public class CrimeFragment extends Fragment {
    private EditText mTitleField;
    private Crime mCrime;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private EditText mCOVIDViolator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
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
        // ConcBand/PE, LIT [D], CALC, PHYSICS, ECON/GOV, STATS, BIO, FREE (FULL) {Anish}
        // Orchestra (Full), ENG/LIT [D], CALC, PHYSICS, ECON, APES, FREE/PE, FREE/NETZERO,{Vinay}
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
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
        mDateButton.setEnabled(false);
        mSolvedCheckBox =
                (CheckBox) v.findViewById(R.id.crime_solved);
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


}