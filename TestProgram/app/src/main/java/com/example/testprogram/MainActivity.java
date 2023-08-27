package com.example.testprogram;
import static java.awt.font.TextAttribute.WEIGHT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDBHelper;
    ArrayList<HashMap<String, Object>> clients = new ArrayList<HashMap<String, Object>>();
    HashMap<String, Object> client;

    private String[] arr = new String[]{"bulbasaur", "ivysaur", "venusaur", "charmander",
            "charmeleon", "charizard", "squirtle", "wartortle", "blastoise", "caterpie",
                "metapod", "butterfree", "weedle", "kakuna", "beedrill", "pidgey", "pidgeotto",
            "pidgeot", "rattata", "raticate"};
    private ListView listView;

    //DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.name_item, R.id.pokemon_name, arr);


        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        Cursor cursor = mDb.rawQuery("SELECT * FROM pokemons", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            client = new HashMap<String, Object>();
            client.put("name",  cursor.getString(0));
            clients.add(client);
            cursor.moveToNext();
        }
        cursor.close();
        String[] from = {"name"};
        int[] to = { R.id.pokemon_name};
        SimpleAdapter adapter = new SimpleAdapter(this, clients, R.layout.name_item, from, to);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startNewActivity((String)listView.getItemAtPosition(i));
            }
        });

    }

    public void startNewActivity(String name){
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("name", name);
            startActivity(intent);
    }

}