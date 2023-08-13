package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

public class Buy_Page extends AppCompatActivity {

ImageView buy_img,about,shop ;
TextView Name, Title,Price;
Button button;



    public static String PURL ="" ;
   public static String NAME ="" ;
    public static String TITLE ="" ;
    public static String IMG ="" ;
    public static String PRICE ="" ;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);
        buy_img=findViewById(R.id.buy_img);
        Name=findViewById(R.id.Name);
        Title=findViewById(R.id.Title);
        button=findViewById(R.id.button);
        Price=findViewById(R.id.Price);
        about=findViewById(R.id.about);
        shop=findViewById(R.id.shop);

        Name.setText(NAME);
        Title.setText(TITLE);
        Price.setText(PRICE);
        Picasso.get().load(IMG).into(buy_img);



        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        Price.setBackgroundColor(color);

        Random rnd2 = new Random();
        int color2 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        Name.setBackgroundColor(color);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Buy_Page.this,Web_Buy.class);
                startActivity(intent);
                Web_Buy.URL=PURL ;




            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Buy_Page.this,Shop_Now.class);
                startActivity(intent);





            }
        });













    }


}