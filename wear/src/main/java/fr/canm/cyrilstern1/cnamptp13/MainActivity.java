package fr.canm.cyrilstern1.cnamptp13;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;
    public NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /**
         * notification lançant le time progress
         */
        Intent act4 = new Intent(this, ProgressActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, act4, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.WearableExtender extender1 = new Notification
                .WearableExtender()
                .setContentAction(1);

        Notification notificationProgress = new Notification.Builder(this)
                .setSmallIcon(R.drawable.watch)
                .setContentTitle("voici un sablier")
                .setContentText("un sablier de 10 sec")
                .setContentIntent(pendingIntent1)
                .extend(extender1)
                .build();


        /**
         * notification image
         */

        List<Notification> pages = new ArrayList<Notification>();
        for( int i = 1; i <= 3; i++ ){
            if(i == 1){

            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Page " + i)
                    .setContentText("peler à vif les oranges..... " + i)
                    .build();
            pages.add(notification);
            }
            if(i == 2){

                Notification notification = new Notification.Builder(this)
                        .setContentTitle("Page " + i)
                        .setContentText("faites cuire les quartiers dans l eau sucrée" + i)
                        .build();
                pages.add(notification);
            }
            if(i == 3){

                Notification notification = new Notification.Builder(this)
                        .setContentTitle("Page " + i)
                        .setContentText("passer au mixeur.. " + i)
                        .build();
                pages.add(notification);
            }

        }

        Notification.WearableExtender extender2 = new Notification
                .WearableExtender()
                .addPages(pages)
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ornage));

        Notification notification2 = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ornage)
                .setContentTitle("Background notification")
                .setContentText("voici une rectte à l'orange")
                .extend(extender2)
                .build();


        /**
         * notification theater
         */

        Intent act2 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
        act2.setData(Uri.parse("http://www.allocine.fr"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, act2, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action action2 = new Notification.Action.Builder(
                R.drawable.cine, "Web", pendingIntent2).build();

        Notification.Style style = new Notification.BigTextStyle()
                .setBigContentTitle("Nouveau film")
                .setSummaryText("Resumer Café Society")
                .bigText("New York, dans les années 30. Coincé entre des parents conflictuels, un frère gangster et la bijouterie familiale, Bobby Dorfman a le sentiment d'étouffer ! Il décide donc de tenter sa chance à Hollywood où son oncle Phil, puissant agent de stars, accepte de l'engager comme coursier. À Hollywood, Bobby ne tarde pas à tomber amoureux. Malheureusement, la belle n'est pas libre et il doit se contenter de son amitié. \n" +
                        "Jusqu'au jour où elle débarque chez lui pour lui annoncer que son petit ami vient de rompre. Soudain, l'horizon s'éclaire pour Bobby et l'amour semble à portée de main…");

        Notification.WearableExtender extender3 = new Notification
                .WearableExtender()
                .addAction(action2)
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.cafesociety));

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.cine)
                .setContentTitle("Nouveau film")
                .setContentText("Content text")
                .setContentIntent(pendingIntent2)
                .setStyle(style)
                .extend(extender3)
                .build();




        notificationManager.notify(10,notification2);
        notificationManager.notify(11,notification);
        notificationManager.notify(12, notificationProgress);


    }
}
