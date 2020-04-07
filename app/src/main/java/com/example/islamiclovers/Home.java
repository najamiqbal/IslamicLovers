package com.example.islamiclovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Util;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    SliderView sliderView;
    List<ImageSliderModel> imageSliderModelList;
    BoomMenuButton bmb;
    Integer[] image1 = {R.drawable.tajweedimage, R.drawable.qirat, R.drawable.faraiz, R.drawable.sunnah,
            R.drawable.prayertime, R.drawable.quiz};
    String[] name = {"Tajweed", "Qirat", "Faraiz", "Sunnah", "Prayer_time", "Quiz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageSliderModelList = new ArrayList<>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Islamic Lovers");
        sliderView = findViewById(R.id.imageSlider);
        imageSliderModelList.add(new ImageSliderModel(R.drawable.mm5));
        imageSliderModelList.add(new ImageSliderModel(R.drawable.img6));
        imageSliderModelList.add(new ImageSliderModel(R.drawable.imm1));
        imageSliderModelList.add(new ImageSliderModel(R.drawable.img7));
        imageSliderModelList.add(new ImageSliderModel(R.drawable.imm3));
        sliderView.setSliderAdapter(new ImageSliderAdapter(this, imageSliderModelList));
        bmb = findViewById(R.id.bmb1);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .isRound(false)
                    .buttonCornerRadius(20)
                    .maxLines(4)
                    .rippleEffect(true)
                    .pieceColor(Color.GREEN)
                    .buttonRadius(Util.dp2px(40))
                    .textSize(15)
                    .highlightedColor(Color.BLUE)
                    .normalTextColor(Color.BLACK)
                    .highlightedText(name[i])
                    .normalImageRes(image1[i])
                    .normalText(name[i]);
            bmb.addBuilder(builder);

        }
        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                //Toast.makeText(Home.this, " button Clicked " + index, Toast.LENGTH_SHORT).show();
                // If you have implement listeners for boom-buttons in builders,
                // then you shouldn't add any listener here for duplicate callbacks.
                if (index < bmb.getPiecePlaceEnum().pieceNumber()) {
                    switch (index) {
                        case 0:
                            Intent intent01 = new Intent(Home.this, categories.class);
                            startActivity(intent01);
                            break;
                        case 1:
                            Intent i1 = new Intent(Home.this, surah_categories.class);
                            startActivity(i1);
                            break;
                        case 2:
                            Intent i2 = new Intent(Home.this, faraizlist.class);
                            startActivity(i2);
                            break;
                        case 3:
                            Intent i3 = new Intent(Home.this, sunnahlist.class);
                            startActivity(i3);
                            break;
                        case 4:
                            Intent i4 = new Intent(Home.this, SalatTimeActivity.class);
                             startActivity(i4);
                            break;
                        case 5:
                            Intent i5 = new Intent(Home.this, playy.class);
                            startActivity(i5);
                            break;
                        default:
                    }
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.search_bar:
                msg = "Search";
                break;
            case R.id.Setting:
                msg = "Setting";
                break;
            case R.id.Edit:
                msg = "Edit";
                break;
            case R.id.Logout:
                msg = "Logout";
                break;
        }

        Toast.makeText(this, msg + " Checked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
}

