package com.example.testprogram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private String[] arr = new String[]{"bulbasaur", "ivysaur", "venusaur", "charmander",
            "charmeleon", "charizard", "squirtle", "wartortle", "blastoise", "caterpie",
            "metapod", "butterfree", "weedle", "kakuna", "beedrill", "pidgey", "pidgeotto",
            "pidgeot", "rattata", "raticate"};
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.name_item, R.id.pokemon_name, arr);
        listView.setAdapter(arrayAdapter);
    }

    public void startNewActivity(View v){
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
    }

}