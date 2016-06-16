package com.example.xavier.admob_example;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class second_activity extends Activity {
    AdView adView;
    Bitmap bmp;
    Button btn_barra;
    Button btn_bigPicture;
    Button btn_Inbox_Style;
    Button btn_Notificacion_conBtones;
    Button getBtn_Notificacion_conBtonesEstilos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        btn_barra = (Button) findViewById(R.id.btn_barra);
        btn_barra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificar();
            }
        });

        btn_bigPicture = (Button) findViewById(R.id.btn_bigPic);
        btn_bigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificar_Big_Picture();
            }
        });

        btn_Inbox_Style = (Button) findViewById(R.id.btn_inbox);
        btn_Inbox_Style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificar_Inbox_Style();
            }
        });

        btn_Notificacion_conBtones = (Button) findViewById(R.id.btn_con_botones);
        btn_Notificacion_conBtones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificar_con_botones();
            }
        });

        getBtn_Notificacion_conBtonesEstilos = (Button) findViewById(R.id.btn_botonEstilo);
        getBtn_Notificacion_conBtonesEstilos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificar_con_botonesEstilos();
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if (getIntent().getExtras() != null)
        {
            boolean mostrarToast = getIntent().getExtras().getBoolean("Notificacion", false);
            if (mostrarToast)
            {
                Toast.makeText(this, "Desde notificación", Toast.LENGTH_SHORT).show();
            }
        }
        adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            public void onAdLoaded() {
            }

            public void onAdOpened() {
            }

            public void onAdClosed() {
            }

            public void onAdLeftApplication() {
            }

            public void onAdFailedToLoad(int errorcode) {
            }
        });

    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Notificar()
    {
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("Notificacion", true);
        // Usar System.currentTimeMillis() para que el ID del pendingIntent, sea único
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(second_activity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon(bmp)
                        .setContentTitle("Mensaje de Alerta")//Título
                        .setContentText("Ejemplo de notificación.")//Descripción
                        .setContentInfo("4")//Mas info
                        .setWhen(System.currentTimeMillis())//hora
                        .setAutoCancel(true)//cierre al dar tab
                        .setPriority(Notification.PRIORITY_LOW)
                        .setLights(Color.BLUE, 500, 500)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pIntent)//Activity que se va llamar
                        .setTicker("Alerta!");//Mensaje que se muestra al llegar la notificación
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
        finish();
    }

    public void Notificar_Big_Picture()
    {
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("Notificacion", true);
        // Usar System.currentTimeMillis() para que el ID del pendingIntent, sea único
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(second_activity.this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentInfo("4")
                .setWhen(System.currentTimeMillis())//hora
                .setAutoCancel(true)//cierre al dar tab
                .setLights(Color.BLUE, 500, 500)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                .setContentIntent(pIntent)//Activity que se va llamar
                .setTicker("Alerta!");//Mensaje que se muestra al llegar la notificación
                Bitmap large = BitmapFactory.decodeResource(getResources(), R.drawable.cool);
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                        .bigPicture(large)
                        .bigLargeIcon(bmp)
                        .setBigContentTitle("BigPicture")
                        .setSummaryText("Descripcion");
        mBuilder.setStyle(bigPictureStyle);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        finish();
    }

    public void Notificar_Inbox_Style()
    {
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("Notificacion", true);
        // Usar System.currentTimeMillis() para que el ID del pendingIntent, sea único
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(second_activity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon(bmp)
                        .setContentTitle("Mensaje de Alerta")//Título
                        .setContentText("Ejemplo de notificación.")//Descripción
                        .setContentInfo("4")
                        .setWhen(System.currentTimeMillis())//hora
                        .setAutoCancel(true)//cierre al dar tab
                        .setLights(Color.BLUE, 500, 500)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pIntent)//Activity que se va llamar
                        .setTicker("Alerta!");//Mensaje que se muestra al llegar la notificación
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0] = new String("Primer linea....");
        events[1] = new String("Segunda linea...");
        events[2] = new String("Tercer linea...");
        events[3] = new String("Cuarta linea...");
        events[4] = new String("Quinta linea...");
        events[5] = new String("Sexta linea...");
        inboxStyle.setBigContentTitle("Detalles:");
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        mBuilder.setStyle(inboxStyle);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        finish();
    }

    public void Notificar_con_botones()
    {
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("Notificacion", true);
        // Usar System.currentTimeMillis() para que el ID del pendingIntent, sea único
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(second_activity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon(bmp)
                        .setContentTitle("Mensaje de Alerta")//Título
                        .setContentText("Ejemplo de notificación.")//Descripción
                        .setContentInfo("4")
                        .setWhen(System.currentTimeMillis())//hora
                        .setAutoCancel(true)//cierre al dar tab
                        .setLights(Color.BLUE, 500, 500)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pIntent)//Activity que se va llmar
                        .setTicker("Alerta!");//Mensaje que se muestra al llegar la notificación
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mBuilder.addAction(android.R.drawable.ic_menu_delete, "Delete", pIntent);
                            mBuilder.addAction(android.R.drawable.ic_menu_share, "Share", pIntent);
                        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        finish();
    }

    public void Notificar_con_botonesEstilos()
    {
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("Notificacion", true);
        // Usar System.currentTimeMillis() para que el ID del pendingIntent, sea único
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Bitmap large = BitmapFactory.decodeResource(getResources(), R.drawable.cool);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(large)
                .bigLargeIcon(bmp)
                .setBigContentTitle("BigPicture")
                .setSummaryText("Descripcion");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(second_activity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentInfo("Lo que sea")
                        .setWhen(System.currentTimeMillis())//hora
                        .setAutoCancel(true)//cierre al dar tab
                        .setLights(Color.BLUE, 500, 500)
                        .addAction(android.R.drawable.ic_menu_delete, "Delete", pIntent)
                        .addAction(android.R.drawable.ic_menu_share, "Share", pIntent)
                        .addAction(android.R.drawable.ic_menu_call, "Call", pIntent)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setStyle(bigPictureStyle)//Establecer el estilo
        .setTicker("Alerta!");//Mensaje que se muestra al llegar la notificación
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        finish();
    }
}
