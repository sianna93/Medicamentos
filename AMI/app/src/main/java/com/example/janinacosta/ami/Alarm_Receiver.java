package com.example.janinacosta.ami;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Sianna-chan on 28/12/2016.
 */
public class Alarm_Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver.", "Yey!");

        //fetch extra strings from the intent
        String get_your_string = intent.getExtras().getString("extra");

        Log.e("What is the key", get_your_string);


        //create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from Alarm Activity to the Rington Playing
        service_intent.putExtra("extra", get_your_string);

        //start the ringtone service
        context.startService(service_intent);


    }
}

