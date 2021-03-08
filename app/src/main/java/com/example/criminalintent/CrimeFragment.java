package com.example.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {
    private EditText mTitleField;
    private Crime mCrime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mCrime = new Crime();
        View v = inflater.inflate(R.layout.fragment_crime, container,
                        false);
        v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new
                                                   TextWatcher() {
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
        return v;

    }


}
