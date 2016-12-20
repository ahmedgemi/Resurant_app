package com.example.ahmed.resturant_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;

    ArrayList<Food> list = new  ArrayList<Food>();

    double total =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);



        list.add(new Food("Pizza",25,R.drawable.img3));
        list.add(new Food("Fish",14,R.drawable.img4));
        list.add(new Food("Sandwich",5,R.drawable.img5));
        list.add(new Food("Soap",10,R.drawable.img7));

        myAdapter adapter = new myAdapter(list);


        listView.setAdapter(adapter);


    }

    public void  order(View v){


        for (int i =0 ; i < listView.getCount() ; i++){

           View view = listView.getChildAt(i);

            CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);
            EditText edit_count = (EditText) view.findViewById(R.id.editText);

            if (check.isChecked()){

                String str = edit_count.getText().toString();

                int count = Integer.parseInt(str);

                total += (double) (count * list.get(i).price) ;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Total Price");

        builder.setMessage("Total = " + String.valueOf(total));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this,"Order Saved" ,Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }


    public class myAdapter extends BaseAdapter{


        ArrayList<Food> data = new  ArrayList<Food>();


        myAdapter( ArrayList<Food> list) {

            data = list ;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Food getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();

            view = inflater.inflate(R.layout.listview_item,viewGroup,false);

            TextView txt_name = (TextView) view.findViewById(R.id.textView);
            TextView txt_price = (TextView) view.findViewById(R.id.textView2);

            ImageView image = (ImageView) view.findViewById(R.id.imageView);


            txt_name.setText(data.get(i).name);

            txt_price.setText(String.valueOf(data.get(i).price) + " EGP");

            image.setImageResource(data.get(i).pic_id);


            return view;
        }
    }



    public  class Food {

        String name ;
        double price;
        int pic_id;

        Food(String n , double p , int id){

            name = n ;
            price = p;
            pic_id = id;
        }

    }


}
