package com.mordor.app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallerList<T> {
    private String STATUS_SUCCESS = "success";
    private String STATUS_ERROR = "error";
    private String STATUS_UNAUTHORIZED = "unauthorized";

    private Call<BaseResponse<ListResponse<T>>> call;
    private Context context;

    public CallerList(Context context, Call call){
        this.context = context;
        this.call = call;
        triggerApi();
    }

    private void triggerApi(){
        this.call.enqueue(new Callback<BaseResponse<ListResponse<T>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ListResponse<T>>> call, Response<BaseResponse<ListResponse<T>>> response) {
                if(response!=null && response.code() == 403){
//                        Intent intent = new Intent(context, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                        ((Activity)(context)).finishAffinity();
                }else if(response.body() == null){
                    onError("Internal Server Error");
                }else{
                    if(STATUS_SUCCESS.equals(response.body().getStatus())){
                        onSuccess(response.body().getData());
                    }else{
                        onError(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<ListResponse<T>>> call, Throwable t) {
                t.printStackTrace();
//                    Intent intent = new Intent(context, NoConnectionActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
            }
        });

    }

    public void onSuccess(ListResponse<T> data){

    }
    public void onError(String errorMessage){
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

}