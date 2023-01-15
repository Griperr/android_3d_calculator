package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Settings_class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);




    }


    @Override
    protected void onStart() {
        super.onStart();

        EditText printer_name =(EditText) findViewById(R.id.PrinterName);
        EditText price4kWh = (EditText) findViewById(R.id.PriceKWH);
        EditText material_name = (EditText) findViewById(R.id.MaterialName);
        EditText material_price =(EditText) findViewById(R.id.PriceMaterial);
        EditText currency =(EditText) findViewById(R.id.CurrencySetting);
        EditText printer_power =(EditText) findViewById(R.id.PrinterPower);
        EditText profit =(EditText) findViewById(R.id.Profit);
        EditText tax =(EditText) findViewById(R.id.Tax);






        SQLiteDatabase settings_db =openOrCreateDatabase("settings.db", Context.MODE_PRIVATE,
                null);





        String columns [] ={"id","Printer_name","Price_4_kWh","Material_name","Price_of_material"
                ,"Currency","Printer_power","Profit_margin", "Tax"}; // tu wczytuje wartości z
        // tabeli
        Cursor k =settings_db.query("Settings",columns,null,null,null,null,null);
        k.moveToFirst();
        printer_name.setText(k.getString(1));
        price4kWh.setText(k.getString(2));
        material_name.setText(k.getString(3));
        material_price.setText(k.getString(4));
        currency.setText(k.getString(5));
        printer_power.setText(k.getString(6));
        profit.setText(k.getString(7));
        tax.setText(k.getString(8));






        settings_db.close();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void GoToMain (View WWW){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }

    public static String _printer_name, _material_name, _currency;
    public static float _price4kWh, _material_price, _printer_power, _profit, _tax;









    @SuppressLint("SetTextI18n")
    public void Save (View WWW) {
        EditText printer_name =(EditText) findViewById(R.id.PrinterName);
        EditText price4kWh = (EditText) findViewById(R.id.PriceKWH);
        EditText material_name = (EditText) findViewById(R.id.MaterialName);
        EditText material_price =(EditText) findViewById(R.id.PriceMaterial);
        EditText currency =(EditText) findViewById(R.id.CurrencySetting);
        EditText printer_power =(EditText) findViewById(R.id.PrinterPower);
        EditText profit =(EditText) findViewById(R.id.Profit);
        EditText tax =(EditText) findViewById(R.id.Tax);



        try {
            _printer_name =  printer_name.getText().toString();
            _material_name=  material_name.getText().toString();
            _currency=  currency.getText().toString();
            _price4kWh=  Float.parseFloat(price4kWh.getText().toString());
            _material_price=  Float.parseFloat(material_price.getText().toString());
            _printer_power=  Float.parseFloat(printer_power.getText().toString());
            _profit=  Float.parseFloat(profit.getText().toString());
            _tax=  Float.parseFloat(tax.getText().toString());


            SQLiteDatabase settings_db =openOrCreateDatabase("settings.db", Context.MODE_PRIVATE,
                    null);


            ContentValues cv = new ContentValues(); // to są wartości do zapisania w tabeli

            cv.put("Printer_name",_printer_name);
            cv.put("Price_4_kWh",_price4kWh);
            cv.put("Material_name", _material_name);
            cv.put("Price_of_material", _material_price);
            cv.put("Currency", _currency);
            cv.put("Printer_power", _printer_power);
            cv.put("Profit_margin", _profit);
            cv.put("Tax",_tax);

            settings_db.update("Settings",cv,"id = ?",new String[]{"0"}); // tu updatuje wpis
            settings_db.close();


        }
        catch (Exception a){
            //
        }





       /* printer_name.setText(_printer_name);
        price4kWh.setText(Float.toString(_price4kWh));
        material_name.setText(_material_name);
        material_price.setText(Float.toString(_material_price));
        currency.setText(_currency);
        printer_power.setText(Float.toString(_printer_power));
        profit.setText(Float.toString(_profit));
        tax.setText(Float.toString(_tax));
*/


    }



}
