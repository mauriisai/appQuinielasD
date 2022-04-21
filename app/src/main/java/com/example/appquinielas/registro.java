package com.example.appquinielas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class registro extends AppCompatActivity {

    //CREACION DE VARIABLES
    Button btnRegistrar;
    EditText txtNomUsuario, txtCorreo, txtIdentificacion, txtTelefeno, txtUsuPassword;

    //INSTANCIA DE LA BD
    SQLite_OpenHelper helper= new SQLite_OpenHelper(this, "BDQuinielas", null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);
        txtNomUsuario= (EditText) findViewById(R.id.txtNomUsuario);
        txtCorreo= (EditText) findViewById(R.id.txtCorreo);
        txtIdentificacion= (EditText) findViewById(R.id.txtIdentificacion);
        txtTelefeno= (EditText) findViewById(R.id.txtTelefono);
        txtUsuPassword= (EditText) findViewById(R.id.txtUsuPassword);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                helper.insertRegistros(String.valueOf(txtNomUsuario.getText()),
                        String.valueOf(txtCorreo.getText()), String.valueOf(txtIdentificacion.getText()),
                        String.valueOf(txtTelefeno.getText()), String.valueOf(txtUsuPassword.getText()));
                helper.cerrar();

                Toast.makeText(getApplicationContext(), "Registro Almacenado con Exito",Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}