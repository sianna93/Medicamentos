package com.example.janinacosta.ami;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Janina Costa on 26/12/2016.
 */

public class MenuActividad extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    public static  final String EXTRA_COLOR = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_recycle);
        /*Button botonCerrarSesion = (Button)findViewById(R.id.idCerrarSesion);
         botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ActividadLogin.class );
                    startActivity(i);
                }
            });

        }
         */

        List<MenuOpciones> items = new ArrayList<>();

        items.add(new MenuOpciones(R.drawable.pastillas, "#082D5C"));
        items.add(new MenuOpciones(R.drawable.mis_medicamentos, "#058687"));
        items.add(new MenuOpciones(R.drawable.historial, "#FCB13D"));
        items.add(new MenuOpciones(R.drawable.salir, "#A31C27"));
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new MenuAdapter(items);
        recycler.setAdapter(adapter);
    }





}
