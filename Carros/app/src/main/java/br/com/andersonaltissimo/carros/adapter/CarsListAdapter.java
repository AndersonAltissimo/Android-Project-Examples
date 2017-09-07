package br.com.andersonaltissimo.carros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.andersonaltissimo.carros.R;
import br.com.andersonaltissimo.carros.entities.Car;
import br.com.andersonaltissimo.carros.listener.OnListClickInteractionListener;
import br.com.andersonaltissimo.carros.viewholder.CarViewHolder;

/**
 * Created by anderson on 19/07/17.
 */

public class CarsListAdapter extends RecyclerView.Adapter<CarViewHolder>{

    private List<Car> lstCars;
    private OnListClickInteractionListener onListClickInteractionListener;

    public CarsListAdapter(List<Car> lstCars, OnListClickInteractionListener listener) {
        this.lstCars = lstCars;
        this.onListClickInteractionListener = listener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View carView = inflater.inflate(R.layout.row_car_list,parent,false);
        return new CarViewHolder(carView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = this.lstCars.get(position);
        holder.bindData(car, this.onListClickInteractionListener);
    }

    @Override
    public int getItemCount() {
        return this.lstCars.size();
    }
}
