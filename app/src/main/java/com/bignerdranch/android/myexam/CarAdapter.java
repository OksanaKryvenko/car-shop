package com.bignerdranch.android.myexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.bignerdranch.android.myexam.RecyclerFragment.mFilteredCars;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{

//    private ArrayList<Car> mFilteredCars;
    private ItemClickListener mItemClickListener;

    public CarAdapter(ArrayList<Car> cars) {
        mFilteredCars = cars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mFilteredCars.get(position));
    }

    @Override
    public int getItemCount() {
        return mFilteredCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvModel;
        private TextView mTvYear;
        private TextView mTvVolume;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvModel = itemView.findViewById(R.id.tv_list_item_car_model);
            mTvYear = itemView.findViewById(R.id.tv_list_item_car_year);
            mTvVolume = itemView.findViewById(R.id.tv_list_item_car_volume);
            itemView.setOnClickListener(this);
        }

        public void setData(final Car car) {

            mTvModel.setText(car.getModel());
            mTvYear.setText(String.valueOf(car.getYear()));
            mTvVolume.setText((Double.toString(car.getVolume())));
            //           notifyDataSetChanged();
        }

 /*       public void refreshData() {
            mCbDone.

        }*/

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
