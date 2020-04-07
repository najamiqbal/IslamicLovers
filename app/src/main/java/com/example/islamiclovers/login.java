package com.example.islamiclovers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText e1,e2;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= e1.getText().toString();
                String pass= e2.getText().toString();
                Boolean checkemailpassword=db.emailpassword(email,pass);
                if(checkemailpassword==true){
                    Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_SHORT).show();
                    Intent s = new Intent(login.this,Home.class);
                    startActivity(s);}
                else
                    Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
