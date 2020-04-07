package com.example.islamiclovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class surah_categories extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_categories);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }
    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalL = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalL == 0) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 1) {
                        Intent s = new Intent(surah_categories.this, surah_01.class);
                        startActivity(s);
                    }
                    if (finalL == 2) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 3) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 4) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 5) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 6) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 7) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 8) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                    if (finalL == 9) {
                        Toast.makeText(surah_categories.this, "HEllO " + finalL, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
