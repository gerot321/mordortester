
package com.mordor.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpPresenter extends BasePresenter {
    private MainActivity mainActivity;

    public HttpPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void login() {
        Call<ResponseBody> call = ServiceGenerator.createService(ServicesAPI.class).login("slot", "0");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String html = null;
                try {
                    html = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Document document = Jsoup.parse(html);
                Log.d("asdadas",response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
