package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.pokedex.Pokedex;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent a = new Intent(MainActivity.this, Menu.class);
                startActivity(a);

            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent b = new Intent(MainActivity.this,Pokedex.class);//cambiar a la activity de info
                startActivity(b);
            }
        });
    }

}