package com.nh.conversionapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConversionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConversionFragment extends Fragment {
    View rootview;
    ArrayList<Coin> coins;
    EditText newValue;
    Spinner spinnerCoins;
    String coinSelected;
    Button btnChange;
    double value;

    public ConversionFragment() {
        // Required empty public constructor
    }

    public static ConversionFragment newInstance(String param1, String param2) {
        ConversionFragment fragment = new ConversionFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        coins = Coin.createContactsList();

        newValue = rootview.findViewById(R.id.value);

        spinnerCoins = rootview.findViewById(R.id.coins_spinner);
        spinnerCoins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coinSelected = parent.getItemAtPosition(position).toString();
                Log.d("select", coinSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnChange = rootview.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView coinsList = rootview.findViewById(R.id.coinsRecycler);
                value = Double.parseDouble(newValue.getText().toString());
                CoinAdapter coinAdapter = new CoinAdapter(coins, value, coinSelected, rootview.getContext());
                coinsList.setAdapter(coinAdapter);
                coinsList.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_conversion, container, false);
        return rootview;
    }

}
