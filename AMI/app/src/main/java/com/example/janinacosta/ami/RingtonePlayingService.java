package com.example.janinacosta.ami;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Sianna-chan on 28/12/2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean  isRunning;

    //@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //fetch the extra string values
        String state= intent.getExtras().getString("extra"); //IDEA: enviar array

        Log.e("Ringtone extra is ", state);

        assert  state != null;
        switch(state){
            case "alarm on":
                startId =1;
                break;
            case "alarm off":
                startId =0;
                Log.e("Start id is ", state);
                break;
            default:
                startId=0;
                break;
        }


        if (!this.isRunning && startId ==1){
            Log.e("there is no music", "and you want start");

            media_song = MediaPlayer.create(this, R.raw.alarm1);
            media_song.start();

            this.isRunning =true;
            this.startId=0;

            //Notificaciones
            NotificationManager notify_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //set up intent activity
            Intent intent_main_activity = new Intent(this.getApplicationContext(),Alarma.class);

            //set up a pending intent
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this,0,intent_main_activity,0);


            //Make nottification
            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Alarma: Tomar Medicamento!")
                    .setContentText("Click me!")
                    .setContentIntent(pending_intent_main_activity)
                    .setSmallIcon(R.drawable.ic_pastilla)
                    .setAutoCancel(true)
                    .build();

            //set up the notification call command
            notify_manager.notify(0,notification_popup);



        }else if(this.isRunning && startId ==0){
            Log.e("there is music", "and you want end");

            media_song.stop();
            media_song.reset();

            this.isRunning=false;
            this.startId=0;

        }else if(!this.isRunning && startId ==0){
            Log.e("there is no music", "and you want end");

            this.isRunning = false;
            this.startId = 0;

        }else if(this.isRunning && startId ==1){
            Log.e("there is music", "and you want start");

            this.isRunning = true;
            this.startId = 1;

        }else {
            Log.e("else ", "somehow you reached this");
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("on Destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;
    }


}