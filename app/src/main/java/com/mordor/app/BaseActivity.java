package com.mordor.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;




public class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    protected Menu menu;
    private AlertDialog mDialog;
    protected boolean applyPinInterval = true;
    protected String infoType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            CacheUtil.setContext(this);
//            Fabric.with(this, new Crashlytics());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    public void showProgress() {
        BaseActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                if (mDialog == null) {
                    mDialog = new AlertDialog.Builder(BaseActivity.this)
                            .setView(R.layout.dialog_progress)
                            .create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
                }
                mDialog.show();
            }
        });

    }
    public void disProgress() {
        BaseActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                if (mDialog != null) {
                    try {
                        if(mDialog.isShowing()){
                            mDialog.dismiss();
                        }
                    }catch (IllegalArgumentException e) {
                        // Do nothing.
                    } catch (Exception e){

                    }
                    mDialog = null;
                }
            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);
    }


    public void showToast(String text){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show();
    }

    void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public void showLoadingDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Loading"); //title
        dialog.setMessage("Loading Please Wait"); // message
        dialog.setCancelable(false);
        dialog.show();
    }

    public void hideLoadingDialog() {
        if (dialog != null)
            dialog.dismiss();
    }
//
//    public void onConnectionFailed() {
//        Intent intent = new Intent(this, ActivityConnectionFailure.class);
//        startActivity(intent);
//    }

    public void hideKeyboard() {

        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.menu = menu;


        return super.onCreateOptionsMenu(menu);
    }
}


