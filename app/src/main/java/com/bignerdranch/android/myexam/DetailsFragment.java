package com.bignerdranch.android.myexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    private static final String BUNDLE_MODEL = "BUNDLE_MODEL";
    private static final String BUNDLE_YEAR = "BUNDLE_YEAR";
    private static final String BUNDLE_VOLUME = "BUNDLE_VOLUME";
    private static final String BUNDLE_SALON = "BUNDLE_SALON";
    private static final String BUNDLE_CONDITIONER = "BUNDLE_CONDITIONER";
    private static final String BUNDLE_ABS = "BUNDLE_ABS";
    private TextView mTvModel;
    private TextView mTvYear;
    private TextView mTvVolume;
    private TextView mTvSalon;
    private TextView mTvConditioner;
    private TextView mTvAbs;
    private CheckBox mCbSalon;
    private CheckBox mCbConditioner;
    private CheckBox mCbAbs;
    private Button mSave;

    public static ArrayList<Car> mSavedCars = new ArrayList<>();


    public static DetailsFragment newInstance(Car car) {
        Bundle args = new Bundle(6);
        args.putString(BUNDLE_MODEL, car.getModel());
        args.putInt(BUNDLE_YEAR, car.getYear());
        args.putDouble(BUNDLE_VOLUME, car.getVolume());
        args.putBoolean(BUNDLE_SALON, car.getSalon());
        args.putBoolean(BUNDLE_CONDITIONER, car.getConditioner());
        args.putBoolean(BUNDLE_ABS, car.getAbs());
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mTvModel = view.findViewById(R.id.tv_model);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvVolume = view.findViewById(R.id.tv_volume);
        mTvSalon = view.findViewById(R.id.tv_leather_salon);
        mTvConditioner = view.findViewById(R.id.tv_conditioner);
        mTvAbs = view.findViewById(R.id.tv_abs);
        mCbSalon = view.findViewById(R.id.cb_leather_salon);
        mCbConditioner = view.findViewById(R.id.cb_conditioner);
        mCbAbs = view.findViewById(R.id.cb_abs);
        mSave = view.findViewById(R.id.btn_save);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSavedCars.add(new Car(mTvModel.getText().toString(), Integer.parseInt(mTvYear.getText().toString()),
                        Double.parseDouble(mTvVolume.getText().toString()), mCbSalon.isChecked(),
                        mCbConditioner.isChecked(), mCbAbs.isChecked()));
            }
        });

        Bundle args = getArguments();
        if(args!=null) {
            mTvModel.setText(args.getString(BUNDLE_MODEL));
            mTvYear.setText(String.valueOf(args.getInt(BUNDLE_YEAR)));
            mTvVolume.setText(String.valueOf(args.getDouble(BUNDLE_VOLUME)));
            mCbSalon.setChecked(args.getBoolean(BUNDLE_SALON));
            mCbConditioner.setChecked(args.getBoolean(BUNDLE_CONDITIONER));
            mCbAbs.setChecked(args.getBoolean(BUNDLE_ABS));
        }
        return view;
    }

    public void updateView(final Car car) {
        mTvModel.setText(car.getModel());
        mTvYear.setText((String.valueOf(car.getYear())));
        mTvVolume.setText(((Double.toString(car.getVolume()))));
        mCbSalon.setChecked(car.getSalon());
        mCbConditioner.setChecked(car.getConditioner());
        mCbAbs.setChecked(car.getAbs());
    }
}
