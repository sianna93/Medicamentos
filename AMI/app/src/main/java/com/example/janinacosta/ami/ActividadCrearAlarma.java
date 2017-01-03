package com.example.janinacosta.ami;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class ActividadCrearAlarma extends AppCompatActivity {
    private EditText editText_hora_inicial, editText_frecuencia;
    private String string_hora, string_minutos;
    private int frecuencia_horas;
    private int hora, minutos;

    //Crear alarma
    private AlarmManager alarmManager;
    private Context context;
    private PendingIntent pending_intent;
    private Intent my_intent;
    final Calendar calendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_alarma);

        this.context=this;
        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        my_intent = new Intent(this.context, Alarm_Receiver.class);

        editText_hora_inicial = (EditText) findViewById(R.id.txt_HoraInicial);
        editText_hora_inicial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alarmaPickerDialog();
            }

        });

        editText_frecuencia = (EditText) findViewById(R.id.txt_frecuencia);
        editText_frecuencia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                frecuenciaPickerDialog();
            }

        });
    }


    private void alarmaPickerDialog(){
        NumberPicker np_hora_inicial = new NumberPicker(this);
        np_hora_inicial.setMaxValue(12);
        np_hora_inicial.setMinValue(0);
        np_hora_inicial.setWrapSelectorWheel(false);
        np_hora_inicial.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //editText_hora_inicial.setText(String.valueOf(newVal));
                string_hora= String.valueOf(newVal);
            }
        });

        NumberPicker np_minutos_inicial = new NumberPicker(this);
        np_minutos_inicial.setMaxValue(60);
        np_minutos_inicial.setMinValue(0);
        np_minutos_inicial.setWrapSelectorWheel(false);
        np_minutos_inicial.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //editText_hora_inicial.setText(String.valueOf(newVal));
                string_minutos= String.valueOf(newVal);
            }
        });

        //CON TIME PICKER

        //Calendar calendar = Calendar.getInstance();
        final TimePicker tp_horaInicial = new TimePicker(this);

        AlertDialog.Builder picker_dialog_horaInicial = new AlertDialog.Builder(this).setView(tp_horaInicial);

        picker_dialog_horaInicial.setTitle("Definir Hora Inicial");
        picker_dialog_horaInicial.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                calendar.set(Calendar.HOUR_OF_DAY, tp_horaInicial.getCurrentHour());
                calendar.set(Calendar.MINUTE, tp_horaInicial.getCurrentMinute());

                hora= tp_horaInicial.getCurrentHour();
                minutos= tp_horaInicial.getCurrentMinute();
                String hour_string = String.valueOf(hora);
                String minute_string = String.valueOf(minutos);

                if (hora > 12) {
                    hour_string = String.valueOf(hora - 12);
                }

                if (minutos < 10) {
                    minute_string = "0" + String.valueOf(minutos);
                }


                String am_pm="";
                if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                    am_pm = "AM";
                else if (calendar.get(Calendar.AM_PM) == Calendar.PM)
                    am_pm = "PM";

                editText_hora_inicial.setText(String.valueOf(hour_string + ":" + minute_string+" "+am_pm));


            }
        });
        /*
        picker_dialog_horaInicial.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });*/
        picker_dialog_horaInicial.show();
    }

    private void frecuenciaPickerDialog(){
        NumberPicker np_frecuencia = new NumberPicker(this);
        np_frecuencia.setMaxValue(24);
        np_frecuencia.setMinValue(0);
        np_frecuencia.setWrapSelectorWheel(false);
        np_frecuencia.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //editText_hora_inicial.setText(String.valueOf(newVal));
                frecuencia_horas=newVal;
            }
        });


        AlertDialog.Builder picker_dialog_frecuencia = new AlertDialog.Builder(this).setView(np_frecuencia);

        picker_dialog_frecuencia.setTitle("Frecuencia");
        picker_dialog_frecuencia.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                editText_frecuencia.setText(String.valueOf(frecuencia_horas));
                String horas= "";

                TextView textView_listaHoras = (TextView) findViewById(R.id.txt_lista_horas);

                ArrayList<String> listaHoras= new ArrayList<String>();



                for(int i=hora; i<24;i=(i+frecuencia_horas)){
                    horas = horas + ", "+i+":"+minutos;

                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, minutos);

                    //
                    my_intent.putExtra("extra", "alarm on");

                    //create a pending intent
                    pending_intent = PendingIntent.getBroadcast(ActividadCrearAlarma.this, 0,
                            my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    //set the alarm manager
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                }
                textView_listaHoras.setText("Listas: "+horas);

            }
        });
        picker_dialog_frecuencia.show();
    }
}
