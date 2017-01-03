package com.example.janinacosta.ami;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janina Costa on 3/1/2017.
 */

public class NuevoMedicamentoActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener{
    EditText etName,etDosis, etIndicaciones, etCantDias;
    Button btnSiguiente,btngetdata;
    private TextView DiasDeTratamiento, Dosis;
    DatabaseHelpher helpher;
    List<MedicamentoModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_medicamento);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbList= new ArrayList<MedicamentoModel>();

        etName = (EditText)findViewById(R.id.txt_NombreMedicament);
        etCantDias = (EditText)findViewById(R.id.txt_DiasDeMedicament);
        etDosis = (EditText)findViewById(R.id.txt_dosis);
        etIndicaciones = (EditText)findViewById(R.id.txt_indicaciones);
        //etFrecuencia = (EditText)findViewById(R.id.etFrecuencia);


        //ver recycler
        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NuevoMedicamentoActivity.this, MisMedicamentosActivity.class));
            }
        });

        btnSiguiente  =(Button)findViewById(R.id.btnSiguiente);
        /**************GUARDAR MEDICAMENTO ACCION EN BOTON DE SIANNA *********/
        //Guarda los datos en la BDD
        /*
        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int num_dias = Integer.parseInt(etCantDias.getText().toString());
                int dosis = Integer.parseInt(etDosis.getText().toString());
                String indicaciones = etIndicaciones.getText().toString();
                //String frecuencia=etFrecuencia.getText().toString();

                if(name.equals("")){
                    Toast.makeText(NuevoMedicamentoActivity.this,"porfavor no dejar blanco",Toast.LENGTH_LONG).show();
                }else {
                    helpher = new DatabaseHelpher(NuevoMedicamentoActivity.this);
                    helpher.insertIntoDB(name, num_dias, dosis, indicaciones);
                }
                etName.setText("");
                etCantDias.setText("");
                etDosis.setText("");
                etIndicaciones.setText("");
                //etFrecuencia.setText("");
                Toast.makeText(NuevoMedicamentoActivity.this, "Medicamento insertado", Toast.LENGTH_LONG);
                Log.v("Se guardo","");

            }
        });
        */


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Alarma.class);
                startActivity(i);
            }
        });


        //muestra numberPicker al dar click en el txt de Dias de tratamiento

        //Dias de tratamiento
        DiasDeTratamiento = (TextView)findViewById(R.id.txt_DiasDeMedicament);
        DiasDeTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogoDias();
            }
        });

        //dosis
        Dosis = (TextView)findViewById(R.id.txt_dosis);
        Dosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogoDosis();
            }
        });


    }

    /*Dialogo de Dias de Tratamiento*/
    public void showDialogoDias()
    {
        final Dialog d = new Dialog(NuevoMedicamentoActivity.this);
        d.setTitle("Dias de Tratamiento");
        d.setContentView(R.layout.dialogo_number_picker);
        Button cancelDialogo = (Button) d.findViewById(R.id.button1);
        Button okDialogo = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.picker);
        np.setMaxValue(30); // max value 100
        np.setMinValue(1);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);

        cancelDialogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DiasDeTratamiento.setText(String.valueOf(np.getValue())); //set the value to textview
                d.dismiss();
            }
        });

        okDialogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { d.dismiss(); }
        });
        d.show();

    }
    /*Dialogo de Dosis*/
    public void showDialogoDosis()
    {
        final Dialog d = new Dialog(NuevoMedicamentoActivity.this);
        d.setTitle("Dosis");
        d.setContentView(R.layout.dialogo_number_picker);
        Button cancelDialogo = (Button) d.findViewById(R.id.button1);
        Button okDialogo = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.picker);
        np.setMaxValue(10); // max value 100
        np.setMinValue(1);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);

        cancelDialogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Dosis.setText(String.valueOf(np.getValue())); //set the value to textview
                d.dismiss();
            }
        });

        okDialogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { d.dismiss(); }
        });
        d.show();

    }


    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Log.i("value is",""+i1);
    }

    //boton regresar

    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_medicamento, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
