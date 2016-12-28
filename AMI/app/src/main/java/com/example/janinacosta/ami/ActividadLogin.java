package com.example.janinacosta.ami;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;

/**
 * Created by Janina Costa on 14/12/2016.
 */

public class ActividadLogin extends AppCompatActivity {

    private TextInputLayout tilUsuario;
    private TextInputLayout tilContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_login);

        Button botonLogin = (Button)findViewById(R.id.btn_login);
        Button botonRegistrar = (Button)findViewById(R.id.boton_registrarse);
        tilUsuario = (TextInputLayout) findViewById(R.id.til_Usuario);
        tilContraseña = (TextInputLayout) findViewById(R.id.til_Contraseña);


        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActividad.class );
                startActivity(i);
            }
        });

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActividadRegistrar.class );
                startActivity(i);
            }
        });

    }


}
