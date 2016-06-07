package fr.canm.cyrilstern1.cnamptp13;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Style style = new Notification.BigTextStyle()
                .setBigContentTitle("My big title")
                .setSummaryText("My summary")
                .bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Integer tristique fringilla neque ornare convallis. Sed aliquam, " +
                        "diam in elementum aliquet, odio massa adipiscing ligula, " +
                        "at pretium justo velit et arcu.");
        Notification.WearableExtender extender = new Notification
                .WearableExtender()
                .setStartScrollBottom(true);
        Intent action = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent act2 = new Intent(Intent.ACTION_VIEW);
        act2.setData(Uri.parse("http://developer.android.com"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, act2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Action action2 = new Notification.Action.Builder(
                R.drawable.common_google_signin_btn_icon_dark, "Web", pendingIntent2).build();

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Content title")
                .setContentText("Content text")
                .setContentIntent(pendingIntent)
                .setStyle(style).extend(extender).build();

        Notification.WearableExtender extender2 = new Notification
                .WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ornage));
        Notification notification2 = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ornage)
                .setContentTitle("Background notification")
                .setContentText("Using a custom background for a normal notification")
                .extend(extender2)
                .build();

        /**
         *
         création de la notification permettant d'afficher la progression'
         */

        Intent act4 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, act4, PendingIntent.FLAG_UPDATE_CURRENT); Notification.Action action1 = new Notification.Action.Builder(
                R.drawable.common_google_signin_btn_icon_dark, "Open app", pendingIntent1).build();

        Intent act3 = new Intent(Intent.ACTION_CALL);
        act3.setData(Uri.parse("http://developer.android.com"));
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, act3, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action action3= new Notification.Action.Builder(R.drawable.common_google_signin_btn_icon_dark, "Web", pendingIntent3).build();

        Notification.WearableExtender extender1 = new Notification
                .WearableExtender()
                .addAction(action3)
                .addAction(action1)
                .setContentAction(1);

        Notification notificationProgress = new Notification.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Action notification")
                .setContentText("This notification has multiple actions")
                .extend(extender1)
                .build();

        notificationManager.notify(10,notification2);
        notificationManager.notify(11,notification);
        notificationManager.notify(12, notificationProgress);
    }
}
