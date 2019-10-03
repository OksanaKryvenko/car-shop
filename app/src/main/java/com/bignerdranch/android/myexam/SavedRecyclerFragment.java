package com.bignerdranch.android.myexam;

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

import static com.bignerdranch.android.myexam.DetailsFragment.mSavedCars;
import static com.bignerdranch.android.myexam.MainActivity.KEY_ABS;
import static com.bignerdranch.android.myexam.MainActivity.KEY_CONDITIONER;
import static com.bignerdranch.android.myexam.MainActivity.KEY_MODEL;
import static com.bignerdranch.android.myexam.MainActivity.KEY_SALON;
import static com.bignerdranch.android.myexam.MainActivity.KEY_VOLUME;
import static com.bignerdranch.android.myexam.MainActivity.KEY_YEAR;
import static com.bignerdranch.android.myexam.MainActivity.TABLE_NAME;
import static com.bignerdranch.android.myexam.MainActivity.mDataBase;
//import static com.bignerdranch.android.myexam.SearchFragment.car;

public class SavedRecyclerFragment extends Fragment implements CarAdapter.ItemClickListener {
    private static final String TAG = "log_tag";

//    private ArrayList<Car> mSavedCars;

    private RecyclerView mRecycler;
    private CarAdapter adapter;

    public static Fragment newInstance() {
        return new SavedRecyclerFragment();
    }


    @Override
    public void onItemClick(View view, int position) {
        DetailsFragment detailsFragment = (DetailsFragment) DetailsFragment.newInstance(mSavedCars.get(position));
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_container, detailsFragment)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("log_tag", "RecyclerFragment onCreateView");
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        mRecycler = view.findViewById(R.id.rv_cars);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CarAdapter(mSavedCars);
        adapter.setClickListener(this);
        mRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
//        mRecycler.getAdapter().setData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
