
package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.anam_animal.cuteanimals.R;

public class Web_Buy extends AppCompatActivity {

    android.webkit.WebView webView;
    LottieAnimationView loading;
    public static String URL = "";
    ImageView shop, back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_buy);
        webView = findViewById(R.id.webView);
        shop = findViewById(R.id.shop);
        back = findViewById(R.id.back);
        loading = findViewById(R.id.loading);



        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&& networkInfo.isConnected()){




        }else {

            new AlertDialog.Builder(Web_Buy.this)
                    .setTitle("No Internet")
                    .setMessage("Please Connect Your Internet")
                    .show();


        }




        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);



        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Web_Buy.this, Shop_Now.class);
                startActivity(intent);


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Web_Buy.this, Buy_Page.class);
                startActivity(intent);


            }
        });



    }


}
