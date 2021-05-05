package com.example.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    //private List<Crime> mCrimes;
    private Map<UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new LinkedHashMap<>();
    }

    public void addCrime(Crime c) {
        mCrimes.put(c.getId(), c);
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }


    public Crime getCrime(UUID id) {
        if (mCrimes.containsKey(id)){
            return mCrimes.get(id);
        }
        else {
            return null;
        }
    }

}