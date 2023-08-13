package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.atikulsoftware.metaadslibrary.MetaAds.FacebookAds;
import com.anam_animal.cuteanimals.R;

public class WebView extends AppCompatActivity {


    ImageView home ;

   android.webkit.WebView webView;

    public static String VIDEO_URL = "" ;
    public static String VIDEO_TITLE = "" ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        home=findViewById(R.id.Home);
        webView=findViewById(R.id.webView);
     WebSettings webSettings = webView.getSettings();
     webSettings.setJavaScriptEnabled(true);
     webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(VIDEO_URL);

        FacebookAds.setBanner(findViewById(R.id.banner_container), WebView.this);

    home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent =new Intent(WebView.this,MainActivity.class);
            startActivity(intent);


        }
    });




    }
}