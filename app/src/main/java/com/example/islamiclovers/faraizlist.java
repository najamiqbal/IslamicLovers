package com.example.islamiclovers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class faraizlist extends AppCompatActivity {
    ListView simpleList;

    String farzlist[] = {"وضو کے فرائض " , "غسل  کے فرائض " , "تیمم کے فرائض ","نماز کے فرائض "};
    int item[] = {R.drawable.wudu, R.drawable.shower, R.drawable.tayamum, R.drawable.prayer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faraizlist);
        simpleList = (ListView) findViewById(R.id.faraizlistview);
        CustomAdapter customAdapter=new CustomAdapter();
        simpleList.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return item.length;
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
            View view=getLayoutInflater().inflate(R.layout.custom_layout,null);
            ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
            TextView textView=(TextView) view.findViewById(R.id.textView);

            imageView.setImageResource(item[position]);
            textView.setText(farzlist[position]);
            return view;
        }
    }
}


