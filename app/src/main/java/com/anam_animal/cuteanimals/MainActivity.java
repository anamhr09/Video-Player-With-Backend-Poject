package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.atikulsoftware.metaadslibrary.MetaAds.FacebookAds;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
ProgressBar progress;
ImageView shop;

ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
HashMap<String,String>hashMap;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         gridView=findViewById(R.id.gridView);
         progress=findViewById(R.id.progress);
         shop=findViewById(R.id.shop);





        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&& networkInfo.isConnected()){

            FacebookAds.setBanner(findViewById(R.id.banner_container), MainActivity.this);


    }else {

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("No Internet")
                    .setMessage("Please Connect Your Internet")
                    .show();


        }

        // array request**********




        String  Url ="https://anamhr.xyz/app/info.json" ;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                     progress.setVisibility(View.GONE);


                        try {

                            for (int x=0 ; x<response.length() ; x++){

                                JSONObject jsonObject=response.getJSONObject(x);
                                String Video_Url = jsonObject.getString("video_url") ;
                                String Img_Url = jsonObject.getString("img_url");
                                String Video_Title =jsonObject.getString("Title");

                                hashMap=new HashMap<>();
                                hashMap.put("V_U" ,Video_Url) ;
                                hashMap.put("I_U" ,Img_Url);
                                hashMap.put("V_T",Video_Title);
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
                progress.setVisibility(View.GONE);


                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("No Internet")
                        .setMessage("Please Connect Your Internet")
                        .show();



            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
// array request**********


shop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

       Intent intent=new Intent(MainActivity.this,Shop_Now.class);
       startActivity(intent);


    }
});






    }

    // Adapter *************************************

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=getLayoutInflater();
        View myView =layoutInflater.inflate(R.layout.item,null);

        TextView title;
        ImageView img;
        CardView cardView;
        title=myView.findViewById(R.id.title);
        img=myView.findViewById(R.id.img);
        cardView=myView.findViewById(R.id.cardView);

        HashMap<String,String>hashMap=arrayList.get(position);
        String V_Url =hashMap.get("V_U") ;
        String I_Url =hashMap.get("I_U") ;
        String V_Title =hashMap.get("V_T") ;


        title.setText(V_Title);
        Picasso.get().load(I_Url).into(img);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new FacebookAds(() -> {
                    // Next Action

                    Intent intent=new Intent(MainActivity.this,WebView.class);
                    startActivity(intent);
                    WebView.VIDEO_URL=V_Url ;
                    WebView.VIDEO_TITLE=V_Title ;

                }).showInterstitial();




            }
        });





        return myView;

}





    }




}




