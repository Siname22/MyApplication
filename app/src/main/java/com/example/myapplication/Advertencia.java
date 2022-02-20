package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Advertencia extends AppCompatActivity {
    Button info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertencia);
        info = findViewById(R.id.button);
        info.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)
            {

                Intent b = new Intent(Advertencia.this, Menu.class);//cambiar a la activity de info
                startActivity(b);
            }
        });
    }

}