package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.pokedex.Pokedex;

public class MainActivity extends AppCompatActivity {

    Button info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.button);
        info.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)
            {

                Intent b = new Intent(MainActivity.this, Advertencia.class);//cambiar a la activity de info
                startActivity(b);
            }
        });
    }
}

