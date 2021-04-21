package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    //figured out how to clone, commit and push
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public int mNum;

    public Crime() {
        mId = UUID.randomUUID();
        //mNum = mNum;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }
    public void setNum(int ints){
        mNum = ints;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }   
}
