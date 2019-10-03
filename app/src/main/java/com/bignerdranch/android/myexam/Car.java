package com.bignerdranch.android.myexam;

public class Car {
    private String mModel;
    private int mYear;
    private double mVolume;
    private boolean mSalon;
    private boolean mConditioner;
    private boolean mAbs;


    public Car(String model, int year, double volume, boolean salon, boolean conditioner, boolean abs) {
        mModel = model;
        mYear = year;
        mVolume = volume;
        mSalon = salon;
        mConditioner = conditioner;
        mAbs = abs;
    }

    public String getModel() {
        return mModel;
    }

    public int getYear() {
        return mYear;
    }

    public double getVolume() {
        return mVolume;
    }

    public boolean getSalon() {
        return mSalon;
    }

    public boolean getConditioner() {
        return mConditioner;
    }

    public boolean getAbs() {
        return mAbs;
    }
}
