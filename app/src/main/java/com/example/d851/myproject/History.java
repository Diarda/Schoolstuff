package com.example.d851.myproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tvPrn,tvDate;
    ListView listView;
    ArrayAdapter<Object> adapter;
    ArrayList<List> arrayList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tvDate.findViewById(R.id.tvDate);
        tvPrn.findViewById(R.id.tvPrn);
        listView.findViewById(R.id.list);
        db=openOrCreateDatabase("Helper1", Context.MODE_PRIVATE, null);

        List<Pair<String,String>> list = new ArrayList<>();
        int ID = StartingPoint.currentUser.getId();
        Cursor c = db.rawQuery("SELECT Product.name, UserProducts.date " +
                "FROM Product INNER JOIN UserProducts ON Product.idPro = UserProducts.idProducts  " +
                "WHERE UserProducs.idUser = " + ID + "", null);
        while(c.moveToNext())
        {
           list.add(new Pair(c.getString(1),c.getString(2)));
        }

        listView.setAdapter(new HistoryAdapter(this,R.layout.history_edit,list));




    }

}
