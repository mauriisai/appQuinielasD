package com.example.appquinielas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.tblEquipo;

public class regEquipo extends AppCompatActivity {

    EditText txtNombreEq, txtPaisEq, txtCorreoEq;
    Button btnGuardarEq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_equipo);

        txtNombreEq = findViewById(R.id.txtNomEquipo);
        txtPaisEq = findViewById(R.id.txtPais);
        txtCorreoEq = findViewById(R.id.txtCorreoEquipo);
        btnGuardarEq = findViewById(R.id.btnGuardarEq);

        btnGuardarEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombreEq.getText().toString().equals("") && !txtPaisEq.getText().toString().equals("")) {

                    tblEquipo tbequipo = new tblEquipo(regEquipo.this);
                    long id = tbequipo.insertarEquipo(txtNombreEq.getText().toString(), txtPaisEq.getText().toString(), txtCorreoEq.getText().toString());

                    if (id > 0) {
                        Toast.makeText(regEquipo.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(regEquipo.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(regEquipo.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombreEq.setText("");
        txtPaisEq.setText("");
        txtCorreoEq.setText("");
    }
}