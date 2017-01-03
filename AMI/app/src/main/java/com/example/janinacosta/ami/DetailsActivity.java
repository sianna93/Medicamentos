package com.example.janinacosta.ami;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janina Costa on 3/1/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    //data
    DatabaseHelpher helpher;
    List<MedicamentoModel> dbList;
    int position;
    TextView tvname,tvdosis, tvnumdias, tvindicaciones, tvfrecuencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.name);
        tvnumdias =(TextView)findViewById(R.id.diasMedicamento);
        tvdosis =(TextView)findViewById(R.id.dosis);
        tvindicaciones =(TextView)findViewById(R.id.indicaciones);
        //tvfrecuencia =(TextView)findViewById(R.id.frecuencia);

        helpher = new DatabaseHelpher(this);
        dbList= new ArrayList<MedicamentoModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            int dias= dbList.get(position).getNum_dias();
            int dosis=dbList.get(position).getDosis();
            String indicaciones= dbList.get(position).getIndicaciones();

            tvname.setText(name);
            tvnumdias.setText(String.valueOf(dias));
            tvdosis.setText(String.valueOf(dosis));
            tvindicaciones.setText(indicaciones);
            //tvfrecuencia.setText(frecuencia);
        }

        Toast.makeText(DetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);
    }


    //boton regresar
    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
