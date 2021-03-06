package com.example.d851.myproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    Button btCalc, btBackCalc, btHistory;
    Spinner sp1;
    TextView tvSugar, tvTips;
    SQLiteDatabase db;
    ArrayList<Product> listofProduct;
    ArrayList<String> listofProductst;
    int AmountOfCarbs;
    String NameOfFood,idProduct;

    Double dailynorm;
    Double insulinportions;

    ArrayAdapter<String> adapterPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        db=openOrCreateDatabase("Helper1", Context.MODE_PRIVATE, null);

        double pw = Math.pow(StartingPoint.currentUser.getHeight(),2)*19;
        dailynorm = pw*0.7;
        insulinportions = 500/dailynorm;


        btCalc = findViewById(R.id.btCalculate);

        sp1=findViewById(R.id.sp);

        tvSugar=findViewById(R.id.tvHundred);
        tvTips=findViewById(R.id.tvTips);
        btBackCalc=findViewById(R.id.btBackCalc);
        btHistory=findViewById(R.id.btHistory);


        listofProduct=new ArrayList<>();
        listofProductst=new ArrayList<>();

        Cursor c= db.rawQuery("SELECT * FROM Product",null);

        while(c.moveToNext())
        {
            listofProduct.add(new Product(c.getString(0),c.getString(1),c.getInt(2)));
            listofProductst.add(c.getString(1));
        }

        adapterPro = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listofProductst);

        sp1.setAdapter(adapterPro);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
         {
             AmountOfCarbs = listofProduct.get(i).getAmountofCarbs();
             NameOfFood = listofProduct.get(i).getName();
             idProduct = listofProduct.get(i).getId();
             tvSugar.setText(""+AmountOfCarbs);
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });
        btCalc.setOnClickListener(this);
        btBackCalc.setOnClickListener(this);
        btHistory.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        if(view==btBackCalc)
        {
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("GO BACK");
          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
          {

              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  finish();
              }
          });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        if (view==btCalc)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = formatter.format(new Date());
            if(StartingPoint.currentUser.getType().equals("Type1"))
            {
                tvTips.setText(String.format("Since you ate %s you need to take %.2f units of insulin per 100g of product.", NameOfFood, (AmountOfCarbs/insulinportions)));
            }
            else tvTips.setText("You have diabetes type 2, there is no information about you needing shots of insulin after food ");
            db.execSQL("INSERT INTO UserProducts VALUES ("+StartingPoint.currentUser.getId()+",'"+idProduct+"','"+currentDate+"')");

        }
        if(btHistory==view)
        {
            Intent intent = new Intent(this,History.class);
            startActivity(intent);
        }

    }
}
