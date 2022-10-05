package com.feedback.impluse;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    Bitmap bitmap;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken", s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String titel,message,img_url;
            titel= remoteMessage.getData().get("title");
             message = remoteMessage.getData().get("message");
            img_url = remoteMessage.getData().get("image");


            Intent intent=new Intent(this,SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent , PendingIntent.FLAG_ONE_SHOT);
        Uri urisound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle(titel);

        notification.setSubText(message);
        notification.setSound(urisound);
        notification.setAutoCancel(true);

            notification.setContentIntent(pendingIntent);

        notification.setSmallIcon(R.drawable.logo);


        }


       Intent intent=new Intent(this,SplashActivity.class);


//       Intent intent=new Intent(this,SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle("Impulse - anonymous feedback");
//        Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
//                + "://" + getPackageName() + "/raw/start");

        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();
        Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.notificationsound);
       // notification.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE);
        notification.setSound(soundUri);

//        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // Default
//
//notification.setSound(soundUri);

        notification.setContentText(remoteMessage.getNotification().getBody());
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.logo);
        notification.setContentIntent(pendingIntent);

        Intent intenttwo=new Intent(this,SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingintenttwo = PendingIntent.getActivity(this, 0 , intenttwo,
                PendingIntent.FLAG_ONE_SHOT);



        notification.addAction(R.drawable.logo,"Details",pendingintenttwo);

      //  notification.addAction(R.drawable.ic_close_black_24dp,"Reject", pendingintenttwo);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "notify_001";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(soundUri,audioAttributes);
            notificationManager.createNotificationChannel(channel);
            notification.setChannelId(channelId);
        }


        notificationManager.notify(0,notification.build());



    }

}