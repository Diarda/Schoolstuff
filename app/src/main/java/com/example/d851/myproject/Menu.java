package com.example.d851.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button btBreakf, btLunch, btDinner, btCalc, btSignOut, btData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        btBreakf=findViewById(R.id.btBreakfast);
        btLunch=findViewById(R.id.btLunch);
        btDinner=findViewById(R.id.btDinner);
        btCalc=findViewById(R.id.btCalc);
        btSignOut=findViewById(R.id.btSignOut);
        btData=findViewById(R.id.btData);

        btBreakf.setOnClickListener(this);
        btLunch.setOnClickListener(this);
        btDinner.setOnClickListener(this);
        btCalc.setOnClickListener(this);
        btSignOut.setOnClickListener(this);
        btData.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if(view==btBreakf)
        {
            Intent intent = new Intent(this,Breakfast.class);
            startActivity(intent);
        }
        if(view==btLunch)
        {
            Intent intent = new Intent(this,Lunch.class);
            startActivity(intent);
        }
        if(view==btDinner)
        {
            Intent intent = new Intent(this,Dinner.class);
            startActivity(intent);
        }
        if(view==btCalc)
        {
            Intent intent = new Intent(this,Calculator.class);
            startActivity(intent);
        }
        if(view==btSignOut)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
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
        if(view==btData)
        {
            Intent intent = new Intent(this,ChangeInfo.class);
            startActivity(intent);
        }

    }
}
