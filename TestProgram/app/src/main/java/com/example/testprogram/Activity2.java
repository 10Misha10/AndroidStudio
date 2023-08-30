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
import android.widget.Toast;

import java.io.IOException;

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
        setImage(image, name);

        mDBHelper = new DatabaseHelper(this);
        checkDB(mDBHelper);

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
    public void checkDB(DatabaseHelper mDBHelper){
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            Toast.makeText(this, "Data Not Loading", Toast.LENGTH_LONG).show();
            throw mSQLException;
        }
    }
    public void setImage(ImageView image, String name){
        int imageResource = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
        if (imageResource != 0) {
            image.setImageResource(imageResource);
        }
    }
    public void startNewActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}