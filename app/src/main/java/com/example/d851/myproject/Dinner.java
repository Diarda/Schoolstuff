package com.example.d851.myproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dinner extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);

        db=openOrCreateDatabase("Helper1", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT recomendations FROM FoodMenu WHERE type = '"+StartingPoint.currentUser.getType()+"' AND time = 'Dinner'", null);
        cursor.moveToFirst();
        String recomendations = cursor.getString(0);
        ((TextView)findViewById(R.id.dinner)).setText(recomendations);
    }
}
