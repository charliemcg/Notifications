package com.violenthoboenterprises.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boilerplate code required for dealing with Oreo and up. Use unique values for 'id' and
        // 'name' if you have more than one type of notifications in your app
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("0", "Channel name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void notify(View view) {
        //set this intent to the class you want to open when the user clicks on the notification
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "0")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_icon))
                .setSmallIcon(R.drawable.small_icon)
                .setContentTitle("My title")
                .setContentText("Like and subscribe")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        final NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        //displaying the notification (the delay is for demonstration purposes)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.notify(0, builder.build());
            }
        }, 3000);
    }
}
