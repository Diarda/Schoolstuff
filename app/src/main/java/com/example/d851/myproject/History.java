package com.example.d851.myproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class History extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tvHist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        db=openOrCreateDatabase("Helper1", Context.MODE_PRIVATE, null);
        tvHist = findViewById(R.id.tvHist);


    }
}
