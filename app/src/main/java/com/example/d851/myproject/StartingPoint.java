package com.example.d851.myproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartingPoint extends AppCompatActivity implements View.OnClickListener {
    Button bt1,bt2;
    SQLiteDatabase db;
    EditText etNameE,etPassE;
    public static UserInformation currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startingpoint);

        db=openOrCreateDatabase("Helper12", Context.MODE_PRIVATE, null);

        createDatabase();

        bt1=findViewById(R.id.btSignUp);
        bt2=findViewById(R.id.btSignIn);

        etNameE=findViewById(R.id.etExisingName);
        etPassE=findViewById(R.id.etExisingPass);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==bt1)
        {
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);

        }
      if(view==bt2)
        {
            String name;
            String pass;

            name = etNameE.getText().toString();
            pass=etPassE.getText().toString();

            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE name='"+name+"' AND password='"+pass+"'",null);
            cursor.moveToFirst();
            if(cursor.getCount()>0)
            {
                currentUser = new UserInformation(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5));
                Intent intent = new Intent(this,Menu.class);
                startActivity(intent);
            }

         else {
                Toast.makeText(this, "User don't exists", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createDatabase() {

        db.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INT, height DOUBLE, type TEXT , password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Product(idPro TEXT PRIMARY KEY, name TEXT,  amountofCarbs INT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS FoodMenu(type TEXT,time TEXT, recomendations TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS UserProducts(idUser INTEGER ,idProducts TEXT ,date INT, FOREIGN KEY(idUser) REFERENCES User(id), FOREIGN KEY(idProducts) REFERENCES Product(id))");


        Cursor cursor = db.rawQuery("SELECT * FROM FoodMenu", null);
        if(cursor.getCount()>0)
            return;

        db.execSQL("INSERT INTO FoodMenu VALUES ('Type1','Breakfast', '" + getString(R.string.recommendations1Break) + "')");
        db.execSQL("INSERT INTO FoodMenu VALUES ('Type2','Breakfast', '" + getString(R.string.recommendations2Break) + "')");

        db.execSQL("INSERT INTO FoodMenu VALUES ('Type1','Lunch', '" + getString(R.string.recommendations1Lunch) + "')");
        db.execSQL("INSERT INTO FoodMenu VALUES ('Type2','Lunch', '" + getString(R.string.recommendations2Lunch) + "')");

        db.execSQL("INSERT INTO FoodMenu VALUES ('Type1','Dinner', '" + getString(R.string.recommendations1Dinner) + "')");
        db.execSQL("INSERT INTO FoodMenu VALUES ('Type2','Dinner', '" + getString(R.string.recommendations2Dinner) + "')");

        db.execSQL("INSERT INTO Product VALUES ('01','Apple',14)");
        db.execSQL("INSERT INTO Product VALUES ('02','Banana',23)");
        db.execSQL("INSERT INTO Product VALUES ('03','Peach',10)");
        db.execSQL("INSERT INTO Product VALUES ('04','Grape',17)");
        db.execSQL("INSERT INTO Product VALUES ('05','Orange',12)");
        db.execSQL("INSERT INTO Product VALUES ('06','Boiled Egg',1)");
        db.execSQL("INSERT INTO Product VALUES ('07','Boiled potato',17)");
        db.execSQL("INSERT INTO Product VALUES ('08','Boiled Chiken without skin',0)");










    }
}
