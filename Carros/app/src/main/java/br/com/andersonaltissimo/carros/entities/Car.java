package br.com.andersonaltissimo.carros.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by anderson on 20/07/17.
 */

public class Car {

    public int id;
    public String model;
    public int hp;
    public Double price;
    public String manuFacturer;
    public Drawable picture;

    public Car(int id, String model, String manuFactorer, int hp, Double price, Drawable picture) {
        this.id = id;
        this.model = model;
        this.hp = hp;
        this.price = price;
        this.manuFacturer = manuFactorer;
        this.picture = picture;
    }
}
