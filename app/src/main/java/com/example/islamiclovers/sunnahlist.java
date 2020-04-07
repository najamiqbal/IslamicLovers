package com.example.islamiclovers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class sunnahlist extends AppCompatActivity {
    ListView simpleList;
    String suunahlist[] = {"سونے کی سنت" , "اٹھنے کی سنت", "پانی پینے کی سنت", "بیت الخلا میں جانے کی سنت ", "بیت الخلا سے باہر آنے کی سنت" ,  "کپڑے پہننے کی سنت ","ناخن تراشنے کی سنت "  ,  "سرمہ ڈالنے کی سنت ",  "مسواک کی سنت ","تیل لگانے کی سنت "   };
    int item[] = {R.drawable.sleep, R.drawable.time, R.drawable.drink, R.drawable.enter, R.drawable.exit, R.drawable.dress, R.drawable.nail, R.drawable.surma, R.drawable.miswak, R.drawable.oil};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunnahlist);

            simpleList = (ListView) findViewById(R.id.sunnahListView);
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
                textView.setText(suunahlist[position]);
                return view;
            }
        }
}
