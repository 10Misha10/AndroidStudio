package com.example.testprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] arr = new String[]{"bulbasaur", "ivysaur", "venusaur", "charmander",
            "charmeleon", "charizard", "squirtle", "wartortle", "blastoise", "caterpie",
            "metapod", "butterfree", "weedle", "kakuna", "beedrill", "pidgey", "pidgeotto",
            "pidgeot", "rattata", "raticate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isConnectedToInternet();

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.name_item, R.id.pokemon_name, arr);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startNewActivity((String)listView.getItemAtPosition(i));
            }
        });
    }
    private void isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected())) {
            Toast.makeText(this, "Offline mode", Toast.LENGTH_SHORT).show();
        }
    }
    public void startNewActivity(String name){
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("name", name);
            startActivity(intent);
    }

}