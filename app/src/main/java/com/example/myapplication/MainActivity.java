package com.example.myapplication;

import static com.example.myapplication.Settings_class._currency;
import static com.example.myapplication.Settings_class._material_name;
import static com.example.myapplication.Settings_class._price4kWh;
import static com.example.myapplication.Settings_class._material_price;
import static com.example.myapplication.Settings_class._printer_name;
import static com.example.myapplication.Settings_class._printer_power;
import static com.example.myapplication.Settings_class._profit;
import static com.example.myapplication.Settings_class._tax;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase settings_db =openOrCreateDatabase("settings.db", Context.MODE_PRIVATE,null);
        settings_db.execSQL("CREATE TABLE IF NOT EXISTS 'Settings' (id INTEGER PRIMARY KEY CHECK " +
                "(id = 0 )" +
                ", " +
                "Printer_name STRING, Price_4_kWh STRING,  Material_name STRING, " +
                "Price_of_material, Currency STRING, Printer_power STRING, Profit_margin STRING, " +
                "Tax STRING" +
                ")");

        ContentValues cv = new ContentValues();

        cv.put("Printer_name","'"+_printer_name+"'");
        cv.put("Price_4_kWh",_price4kWh);
        cv.put("Material_name", "'"+_material_name+"'");
        cv.put("Price_of_material", _material_price);
        cv.put("Currency", "'"+_currency+"'");
        cv.put("Printer_power", _printer_power);
        cv.put("Profit_margin", _profit);
        cv.put("Tax",_tax);
       // settings_db.insert("Settings",null,cv);

         try {

            /* settings_db.execSQL("INSERT INTO 'Settings' VALUES(0,"+"0," +"0,"+"0,"+"0,"+"0,"+
                     "0,"+"0,"+
                     "0)"); // "0"*/

            // settings_db.execSQL("INSERT INTO 'Settings' VALUES(0,"+"0"+"0,0,0,0,0,0,0,0)"); //
             // "0"


             //settings_db.update("Settings",cv,"id=0",null);
            /* settings_db.execSQL("UPDATE 'Setings' SET Printer_name = '"+ _printer_name +"' , " +
                     "Price_4_kWh =  '"+ _price4kWh +"', " +
                     " Material_name = '"+ _material_name +"', Price_of_matrial = '"+ _material_price +"', Currency = '"+ _currency +"', Printer_power = '"+ _printer_power +"', Profit_margin = '"+ _profit +"', Tax = '"+ _tax +"'   ");
             // '"+ _tax +"'*/
         }
         catch (Exception a) {
             //
         }
         settings_db.close();


         }

    @Override
    protected void onStart() {
        super.onStart();


        SQLiteDatabase settings_db =openOrCreateDatabase("settings.db", Context.MODE_PRIVATE,null);
        settings_db.execSQL("CREATE TABLE IF NOT EXISTS 'Settings' (id INTEGER PRIMARY KEY CHECK " +
                "(id = 0 )" +
                ", " +
                "Printer_name STRING, Price_4_kWh STRING,  Material_name STRING, " +
                "Price_of_material, Currency STRING, Printer_power STRING, Profit_margin STRING, " +
                "Tax STRING" +
                ")");

        ContentValues cv = new ContentValues();

        cv.put("Printer_name","'"+_printer_name+"'");
        cv.put("Price_4_kWh",_price4kWh);
        cv.put("Material_name", "'"+_material_name+"'");
        cv.put("Price_of_material", _material_price);
        cv.put("Currency", "'"+_currency+"'");
        cv.put("Printer_power", _printer_power);
        cv.put("Profit_margin", _profit);
        cv.put("Tax",_tax);
        //settings_db.insert("Settings",null,cv);
        settings_db.update("Settings",cv,"id =?",null);

        try {

            /* settings_db.execSQL("INSERT INTO 'Settings' VALUES(0,"+"0," +"0,"+"0,"+"0,"+"0,"+
                     "0,"+"0,"+
                     "0)"); // "0"*/

            // settings_db.execSQL("INSERT INTO 'Settings' VALUES(0,"+"0"+"0,0,0,0,0,0,0,0)"); //
            // "0"


            //settings_db.update("Settings",cv,"id=0",null);
            /* settings_db.execSQL("UPDATE 'Setings' SET Printer_name = '"+ _printer_name +"' , " +
                     "Price_4_kWh =  '"+ _price4kWh +"', " +
                     " Material_name = '"+ _material_name +"', Price_of_matrial = '"+ _material_price +"', Currency = '"+ _currency +"', Printer_power = '"+ _printer_power +"', Profit_margin = '"+ _profit +"', Tax = '"+ _tax +"'   ");
             // '"+ _tax +"'*/
        }
        catch (Exception a) {
            //
        }
        settings_db.close();
    }

         /* String _printer_name = null, _material_name = null, _currency = null;
         float _price4kWh = 0, _material_price  = 0, _printer_power  = 0, _profit  = 0, _tax  = 0;

         _printer_name=  Settings_class._printer_name;*/



    float cost;

    public void GoToSettings (View WWW){
        Intent screen = new Intent(this,Settings_class.class);
        startActivity(screen);


    }

    public void Test (View WWW){
        //Calc_cost();
        //Calc_profit();

    }

    public void Calc_cost(View WWW){

        try {
            TextView costText = (TextView) findViewById(R.id.Cost);
            EditText printTimeText = (EditText) findViewById(R.id.PrintTime);
            EditText materialWeightText = (EditText) findViewById(R.id.PrintWeight);
            float time = Float.parseFloat(printTimeText.getText().toString());
            float costOfEnergy = ((time*_printer_power)/1000)*_price4kWh;
            float weightOfMaterial = Float.parseFloat(materialWeightText.getText().toString());
            float costOfMaterial = weightOfMaterial*(_material_price/1000);
            float totalCost = costOfEnergy+costOfMaterial;
            String totalCostSTR = Float.toString(totalCost);
            costText.setText(totalCostSTR);
            //-----------------------------------------------


            TextView profitText = (TextView) findViewById(R.id.CostMargin);
            float totalProfit = totalCost + totalCost*(_profit/100);
            String profitSTR = Float.toString(totalProfit);
            profitText.setText(profitSTR);

            //----------------------------------------------------

            TextView taxText = (TextView) findViewById((R.id.Price));
            float totalTax = totalProfit + totalProfit*(_tax/100);
            String taxSTR = Float.toString(totalTax);
            taxText.setText(taxSTR);



        }

         catch (Exception e) {
            e.getCause();



          /*  _profit=0;
            _tax=0;
            _material_price=0;
            _currency=null;
            _price4kWh=0;
            _printer_power=0;
            _material_name=null;
            _printer_name=null;*/
        }

       /* finally {
            String a ="Some fields not filled";



            Toast.makeText(MainActivity.this,a+"" ,Toast.LENGTH_LONG).show();

        }*/


    }



}