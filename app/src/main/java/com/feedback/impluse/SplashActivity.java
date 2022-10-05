package com.feedback.impluse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.feedback.impluse.CacheStorage.SessionManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;


public class SplashActivity extends AppCompatActivity {
    ProgressBar progressbar;

    public static String deviceid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(SplashActivity.this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        progressbar.setMax(100);
        progressbar.setProgress(2);
        getprogress(3);


        ImageView imageView = findViewById(R.id.imageview);
        Glide.with(this).load(R.drawable.logoonlight).into(imageView);

//        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//        telephonyManager.getDeviceId();

        deviceid = android.provider.Settings.System.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        Log.e("deviceid",deviceid+"");



    }
    private void getprogress(final int progress) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressbar.setProgress(progress);
                if (progress <= 100) {
                    getprogress(progress + 5);
                } else {

                    FirebaseApp.initializeApp(SplashActivity.this);

                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
                        if (!TextUtils.isEmpty(token)) {
                            Log.d("MYTAG", "retrieve token successful : " + token);
                        } else{
                            Log.w("MYTAG", "token should not be null...");
                        }
                    }).addOnFailureListener(e -> {
                        //handle e
                    }).addOnCanceledListener(() -> {
                        //handle cancel
                    }).addOnCompleteListener(task -> Log.v("MYTAG", "This is the token : " + task.getResult()));

                    String deviceToken = FirebaseMessagingService.getToken(SplashActivity.this);
                    Log.d("MYTAG", "This is your Firebase token" + deviceToken);

                    if(new SessionManager(SplashActivity.this).isLoggedIn())
                    {
//                    new SessionManager(Splash.this).setKeyHaveJob(false);

                        startActivity(new Intent(SplashActivity.this,Dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        Log.e("sessionlogin",new SessionManager(SplashActivity.this).isLoggedIn()+" ");
                    }
                    else {
                        Intent intent=new Intent(SplashActivity.this, Dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

//Intent intent=new Intent(SplashActivity.this, SliderActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//startActivity(intent);
//finish();
                }
            }
        }, 100);
    }
}