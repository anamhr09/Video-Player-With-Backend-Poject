package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Shop_Now extends AppCompatActivity {


    ImageView back;
    GridView gridView;
    ProgressBar progressBar;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_now);
       gridView=findViewById(R.id.gridView);
       progressBar=findViewById(R.id.progressBar);
         back=findViewById(R.id.back);

         String Url="https://anamhr.xyz/app/shop.json" ;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       progressBar.setVisibility(View.GONE);

                        try {

                            for (int x=0 ; x<response.length() ; x++){

                                JSONObject jsonObject=response.getJSONObject(x);
                                String Pname =jsonObject.getString("p_name");
                                String Purl =jsonObject.getString("p_url");
                                String Pimg =jsonObject.getString("img_url");
                                String Ptitle =jsonObject.getString("title");
                                String Pprice =jsonObject.getString("price");

                                hashMap=new HashMap<>();
                                hashMap.put("P_Name",Pname);
                                hashMap.put("P_Url",Purl);
                                hashMap.put("P_Img",Pimg);
                                hashMap.put("P_Title",Ptitle);
                                hashMap.put("P_Price",Pprice);
                                arrayList.add(hashMap);


                            }
                          MyAdapter myAdapter=new MyAdapter();
                            gridView.setAdapter(myAdapter);




                        } catch (JSONException e) {

                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);



            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Shop_Now.this);
        requestQueue.add(jsonArrayRequest);



back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(Shop_Now.this,MainActivity.class);
        startActivity(intent);

    }
});





    }

//Adapter ************************************************
private class MyAdapter extends BaseAdapter{


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=getLayoutInflater();
        View myView =layoutInflater.inflate(R.layout.e_item,null);
        TextView Name,Title,Price ;
        ImageView Img;
        CardView card;
         Name=myView.findViewById(R.id.p_name);
        Title=myView.findViewById(R.id.p_title);
        Price=myView.findViewById(R.id.p_price);
        Img=myView.findViewById(R.id.p_img);
        card=myView.findViewById(R.id.card);

        HashMap<String,String>hashMap=arrayList.get(position);
       String PName = hashMap.get("P_Name") ;
        String PLink = hashMap.get("P_Url") ;
        String PImg = hashMap.get("P_Img") ;
        String PTitle = hashMap.get("P_Title") ;
        String PPrice = hashMap.get("P_Price") ;

        Name.setText(PName);
        Title.setText(PTitle);
        Price.setText(PPrice);
        Picasso.get().load(PImg).into(Img);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
       Price.setBackgroundColor(color);



       card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent=new Intent(Shop_Now.this,Buy_Page.class);
               startActivity(intent);
               Buy_Page.PURL=PLink;
               Buy_Page.NAME=PName;
               Buy_Page.IMG=PImg;
               Buy_Page.TITLE=PTitle;
               Buy_Page.PRICE=PPrice;


           }
       });





        return myView;
    }
}




//Adapter ************************************************

}