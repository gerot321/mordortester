package com.mordor.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.mordor.app.databinding.ActivityMainBinding;
import com.mordor.app.databinding.ActivityResultBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        activityMainBinding.btnCheck.setOnClickListener(view1 -> {
            showProgress();
            thread.start();
        });
    }
    ArrayList<String> linksKeyWord = new ArrayList<>();
    String URL_PREFIX = "/url?q=";
    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            try  {
                for(int i = 0;i<13;i++){
                    Document links=null;
                    String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";

                    try {
                        links = Jsoup.connect("https://www.google.com/search?q=slot+online&start="+String.valueOf(i*10)).
                                userAgent(userAgent).get();
                    } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    Elements child = links.select("#main > div > div > div > a");
                    for (Element link : child) {
                        if(link.toString().contains("href")&&link.attr("href").startsWith(URL_PREFIX)){
                            linksKeyWord.add(link.attr("href").replace(URL_PREFIX,""));
                        }
                    }
                }
                MainActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        disProgress();
                        //Do your UI operations like dialog opening or Toast here
                    }
                });
                Intent intent = new Intent(MainActivity.this, RankingResultActivity.class);
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)linksKeyWord);
                intent.putStringArrayListExtra("list",linksKeyWord);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}