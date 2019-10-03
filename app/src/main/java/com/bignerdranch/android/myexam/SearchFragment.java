package com.bignerdranch.android.myexam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SearchFragment extends Fragment {
    private TextView mTvModel;
    private Spinner mSpModel;
    private TextView mTvYear;
    private TextView mTvFrom;
    private TextView mTvYearFrom;
    private SeekBar mSbYearFrom;
    private TextView mTvTo;
    private TextView mTvYearTo;
    private SeekBar mSbYearTo;
    private TextView mTvVolume;
    private Spinner mSpVolume;
    private TextView mTvOptions;
    private TextView mTvLeatherSalon;
    private TextView mTvConditioner;
    private TextView mTvAbs;
    private CheckBox mCbLeatherSalon;
    private CheckBox mCbConditioner;
    private CheckBox mCbAbs;
    private Button mBtnFind;

    private String mChosenModel;
    private double mChosenVolume;
    private int mChosenYearFrom;
    private int mChosenYearTo;

    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;

    public static SearchedCar sSearchedCar;
    private int i;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

/*    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager manager = getFragmentManager();
        SavedRecyclerFragment savedRecyclerFragment;
        if (id == R.id.action_saved_cars) {
            savedRecyclerFragment = (SavedRecyclerFragment) SavedRecyclerFragment.newInstance();
            manager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fl_container, savedRecyclerFragment)
                    .commit();
        }
        return false;
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("log_tag", "SearchFragment onCreateView");
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        mTvModel = view.findViewById(R.id.tv_model);
        mSpModel = view.findViewById(R.id.sp_model);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvFrom = view.findViewById(R.id.tv_from);
        mTvYearFrom = view.findViewById(R.id.tv_year_from);
        mSbYearFrom = view.findViewById(R.id.sb_year_from);
        mTvTo = view.findViewById(R.id.tv_to);
        mTvYearTo = view.findViewById(R.id.tv_year_to);
        mSbYearTo = view.findViewById(R.id.sb_year_to);
        mTvVolume = view.findViewById(R.id.tv_volume);
        mSpVolume = view.findViewById(R.id.sp_volume);
        mTvOptions = view.findViewById(R.id.tv_options);
        mTvLeatherSalon = view.findViewById(R.id.tv_leather_salon);
        mTvConditioner = view.findViewById(R.id.tv_conditioner);
        mTvAbs = view.findViewById(R.id.tv_abs);
        mCbLeatherSalon = view.findViewById(R.id.cb_leather_salon);
        mCbConditioner = view.findViewById(R.id.cb_conditioner);
        mCbAbs = view.findViewById(R.id.cb_abs);
        mBtnFind = view.findViewById(R.id.btn_find);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);



        mSbYearFrom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvYearFrom.setText(String.valueOf(mSbYearFrom.getProgress() + 1990));
                mChosenYearFrom = Integer.parseInt(mTvYearFrom.getText().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSbYearTo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvYearTo.setText(String.valueOf(mSbYearTo.getProgress() + Integer.parseInt(mTvYearFrom.getText().toString())));
                if (Integer.parseInt(mTvYearTo.getText().toString()) > 2019) {
                    mTvYearTo.setText(String.valueOf(2019));
                    mChosenYearTo = Integer.parseInt(mTvYearTo.getText().toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(mTvLeatherSalon.getContext(), R.array.models, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpModel.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(mTvLeatherSalon.getContext(), R.array.volumes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpVolume.setAdapter(adapter);

        mSpModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choise = getResources().getStringArray(R.array.models);
                mChosenModel = choise[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpVolume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choise = getResources().getStringArray(R.array.volumes);
                mChosenVolume = Double.parseDouble(choise[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        car = new Car ("BMW",2004, 2.0,true,true,true);


//        mSbYearFrom.getProgress();

        mBtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                FragmentManager fm = getFragmentManager();

                Fragment fragment = fm.findFragmentById(R.id.fl_container);
                if (fragment == null) {
                    fragment = new RecyclerFragment();
                    fm.beginTransaction()
                            .add(R.id.fl_container, fragment)
                            .commit();
                }*/

                mProgressBar.setVisibility(ProgressBar.VISIBLE);
                i = 0;
                mProgressBar.setProgress(i);
                mCountDownTimer = new CountDownTimer(4000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.v("Logt", "Tick" + i + millisUntilFinished);
                        i++;
                        mProgressBar.setProgress((int) i * 100 / (4000 / 1000));

                    }

                    @Override
                    public void onFinish() {
                        mProgressBar.setProgress(100);
                        mProgressBar.setVisibility(View.GONE);
                        Log.d("Logt", "ProgressBar " + mProgressBar.getVisibility());
                        FragmentManager manager = getFragmentManager();

                        RecyclerFragment recyclerFragment;
                        recyclerFragment = (RecyclerFragment) RecyclerFragment.newInstance();

                        manager.beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fl_container, recyclerFragment)
                                .commit();
                    }
                };
                mCountDownTimer.start();
/*                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


                sSearchedCar = new SearchedCar(mChosenModel, mChosenYearFrom, mChosenYearTo,
                        mChosenVolume, mCbLeatherSalon.isChecked(),
                        mCbConditioner.isChecked(), mCbAbs.isChecked());
                Log.d("logTag", "car = " + sSearchedCar.getModel() + sSearchedCar.getYearFrom()
                        + sSearchedCar.getYearTo() + sSearchedCar.getVolume()
                        + sSearchedCar.isSalon() + sSearchedCar.isConditioner() + sSearchedCar.isAbs());


  //              if(/*(mProgressBar.getVisibility() == View.GONE) && */sSearchedCar != null) {
                   //                }
            }
        });
        return view;
    }
}
