package com.example.islamiclovers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class suunah_farz_categoryy extends AppCompatActivity {
    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suunah_farz_categoryy);
        Button b1=(Button) findViewById(R.id.button);
        Button b2=(Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensunnahlist();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openfaraizlist();
            }
        });
    }
    public void opensunnahlist(){
        Intent intent=new Intent(this,sunnahlist.class);
        startActivity(intent);
    }
    public void openfaraizlist(){
        Intent intent=new Intent(this,faraizlist.class);
        startActivity(intent);
    }
}
