package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Settings_class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);




    }

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







        _printer_name =  printer_name.getText().toString();
        _material_name=  material_name.getText().toString();
        _currency=  currency.getText().toString();
        _price4kWh=  Float.parseFloat(price4kWh.getText().toString());
        _material_price=  Float.parseFloat(material_price.getText().toString());
        _printer_power=  Float.parseFloat(printer_power.getText().toString());
        _profit=  Float.parseFloat(profit.getText().toString());
        _tax=  Float.parseFloat(tax.getText().toString());

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
