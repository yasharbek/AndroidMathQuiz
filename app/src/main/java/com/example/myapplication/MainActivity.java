package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    public static final int COUNTER_ACTIVITY_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.difficulty,  // Create a string array resource in res/values/strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
    }

    public void startButton(View v){
        Intent i = new Intent(this, CounterActivity.class);
        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        String selectedDifficulty = difficultySpinner.getSelectedItem().toString();
        i.putExtra("Difficulty", selectedDifficulty);
        startActivity(i);
    }
}