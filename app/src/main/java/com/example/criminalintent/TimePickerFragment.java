package com.example.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.time";

    private static final String ARG_TIME = "time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Time time) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, time);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Time time = (Time)
                getArguments().getSerializable(ARG_TIME);

        //time.getTime();

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker)
                v.findViewById(R.id.dialog_time_picker);
        mTimePicker.setEnabled(true);
        mTimePicker.setIs24HourView(false);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title).setPositiveButton(android.R.string.ok,
                        null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                        public void
                        onClick(DialogInterface dialog, int which) {
                            int minute =
                                    mTimePicker.getCurrentMinute();
                            int hour =
                                    mTimePicker.getCurrentHour();
                            Clock clock = new Clock() {
                                @Override
                                public ZoneId getZone() {
                                    return null;
                                }

                                @Override
                                public Clock withZone(ZoneId zone) {
                                    return null;
                                }

                                @Override
                                public Instant instant() {
                                    return null;
                                }
                            };
                            sendResult(Activity.RESULT_OK, time);
                        }
                        })
                .create();

    }

    private void sendResult(int resultCode, Time time) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        try{
            intent.putExtra(EXTRA_TIME, time);
        } catch (Exception e){
            Log.d("intent_error", "Issue!!!!!");
        }

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }



}
