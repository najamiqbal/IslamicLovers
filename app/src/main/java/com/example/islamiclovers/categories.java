package com.example.islamiclovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class categories extends AppCompatActivity {
    Toolbar toolbar;
    ListView l1;
    String [] list_name={"حروف مفردات","حروف مرکبات","حروف مقطعات","حرکات ","تنوین ","مشق حرکات وتنوین","مدہ ؤ لین","سکون یعنی جزم","مشق سکون","تشدید","مشق تشدید"};
    String [] title ={"تحتی نمبر ۱","تحتی نمبر ۲","تحتی نمبر ۳","تحتی نمبر ۴","تحتی نمبر ۵","تحتی نمبر ۷ؐ ","تحتی نمبر ۸","تحتی نمبر ۹","تحتی نمبر ۱۰","تحتی نمبر ۱۱","تحتی نمبر ۱۲"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Islamic Lovers");

        l1= findViewById(R.id.list_1);
        Custom_view customAdapter=new Custom_view();
        l1.setAdapter(customAdapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent s = new Intent(categories.this, Moferdaat.class);
                    startActivity(s);
                }
                if(position==1)
                {
                    Intent s = new Intent(categories.this, morakbaat.class);
                    startActivity(s);
                }
                if(position==2)
                {
                    Intent s = new Intent(categories.this,Moqataat.class);
                    startActivity(s);
                }
                if(position==3)
                {
                    Intent s = new Intent(categories.this, harkaat.class);
                    startActivity(s);
                }
                if(position==4)
                {
                    Intent s = new Intent(categories.this, tanveen.class);
                    startActivity(s);
                }
                if(position==5)
                {
                    Intent s = new Intent(categories.this, harkaattanveen.class);
                    startActivity(s);
                }
            }
        });
    }
    class Custom_view extends BaseAdapter {

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.qaida_item,null);
            TextView textView1=(TextView) view.findViewById(R.id.textView2);
            TextView textView2=(TextView) view.findViewById(R.id.textView1);

            textView1.setText(title[position]);
            textView2.setText(list_name[position]);
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg="";
        switch (item.getItemId())
        {
            case R.id.search_bar:
                msg="Search";
                break;
            case R.id.Setting:
                msg="Setting";
                break;
            case R.id.Edit:
                msg="Edit";
                break;
            case R.id.Logout:
                msg="Logout";
                break;
        }

        Toast.makeText(this,msg +" Checked",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
}
