package com.example.islamiclovers;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SalatTimeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_fajr,tv_dhoher,tv_asr,tv_magrib,tv_isha,tv_location;
    private ProgressDialog pDialog;
    private AlertDialog dialogBuilder;
    SharedPreferences prefs;
    GPSTracker gps;
    ImageView im_fajr,im_dhuhr,im_asr,im_magrib,im_isha;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salat_time_activity);
        initialization();
    }

    private void initialization() {
        toolbar = findViewById(R.id.toolbar_provider);
        toolbar.setTitle("Prayer time");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pDialog = new ProgressDialog(SalatTimeActivity.this);
        pDialog.setCancelable(false);
        tv_fajr=findViewById(R.id.tv_fajr);
        tv_dhoher=findViewById(R.id.tv_dhuhr);
        tv_asr=findViewById(R.id.tv_asr);
        tv_magrib=findViewById(R.id.tv_maghrib);
        tv_isha=findViewById(R.id.tv_isha);
        tv_location=findViewById(R.id.card_location);
        im_fajr=findViewById(R.id.im_fajr);
        im_dhuhr=findViewById(R.id.im_dhuhr);
        im_asr=findViewById(R.id.im_asr);
        im_magrib=findViewById(R.id.im_magrib);
        im_isha=findViewById(R.id.im_isha);
        gps = new GPSTracker(this);
        prefs = getSharedPreferences("", MODE_PRIVATE);
        setupCompass();
        //getPrayerTime();
    }

    private void getPrayerTime(String city) {
        String url="https://muslimsalat.com/"+city+".json?key=f4d9a1aa4080edf8892b04d70ea1eaff";
        pDialog.setMessage("Feteching data....");
        pDialog.show();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                //Toast.makeText(SalatTimeActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    tv_fajr.setText(response.getJSONArray("items").getJSONObject(0).get("fajr").toString());
                    tv_dhoher.setText(response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString());
                    tv_asr.setText(response.getJSONArray("items").getJSONObject(0).get("asr").toString());
                    tv_magrib.setText(response.getJSONArray("items").getJSONObject(0).get("maghrib").toString());
                    tv_isha.setText(response.getJSONArray("items").getJSONObject(0).get("isha").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
            }
        });
        VolleyRequestsent.getInstance().addRequestQueue(jsonObjectRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu item selected
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupCompass() {
        Boolean permission_granted = GetBoolean("permission_granted");
        if (permission_granted) {
            fetch_GPS();
            //getBearing();
        } else {
            //text_atas.setText(getResources().getString(R.string.msg_permission_not_granted_yet));
            //text_bawah.setText(getResources().getString(R.string.msg_permission_not_granted_yet));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    SaveBoolean("permission_granted", true);
                    fetch_GPS();

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_permission_required), Toast.LENGTH_LONG).show();
                    //finish();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void SaveBoolean(String Judul, Boolean bbb) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(Judul, bbb);
        edit.apply();
    }

    public Boolean GetBoolean(String Judul) {
        Boolean result = prefs.getBoolean(Judul, false);
        return result;
    }


    public void fetch_GPS() {
        double result = 0;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getSubLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                tv_location.setText( city+","+country );
                getPrayerTime(city);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //text_bawah.setText(getResources().getString(R.string.your_location) + "\nLat: " + latitude + " Long: " + longitude);

        } else {
            gps.showSettingsAlert();
            //text_bawah.setText(getResources().getString(R.string.pls_enable_location));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_fajr:
                break;
            case R.id.im_dhuhr:
                break;
            case R.id.im_asr:
                break;
            case R.id.im_magrib:
                break;
            case R.id.im_isha:
                break;

        }
    }
}
