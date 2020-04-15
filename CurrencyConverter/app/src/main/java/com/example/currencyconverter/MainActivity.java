package com.example.currencyconverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    List<String> currencyList;
    ArrayAdapter<String> adapter1, adapter2;

    Spinner spinner1;
    Spinner spinner2;

    int itemSpinner1Selected;
    int itemSpinner2Selected;

    List<Currency> currencies;

    TextView currency1, currency2;
    Button btn_1, btn_2, btn_3,
            btn_4, btn_5, btn_6,
            btn_7, btn_8, btn_9, btn_0,
            btn_bs, btn_ce, btn_point;

    Dollar dollar;
    Dong dong;
    Kip kip;
    Peso peso;
    Yuan yuan;

    String number2 = "0";

    static final int MAX_NO_CURRENCY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyList = new ArrayList<>();
        currencyList.add("United States - Dollar");
        currencyList.add("Vietnam - Dong");
        currencyList.add("Laos - Kips");
        currencyList.add("Uruguay - Peso");
        currencyList.add("China - Yuan");


        dollar = new Dollar();
        dong = new Dong();
        kip = new Kip();
        peso = new Peso();
        yuan = new Yuan();

        currencies = new ArrayList<>();
        currencies.add(dollar);
        currencies.add(dong);
        currencies.add(kip);
        currencies.add(peso);
        currencies.add(yuan);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencyList);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencyList);

        spinner1 = findViewById(R.id.spinner_currency_1);
        spinner2 = findViewById(R.id.spinner_currency_2);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        currency1 = findViewById(R.id.text_currency_1);
        currency2 = findViewById(R.id.text_currency_2);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_ce = findViewById(R.id.btn_ce);
        btn_bs = findViewById(R.id.btn_bs);
        btn_point = findViewById(R.id.btn_point);


        currency1.setOnClickListener(this);
        currency2.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_ce.setOnClickListener(this);
        btn_bs.setOnClickListener(this);
        btn_point.setOnClickListener(this);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSpinner1Selected = position;
                updateDisplay(number2, itemSpinner1Selected, itemSpinner2Selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSpinner2Selected = position;
                updateDisplay(number2, itemSpinner1Selected, itemSpinner2Selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_0){
            numberButtonHandling("0");
        }
        if(id == R.id.btn_1){
            numberButtonHandling("1");
        }
        if(id == R.id.btn_2){
            numberButtonHandling("2");
        }
        if(id == R.id.btn_3){
            numberButtonHandling("3");
        }
        if(id == R.id.btn_4){
            numberButtonHandling("4");
        }
        if(id == R.id.btn_5){
            numberButtonHandling("5");
        }
        if(id == R.id.btn_6){
            numberButtonHandling("6");
        }
        if(id == R.id.btn_7){
            numberButtonHandling("7");
        }
        if(id == R.id.btn_8){
            numberButtonHandling("8");
        }
        if(id == R.id.btn_9){
            numberButtonHandling("9");
        }

        if(id == R.id.btn_bs){
            bsButtonHandling();
        }
        if(id == R.id.btn_ce){
            ceButtonHandling();
        }

        if (id == R.id.btn_point) {
            pointButtonHandling();
        }
    }

    void numberButtonHandling(String append){
        number2 = currency2.getText().toString();
        if(number2.equals("0")) {
            number2 = append;
        } else {
            number2 = number2 + append;
        }
        updateDisplay(number2, itemSpinner1Selected, itemSpinner2Selected);
    }

    void pointButtonHandling(){
        if (number2.equals("0")){
            number2 = "0.";
        } else {
            number2 = number2 + ".";
        }
        updateDisplay(number2, itemSpinner1Selected, itemSpinner2Selected);
    }

    void bsButtonHandling(){
        number2 = currency2.getText().toString();
        if(number2.length() == 1){
            number2 = "0";
        } else {
            number2 = removeLastChar(number2);
        }
        updateDisplay(number2, itemSpinner1Selected, itemSpinner2Selected);
    }

    void ceButtonHandling(){
        currency1.setText("0");
        currency2.setText("0");
    }

    void updateDisplay(String number2, int itemSpinner1Selected, int itemSpinner2Selected){
        double number1 = 0.0;
        double currency1OverDollar = currencies.get(itemSpinner1Selected).toDollar();
        double currency2OverDollar = currencies.get(itemSpinner2Selected).toDollar();
        number1 = Double.valueOf(number2) * currency1OverDollar / currency2OverDollar;
        if (number1 == 0.0) {
            currency1.setText("0");
        } else {
            currency1.setText(Double.toString(number1));
        }
        currency2.setText(number2);
    }

    String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
