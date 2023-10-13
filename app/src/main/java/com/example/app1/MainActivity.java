package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import  android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Lifecycle onStart", Toast.LENGTH_SHORT).show();
    }

    public void hitung(View view) {
        Intent hitung= new Intent(MainActivity.this, Hitung.class);
        startActivity(hitung);
    }
}