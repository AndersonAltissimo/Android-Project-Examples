package br.com.andersonaltissimo.carros.data;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import br.com.andersonaltissimo.carros.R;
import br.com.andersonaltissimo.carros.entities.Car;

/**
 * Created by anderson on 20/07/17.
 */

public class CarMock {

    private List<Car> lstCars;

    public CarMock(Activity activity){
        this.lstCars = getCarMockList(activity);
    }

    public List<Car> getList(){
        return this.lstCars;
    }

    public Car get(int id){ return this.lstCars.get(id); }

    public List<Car> getCarMockList(Activity activity) {
        List<Car> lst = new ArrayList<>();
        int id = 0;
        Car audiR8 = new Car(id, "Audi R8", "Audi", 610, 1165338.00, ContextCompat.getDrawable(activity, R.drawable.audir8));
        lst.add(audiR8);
        Car bugattiChiron = new Car(++id, "Bugatti Chiron", "Bugatti", 1520, 1000000.00, ContextCompat.getDrawable(activity, R.drawable.bugattichiron));
        lst.add(bugattiChiron);
        Car civicTypeR = new Car(++id, "Civic Type R", "Honda", 310, 136000.00, ContextCompat.getDrawable(activity, R.drawable.civictyper));
        lst.add(civicTypeR);
        Car corolla = new Car(++id, "Corolla", "Toyota", 144, 101000.00, ContextCompat.getDrawable(activity, R.drawable.corolla));
        lst.add(corolla);
        Car ferrari458Italia = new Car(++id, "Ferrari 458 Itália", "Ferrari", 670, 1500000.00, ContextCompat.getDrawable(activity, R.drawable.ferrari458));
        lst.add(ferrari458Italia);
        Car ferrariF60America = new Car(++id, "Ferrari F60 América", "Ferrari", 700, 2500000.00, ContextCompat.getDrawable(activity, R.drawable.ferrarif60america));
        lst.add(ferrariF60America);
        Car jaguarFTypeCoupe = new Car(++id, "Jaguar F-Type Coupe", "Jaguar", 340, 500000.00, ContextCompat.getDrawable(activity, R.drawable.jaguarftype));
        lst.add(jaguarFTypeCoupe);
        Car jeepRenegade = new Car(++id, "Jeep Renegade", "Jeep", 139, 79000.00, ContextCompat.getDrawable(activity, R.drawable.jeeprenegade));
        lst.add(jeepRenegade);
        Car lamborghiniAventador = new Car(++id, "Lamborghini Aventador", "Lamborghini", 700, 2800000.00, ContextCompat.getDrawable(activity, R.drawable.lamborghiniaventador));
        lst.add(lamborghiniAventador);
        Car mcLarenMP412C = new Car(++id, "McLaren MP4-12C", "McLaren", 625, 2200000.00, ContextCompat.getDrawable(activity, R.drawable.mclarenmp412c));
        lst.add(mcLarenMP412C);
        Car mustang = new Car(++id, "Mustang", "Ford", 533, 270000.00, ContextCompat.getDrawable(activity, R.drawable.mustang));
        lst.add(mustang);
        Car porsche911 = new Car(++id, "Porsche 911", "Porsche", 435, 509000.00, ContextCompat.getDrawable(activity, R.drawable.porsche911));
        lst.add(porsche911);
        Car rangeRoverEvoque = new Car(++id, "Range Rover Evoque", "Range Rover", 285, 224000.00, ContextCompat.getDrawable(activity, R.drawable.rangerover));
        lst.add(rangeRoverEvoque);
        Car vanquish = new Car(++id, "Vanquish", "Aston Martin", 573, 1850000.00, ContextCompat.getDrawable(activity, R.drawable.vanquish));
        lst.add(vanquish);

        return lst;
    }
}
