package com.example.mysoap;

import android.graphics.drawable.Drawable;

public class ModelCart {

    public boolean selected;
    String name, price,id;
    Integer amount;
    Drawable image;


    //constructor
    public ModelCart(String id, Drawable image, String name, int amount, String price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public Drawable getImg() {
        return this.image;
    }

    public int getAmount() {
        return this.amount;
    }

    }

