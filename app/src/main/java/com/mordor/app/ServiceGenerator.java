package com.mordor.app;


import android.os.Build;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://mordor.gamesdevbox.xyz/";

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();


    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Boolean.class, new IntBooleanTypeAdapter())
            .registerTypeAdapter(boolean.class, new IntBooleanTypeAdapter())
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    public static <S> S createService(Class<S> serviceClass) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
//                        .header("ApiVersion", String.valueOf(BuildConfig.VERSION_CODE))
                        .header("deviceId", Build.ID)
                        .header("deviceName",Build.MODEL)
                        .header("Cache-Control","no-cache")
//                        .header("Authorization","Bearer "+ PreferenceUtil.getToken())
                        .header("Connection","keep-alive");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(httpClient.connectTimeout(200, TimeUnit.SECONDS).readTimeout(200, TimeUnit.SECONDS).build()).build();

        return retrofit.create(serviceClass);
    }


    public static Retrofit retrofit() {
        return builder.build();
    }
}