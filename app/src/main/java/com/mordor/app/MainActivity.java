package com.mordor.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        HttpPresenter httpPresenter = new HttpPresenter(this);
//        httpPresenter.login();

        thread.start();

    }
    List<String> linksKeyWord = new ArrayList<>();
    String URL_PREFIX = "/url?q=";
    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            try  {
                for(int i = 0;i<10;i++){
                    Document links=null;
                    String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";

                    try {
                        links = Jsoup.connect("https://www.google.com/search?q=gege&start="+String.valueOf(i*10)).
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
//                    Element title = link.selectFirst("");
//                    Element url = link.absUrl("href"); // Google returns URLs in
//                    format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".

//                    System.out.println("Title: " + title);
//                    System.out.println("URL: " + url);


                        if(link.toString().contains("href")&&link.attr("href").startsWith(URL_PREFIX)){
                            linksKeyWord.add(link.attr("href").replace(URL_PREFIX,""));
                        }
                    }
                }

                Log.d("asdadas","response.body().toString()");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}