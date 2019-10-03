package com.bignerdranch.android.myexam;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.bignerdranch.android.myexam.MainActivity.KEY_ABS;
import static com.bignerdranch.android.myexam.MainActivity.KEY_CONDITIONER;
import static com.bignerdranch.android.myexam.MainActivity.KEY_MODEL;
import static com.bignerdranch.android.myexam.MainActivity.KEY_SALON;
import static com.bignerdranch.android.myexam.MainActivity.KEY_VOLUME;
import static com.bignerdranch.android.myexam.MainActivity.KEY_YEAR;
import static com.bignerdranch.android.myexam.MainActivity.TABLE_NAME;
import static com.bignerdranch.android.myexam.MainActivity.mDataBase;
import static com.bignerdranch.android.myexam.SearchFragment.sSearchedCar;

public class RecyclerFragment extends Fragment implements CarAdapter.ItemClickListener {

    private static final String TAG = "log_tag";

    private ArrayList<Car> mCars;
    public static ArrayList<Car> mFilteredCars;

    private RecyclerView mRecycler;
    private CarAdapter adapter;

    public static Fragment newInstance() {

        return new RecyclerFragment();
    }

    public void readDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = mDataBase.query(TABLE_NAME, null, null, null,
                        null, null, null, null);    // достаём все данные из таблицы
                mCars = new ArrayList<>();
                if (cursor.moveToFirst()) {
                    int modelIndex = cursor.getColumnIndex(KEY_MODEL);
                    int yearIndex = cursor.getColumnIndex(KEY_YEAR);
                    int volumeIndex = cursor.getColumnIndex(KEY_VOLUME);
                    int salonIndex = cursor.getColumnIndex(KEY_SALON);
                    int conditionerIndex = cursor.getColumnIndex(KEY_CONDITIONER);
                    int absIndex = cursor.getColumnIndex(KEY_ABS);
                    do {
                        String model = cursor.getString(modelIndex);
                        int year = cursor.getInt(yearIndex);
                        double volume = cursor.getDouble(volumeIndex);
                        boolean salon = cursor.getInt(salonIndex) > 0;
                        boolean conditioner = cursor.getInt(conditionerIndex) > 0;
                        boolean abs = cursor.getInt(absIndex) > 0;
                        mCars.add(new Car(model, year, volume, salon, conditioner, abs));
                        Log.d("logTag", "model = " + model + ", year = " + year + ", volume = " + volume +
                                ", salon = " + salon + ", conditioner = " + conditioner + ", abs = " + abs);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String firstCarModel = mCars.get(0).getModel();
                double lastCarVolume = mCars.get(9).getVolume();
                Log.d("logTag", "firstCarModel" + firstCarModel + ", lastCarVolume = " + lastCarVolume);
                carFilter();
            }
        }).start();
    }

        public void carFilter () {
            mFilteredCars = new ArrayList<>();
            for (int i = 0; i < mCars.size(); i++) {
                if (sSearchedCar.getModel().equals(mCars.get(i).getModel())
                        && sSearchedCar.getYearFrom() <= mCars.get(i).getYear()
                        && sSearchedCar.getYearTo() >= mCars.get(i).getYear()
                        && sSearchedCar.getVolume() == mCars.get(i).getVolume()
                        && ((sSearchedCar.isSalon() == mCars.get(i).getSalon() == true)
                        || (sSearchedCar.isConditioner() == mCars.get(i).getConditioner() == true)
                        || (sSearchedCar.isAbs() == mCars.get(i).getAbs() == true))) {
                    mFilteredCars.add(mCars.get(i));
                    String firstCarModel = mFilteredCars.get(0).getModel();
                    Log.d("logTag", "mFilteredCars model" + firstCarModel);
                }
            }
        }

        @Override
        public void onItemClick (View view,int position){
            DetailsFragment detailsFragment = (DetailsFragment) DetailsFragment.newInstance(mFilteredCars.get(position));
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fl_container, detailsFragment)
                    .commit();
        }

        @Override
        public void onAttach (Context context){
            super.onAttach(context);
            Log.d(TAG, "onAttach");
        }

        @Override
        public void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate");
        }

        @Nullable
        @Override
        public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState){

            Log.d("log_tag", "RecyclerFragment onCreateView");
            Log.d(TAG, "onCreateView");
            readDatabase();
//        carFilter();
            View view = inflater.inflate(R.layout.fragment_recycler, container, false);

            mRecycler = view.findViewById(R.id.rv_cars);
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

            adapter = new CarAdapter(mFilteredCars);
            adapter.setClickListener(this);
            mRecycler.setAdapter(adapter);

            return view;
        }

        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            Log.d(TAG, "onActivityCreated");
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onStart () {
            super.onStart();
            Log.d(TAG, "onStart");

        }

        @Override
        public void onResume () {
            super.onResume();
            Log.d(TAG, "onResume");
//        mRecycler.getAdapter().setData();
        }

        @Override
        public void onPause () {
            super.onPause();
            Log.d(TAG, "onPause");
        }

        @Override
        public void onStop () {
            super.onStop();
            Log.d(TAG, "onStop");
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView");
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            Log.d(TAG, "onDestroy");
        }

        @Override
        public void onDetach () {
            super.onDetach();
            Log.d(TAG, "onDetach");
        }
    }
