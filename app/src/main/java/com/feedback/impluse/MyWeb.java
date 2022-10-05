package com.feedback.impluse;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MyWeb extends AppCompatActivity {

    Button taketest;
    EditText editcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myweb);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final WebView webview;
        webview = (WebView) findViewById(R.id.webview);
        webview.setScrollContainer(false);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.loadUrl(getIntent().getExtras().getString("theurl","https://www.impulse.win/terms-of-use/"));


        webview.setWebViewClient(new WebViewClient()
        {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(MyWeb.this, "An error Occurred, Try Again!" , Toast.LENGTH_LONG).show();

                Log.e("testweb","error");

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

//                callprogress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                Log.e("testwebdata","finish");


                try
                {


                    Log.e("testwebdata","try");
                }
                catch (Exception ex)
                {
                    Log.e("testwebdata","catch");
                }

            }

            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
//                editcode.setEnabled(true);
//                editcode.setFocusable(true);

                try
                {

                    Log.e("testwebdata","overrideurl");

                    webview.loadUrl(url+"");
                }
                catch (Exception ex)
                {
                    Log.e("testwebdata","catch");
                }

                return true;
            }

        });


    }

    @Override
    public void onBackPressed() {
//        new AlertDialog.Builder(TestWeb.this).setTitle("Exit").setMessage("Do you want to Exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).show();

//        try
//        {
//            CustomProgress.hide();
//        }
//        catch (Exception ex)
//        {
//
//        }


        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {

//        try
//        {
//            CustomProgress.hide();
//        }
//        catch (Exception ex)
//        {
//
//        }

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        CustomProgress.hide();
//        callprogress();
    }


}
