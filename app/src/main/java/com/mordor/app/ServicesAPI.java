package com.mordor.app;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicesAPI {
    @GET("search")
    Call<ResponseBody> login(@Query("q") String keyword, @Query("start") String offset);

}
