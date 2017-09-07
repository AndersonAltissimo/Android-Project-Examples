package br.com.andersonaltissimo.carros.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.andersonaltissimo.carros.R;
import br.com.andersonaltissimo.carros.constants.CarsConstants;
import br.com.andersonaltissimo.carros.data.CarMock;
import br.com.andersonaltissimo.carros.entities.Car;

public class DetailsActivity extends AppCompatActivity {

    private ViewHolder vh = new ViewHolder();
    private CarMock mock;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.vh.txtModel = (TextView) findViewById(R.id.txt_model);
        this.vh.txtHorsePower = (TextView) findViewById(R.id.txt_horsePower);
        this.vh.txtPrice = (TextView) findViewById(R.id.txt_price);
        this.vh.txtManufacturer = (TextView) findViewById(R.id.txt_manu_facturer);
        this.vh.imgCarDetails = (ImageView) findViewById(R.id.img_car_details);


        this.mock = new CarMock(this);

        this.getDataFromActivity();
        this.setData();
    }

    private void setData() {
        this.vh.txtModel.setText(this.car.model);
        this.vh.txtHorsePower.setText(String.valueOf(this.car.hp));
        this.vh.txtPrice.setText(String.valueOf(this.car.price));
        this.vh.txtManufacturer.setText(String.valueOf(this.car.manuFacturer));
        this.vh.imgCarDetails.setImageDrawable(this.car.picture);
    }

    private void getDataFromActivity() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            this.car = this.mock.get(bundle.getInt(CarsConstants.CARRO_ID));
        }
    }

    private static class ViewHolder{
        TextView txtModel;
        TextView txtHorsePower;
        TextView txtPrice;
        TextView txtManufacturer;
        ImageView imgCarDetails;
    }

}
