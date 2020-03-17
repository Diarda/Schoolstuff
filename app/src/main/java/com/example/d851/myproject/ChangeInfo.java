package com.example.d851.myproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeInfo extends AppCompatActivity implements View.OnClickListener
{
    EditText etName, etHeight, etAge, etPassword;

    RadioGroup rg;
    RadioButton rb1,rb2;

    Button bt, btBack;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        db=openOrCreateDatabase("Helper12", Context.MODE_PRIVATE, null);


        etName=findViewById(R.id.etName);
        etHeight=findViewById(R.id.etHeight);
        etAge=findViewById(R.id.etAge);
        etPassword=findViewById(R.id.etPassword);
        bt=findViewById(R.id.btSignUp2);
        btBack=findViewById(R.id.btBack);
        rb1=findViewById(R.id.rbType1);
        rb2=findViewById(R.id.rbType2);


        bt.setOnClickListener(this);
        btBack.setOnClickListener(this);

        rg=findViewById(R.id.rg);

        etName.setText(StartingPoint.currentUser.getName());
        etHeight.setText(String.valueOf(StartingPoint.currentUser.getHeight()));
        etAge.setText(String.valueOf(StartingPoint.currentUser.getAge()));
        etPassword.setText(StartingPoint.currentUser.getPassword());
        if(StartingPoint.currentUser.getType().equals(rb1.getText().toString())) rb1.setActivated(true);
        else rb2.setActivated(true);

    }

    @Override
    public void onClick(View view) {
        if(view==btBack)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to go back?");
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
        if(view==bt)
        {

            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            double height = Double.parseDouble(etHeight.getText().toString());
            String password = etPassword.getText().toString();

            int num = rg.getCheckedRadioButtonId();

            RadioButton rb = findViewById(num);

            String sql = "UPDATE User" +
                    " SET " +
                    "name = '" + name + "', age = "+age +", height = "+ height +"," +
                    " type = '"+rb.getText().toString()+"', password = '"+password+"'" +
                    " WHERE id = "+StartingPoint.currentUser.getId();
            StartingPoint.currentUser.setName(name);
            StartingPoint.currentUser.setAge(age);
            StartingPoint.currentUser.setHeight(height);
            StartingPoint.currentUser.setType(rb.getText().toString());
            StartingPoint.currentUser.setPassword(password);
            db.execSQL(sql);
            finish();
        }
    }
}
