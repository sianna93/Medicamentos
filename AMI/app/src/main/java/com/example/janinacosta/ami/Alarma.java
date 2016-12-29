package com.example.janinacosta.ami;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarma extends AppCompatActivity {
    AlarmManager alarmManager;
    private TimePicker alarmTimePicker;
    private TextView updateText;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        this.context=this;

        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmTimePicker=(TimePicker) findViewById(R.id.timePicker);
        updateText = (TextView) findViewById(R.id.update_text);

        final Calendar calendar = Calendar.getInstance();

        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                int hour = alarmTimePicker.getCurrentHour();
                int minute = alarmTimePicker.getCurrentMinute();

                //convert int to string
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                set_alarm_text("Alarma programada: " + hour_string + ":" + minute_string);

                //
                my_intent.putExtra("extra", "alarm on");


                //create a pending intent
                pending_intent = PendingIntent.getBroadcast(Alarma.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
            }
        });

        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm off!");

                alarmManager.cancel(pending_intent);

                // put extra string into my intent
                //tells the clock
                my_intent.putExtra("extra", "alarm off");


                // stop the ringtone
                sendBroadcast(my_intent);
            }
        });
    }

    private void set_alarm_text(String output) {
        updateText.setText(output);
    }
}
