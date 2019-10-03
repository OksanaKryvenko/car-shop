package com.bignerdranch.android.myexam;

public class SearchedCar {
    private String mModel;
    private int mYearFrom;
    private int mYearTo;
    private double mVolume;
    private boolean mSalon;
    private boolean mConditioner;
    private boolean mAbs;

    public SearchedCar(String model, int yearFrom, int yearTo, double volume, boolean salon, boolean conditioner, boolean abs) {
        mModel = model;
        mYearFrom = yearFrom;
        mYearTo = yearTo;
        mVolume = volume;
        mSalon = salon;
        mConditioner = conditioner;
        mAbs = abs;
    }

    public String getModel() {
        return mModel;
    }

    public int getYearFrom() {
        return mYearFrom;
    }

    public int getYearTo() {
        return mYearTo;
    }

    public double getVolume() {
        return mVolume;
    }

    public boolean isSalon() {
        return mSalon;
    }

    public boolean isConditioner() {
        return mConditioner;
    }

    public boolean isAbs() {
        return mAbs;
    }
}
