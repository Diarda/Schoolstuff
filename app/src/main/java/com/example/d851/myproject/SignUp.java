package com.example.d851.myproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etHeight, etAge, etPassword;



    RadioGroup rg;

    Button bt, btBack;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        db=openOrCreateDatabase("Helper1", Context.MODE_PRIVATE, null);


        etName=findViewById(R.id.etName);
        etHeight=findViewById(R.id.etHeight);
        etAge=findViewById(R.id.etAge);
        etPassword=findViewById(R.id.etPassword);
        bt=findViewById(R.id.btSignUp2);
        btBack=findViewById(R.id.btBack);
        bt.setOnClickListener(this);
        btBack.setOnClickListener(this);

        rg=findViewById(R.id.rg);

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

            String sql = "INSERT INTO User(name, age, height, type, password ) VALUES('" + name + "'," + age + "," + height + ",'" + rb.getText().toString() + "','" + password + "')";
            db.execSQL(sql);
            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE name = '"+name+"' AND password='"+password+"'",null);
            cursor.moveToFirst();
            int id =cursor.getInt(0);
            finish();
        }
    }
}
