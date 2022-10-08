package com.feedback.impluse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.feedback.impluse.CacheStorage.SessionManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;


public class IntroActivity extends AppCompatActivity {

    AppCompatButton skipnow;
    VideoView videoView;
    private BetterVideoPlayer mBetterVideoPlayer;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mBetterVideoPlayer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private static final boolean AUTO_HIDE = true;
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(IntroActivity.this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.intro);



        getWindow(). addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        skipnow =findViewById(R.id.skipnow);
        skipnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SessionManager(IntroActivity.this).setLogin(true);
                Intent intent=new Intent(IntroActivity.this, Dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        try
        {
            mVisible = true;
            mBetterVideoPlayer = (BetterVideoPlayer) findViewById(R.id.bvp);

            String uriPath
                    = "android.resource://"
                    + getPackageName() + "/" + R.raw.intro;
            Uri uri = Uri.parse(uriPath);

            mBetterVideoPlayer.setSource(uri);
            mBetterVideoPlayer.enableSwipeGestures();
            mBetterVideoPlayer.showControls();
            mBetterVideoPlayer.setHideControlsOnPlay(true);
         //   mBetterVideoPlayer.getToolbar().setTitle(title);

        }
        catch (Exception ex)
        {
            Toast.makeText(this, "An error Occurred", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPause() {
        mBetterVideoPlayer.pause();
        super.onPause();
        mBetterVideoPlayer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


    }

    @Override
    public void onResume() {
        super.onResume();


        mBetterVideoPlayer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }




}