package com.anam_animal.cuteanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.atikulsoftware.metaadslibrary.MetaAds.FacebookAds;
import com.anam_animal.cuteanimals.R;


public class splsh_screen extends AppCompatActivity {

         ProgressBar progress;
         private int prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh_screen);
       progress=findViewById(R.id.progress);


          AdUnitId.fbId();
        FacebookAds.loadInterstitial(splsh_screen.this);


       Thread thread =new Thread(new Runnable() {
           @Override
           public void run() {

             doWork();

               Intent intent=new Intent(splsh_screen.this,MainActivity.class);
               startActivity(intent);



           }
       });
 thread.start();



    }

    public void doWork(){

for (prog=25 ;prog<=100 ; prog=prog+25){

    try {
        Thread.sleep(1000);
        progress.setProgress(prog);
    } catch (InterruptedException e) {

        throw new RuntimeException(e);
    }

}



    }







}