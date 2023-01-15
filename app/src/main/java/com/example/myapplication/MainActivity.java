package com.example.myapplication;

import static com.example.myapplication.Settings_class._currency;
import static com.example.myapplication.Settings_class._material_name;
import static com.example.myapplication.Settings_class._price4kWh;
import static com.example.myapplication.Settings_class._material_price;
import static com.example.myapplication.Settings_class._printer_name;
import static com.example.myapplication.Settings_class._printer_power;
import static com.example.myapplication.Settings_class._profit;
import static com.example.myapplication.Settings_class._tax;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText printTimeText =  findViewById(R.id.PrintTime);
        EditText materialWeightText =  findViewById(R.id.PrintWeight);

        printTimeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Print_time = printTimeText.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialWeightText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Print_weight=materialWeightText.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




         }

    @SuppressLint("SQLiteString")
    @Override
    protected void onStart() {   // ta funkcja odpala się za każdym razem jak ten ekran jest
        // widoczny dla użytkownika
        super.onStart();


        SQLiteDatabase settings_db =openOrCreateDatabase("settings.db", Context.MODE_PRIVATE,
                null); // tutaj otwierana jest baza danych
        settings_db.execSQL("CREATE TABLE IF NOT EXISTS 'Settings' (id INTEGER PRIMARY KEY CHECK (id = 0 )," +
                " Printer_name STRING, Price_4_kWh STRING,  Material_name STRING, Price_of_material," +
                " Currency STRING, Printer_power STRING, Profit_margin STRING, Tax STRING)"); //
        // Tu tworzę tabele jeżeli jeszcze jej nie ma






        String columns [] ={"id","Printer_name","Price_4_kWh","Material_name","Price_of_material"
                ,"Currency","Printer_power","Profit_margin", "Tax"}; // tu wczytuje wartości z
        // tabeli
        Cursor k =settings_db.query("Settings",columns,null,null,null,null,null);
        k.moveToFirst();
        _printer_name=k.getString(1);
        _price4kWh=k.getFloat(2);
        _material_name=k.getString(3);
        _material_price=k.getFloat(4);
        _currency=k.getString(5);
        _printer_power=k.getFloat(6);
        _profit=k.getFloat(7);
        _tax=k.getFloat(8);






        settings_db.close(); // tu zamykam bazę


        EditText printTimeText =  findViewById(R.id.PrintTime);
        EditText materialWeightText =  findViewById(R.id.PrintWeight);
        printTimeText.setText(Print_time);
       materialWeightText.setText(Print_weight);


    }







    public void GoToSettings (View WWW){ // przejście do settingsów





        Intent screen = new Intent(this,Settings_class.class);
        startActivity(screen);




    }







   static String Print_time="0", Print_weight="0";
    float totalTax;
    Date date = Calendar.getInstance().getTime();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

    String Printer,Material,kWh,Date,Price; //Tutaj tworze wartości do wysłania do bazy


    public void Test (View WWW){ // testowa funkcja żeby nie trzeba było psuć już istniejących


        Printer=_printer_name;
        Material=_material_name;
        kWh=Float.toString(_price4kWh);
        Date= sdf.format(date);
        Price=Float.toString(totalTax);


        OkHttpClient client = new OkHttpClient();


        String url = "http://10.0.2.2/?Printer="+Printer+"&Material="+Material+
                "&kWh_Cost="+kWh+"&Date="+Date+"&Price_for_Client="+Price+"";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                //tekst.setText(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                           //tekst.setText(myResponse);







                        }
                    });
                }
            }
        });

        //Toast.makeText(MainActivity.this,Date+"" ,Toast.LENGTH_LONG).show();


    }

    public void Send(){

        Printer=_printer_name;
        Material=_material_name;
        kWh=Float.toString(_price4kWh);
        Date= sdf.format(date);
        Price=Float.toString(totalTax);


        OkHttpClient client = new OkHttpClient();


        String url = "http://10.0.2.2/?Printer="+Printer+"&Material="+Material+
                "&kWh_Cost="+kWh+"&Date="+Date+"&Price_for_Client="+Price+"";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                //tekst.setText(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //tekst.setText(myResponse);







                        }
                    });
                }
            }
        });

    }


    public void Calc_cost(View WWW){  //tutaj się dzieje matematyka

        try {
            TextView costText =  findViewById(R.id.Cost);
            EditText printTimeText =  findViewById(R.id.PrintTime);
            EditText materialWeightText =  findViewById(R.id.PrintWeight);
            float time = Float.parseFloat(Print_time);
            float costOfEnergy = ((time*_printer_power)/1000)*_price4kWh;
            float weightOfMaterial = Float.parseFloat(Print_weight);
            float costOfMaterial = weightOfMaterial*(_material_price/1000);
            float totalCost = costOfEnergy+costOfMaterial;
            String totalCostSTR = Float.toString(totalCost);
            costText.setText(totalCostSTR);
            //-----------------------------------------------


            TextView profitText =  findViewById(R.id.CostMargin);
            float totalProfit = totalCost + totalCost*(_profit/100);
            String profitSTR = Float.toString(totalProfit);
            profitText.setText(profitSTR);

            //----------------------------------------------------

            TextView taxText =  findViewById((R.id.Price));
            totalTax = totalProfit + totalProfit*(_tax/100);
            String taxSTR = Float.toString(totalTax);
            taxText.setText(taxSTR);

            //----------------------------------------------------

            TextView currency1 = findViewById(R.id.Currency);
            TextView currency2 = findViewById(R.id.currency);
            TextView currency3 = findViewById(R.id.currency2);
            currency1.setText(_currency);
            currency2.setText(_currency);
            currency3.setText(_currency);

            //--------------------------------------------------
            Send();





        }

         catch (Exception e) {
            //




        }

        finally {
            ErrorToast();



        }


    }

public  void ErrorToast(){ //funkcja do powiadomienia użytkownika o braku danych.
    String a ="Some fields not filled";
    if(_printer_power==0.0 || _profit==0.0 || _tax==0.0 || _price4kWh==0.0 || _material_price==0.0){
        Toast.makeText(MainActivity.this,a+"" ,Toast.LENGTH_LONG).show();
    }



}

}

