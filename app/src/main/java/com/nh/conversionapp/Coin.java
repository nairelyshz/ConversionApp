package com.nh.conversionapp;

import android.util.Log;

import java.util.ArrayList;

public class Coin {
    private String name;
    private double value;

    public Coin(String nameNew, double valueNew){
        name = nameNew;
        value = valueNew;
    }

    public static ArrayList<Coin> createContactsList() {
        ArrayList<Coin> coins = new ArrayList<Coin>();
        ArrayList <String> names = new ArrayList<>();
        names.add("ETH");
        names.add("BTC");
        names.add("PTR");
        names.add("BS");
        names.add("EURO");
        names.add("USD");
        double num = 1.75;
        for (int i = 0; i < names.size(); i++) {
            Coin c = new Coin(names.get(i), num);
            coins.add(c);
            num += num;
        }


        return coins;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
