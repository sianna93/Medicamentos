package com.example.janinacosta.ami;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Janina Costa on 14/12/2016.
 */
public class ActividadRegistrar extends AppCompatActivity {
    private TextInputLayout tilNombre;
    private TextInputLayout tilUsuario;
    private TextInputLayout tilContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registrar);
        Button botonAceptar = (Button)findViewById(R.id.btn_aceptar);
        Button botonCancelar = (Button)findViewById(R.id.btn_cancelar);
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombres);
        //EditText txtNombre = (EditText) findViewById(R.id.txt_nombres);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });


        //Accion boton Cancelar: Regresa ActivityLogin
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActividadLogin.class );
                startActivity(i);
            }
        });
    }

     boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            tilNombre.getEditText().setText("");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    void validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        boolean a = esNombreValido(nombre);
        if (a) {
            //si todos los datos están validados
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show();
        }

    }


}
