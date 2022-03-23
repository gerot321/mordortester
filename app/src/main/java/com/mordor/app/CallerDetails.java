package com.mordor.app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallerDetails<T> {
//    private String STATUS_SUCCESS = "success";
//    private String STATUS_ERROR = "error";
//    private String STATUS_UNAUTHORIZED = "unauthorized";
//
//    private Call<BaseResponse<T>> call;
//    private Context context;
//
//    public CallerDetails(Context context, Call call){
//        this.context = context;
//        this.call = call;
//        triggerApi();
//    }
//
//    private void triggerApi(){
//        if(isNetworkConnected(context)){
//            this.call.enqueue(new Callback<BaseResponse<T>>() {
//                @Override
//                public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
//                    if(response!=null && response.code() == 403){
//                        Intent intent = new Intent(context, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                        PreferenceUtil.clearAll();
//                        ((Activity)(context)).finishAffinity();
//                    }else if(response.body() == null){
//                        onError("Internal Server Error");
//                    }else{
//                        if(STATUS_SUCCESS.equals(response.body().getStatus())){
//                            onSuccess(response.body().getData());
//                        }else{
//                            onError(response.body().getMessage());
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
//                    t.printStackTrace();
//
//                }
//            });
//        }else{
////            Intent intent = new Intent(context, NoConnectionActivity.class);
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(intent);
//        }
//
//    }
//
//    public void onSuccess(T data){
//
//    }
//    public void onError(String errorMessage){
//        utils.showToastError(errorMessage, this.context, Constant.TOAST_ERROR);
//    }
//    private boolean isNetworkConnected(Context context) {
//        if(context==null){
//            return true;
//        }
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo() != null;
//    }
//    public boolean internetIsConnected() {
//        try {
//            String command = "ping -c 1 google.com";
//            return (Runtime.getRuntime().exec(command).waitFor() == 0);
//        } catch (Exception e) {
//            return false;
//        }
//    }
}