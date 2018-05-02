package com.example.janetdo.financeapp;

/**
 * Created by janetdo on 16.04.18.
 */

public enum Category {
    food, travel, cosmetics, entertainment, empty;

    public enum food{
        restaurant, supermarket;
    }

    public enum travel {
        DB, tram, bus;
    }

    public enum cosmetics {
        products, spa;
    }


    public enum entertainment{
        electronics, outdoor;
    }


}
