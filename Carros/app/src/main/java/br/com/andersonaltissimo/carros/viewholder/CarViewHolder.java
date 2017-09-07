package br.com.andersonaltissimo.carros.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.andersonaltissimo.carros.R;
import br.com.andersonaltissimo.carros.entities.Car;
import br.com.andersonaltissimo.carros.listener.OnListClickInteractionListener;

/**
 * Created by anderson on 19/07/17.
 */

public class CarViewHolder extends RecyclerView.ViewHolder {

    private TextView edModel;
    private TextView edDetails;
    private ImageView imgCarPicture;

    public CarViewHolder(View itemView) {
        super(itemView);
        this.edModel = (TextView) itemView.findViewById(R.id.edModelo);
        this.edDetails = (TextView) itemView.findViewById(R.id.edDetails);
        this.imgCarPicture = (ImageView) itemView.findViewById(R.id.img_car_picture);
    }

    public void bindData(final Car car, final OnListClickInteractionListener listener) {

        this.imgCarPicture.setImageDrawable(car.picture);
        this.edModel.setText(car.model);

        this.edDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(car.id);
            }
        });
    }
}
