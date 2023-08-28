package com.example.testprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.LinkedList;

public class Activity2 extends AppCompatActivity {
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    TextView textView;
    TextView textView_type;
    TextView textView_weight;
    TextView textView_height;

    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView = findViewById(R.id.name);
        textView_type = findViewById(R.id.types_data);
        textView_weight = findViewById(R.id.weight_data);
        textView_height = findViewById(R.id.height_data);
        image = findViewById(R.id.imageView);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        textView.setText(name);

        int imageResource = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());

        if (imageResource != 0) {
            image.setImageResource(imageResource);
        }
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
            if(name.equals(cursor.getString(0))){
                textView_type.setText(cursor.getString(1));
                textView_weight.setText(cursor.getString(2));
                textView_height.setText(cursor.getString(3));
                break;
            }
            cursor.moveToNext();
        }
        cursor.close();

    }
    public void startNewActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}