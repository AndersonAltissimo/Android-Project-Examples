package br.com.andersonaltissimo.carros.view;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

import br.com.andersonaltissimo.carros.R;
import br.com.andersonaltissimo.carros.adapter.CarsListAdapter;
import br.com.andersonaltissimo.carros.constants.CarsConstants;
import br.com.andersonaltissimo.carros.data.CarMock;
import br.com.andersonaltissimo.carros.entities.Car;
import br.com.andersonaltissimo.carros.listener.OnListClickInteractionListener;

public class MainActivity extends AppCompatActivity {

    ViewHolder vh = new ViewHolder();

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        CarMock carMock = new CarMock(this);
        List<Car> carList = new ArrayList<>();
        carList.addAll(carMock.getList());

        this.context = this;
        this.vh.recyclerCars = (RecyclerView) findViewById(R.id.recycler_cars);

        OnListClickInteractionListener listener = new OnListClickInteractionListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(CarsConstants.CARRO_ID, id);

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

        //definir Adapter
        CarsListAdapter carsListAdapter = new CarsListAdapter(carList, listener);
        this.vh.recyclerCars.setAdapter(carsListAdapter);

        //definir Layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.vh.recyclerCars.setLayoutManager(linearLayoutManager);
    }


    private static class ViewHolder{
        RecyclerView recyclerCars;
    }
}
