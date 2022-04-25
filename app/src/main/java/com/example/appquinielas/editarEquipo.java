package com.example.appquinielas;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import OpenHelper.tblEquipo;
import entidades.Equipos;

public class editarEquipo extends AppCompatActivity {

    EditText txtNombreEq, txtPaisEq, txtCorreoEq;
    Button btnGuardarEq;
    FloatingActionButton fabEdit, fabDelete;
    boolean correcto = false;
    Equipos equipo;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_equipo);

        txtNombreEq = findViewById(R.id.txtNomEquipo);
        txtPaisEq = findViewById(R.id.txtPais);
        txtCorreoEq = findViewById(R.id.txtCorreoEquipo);
        fabEdit = findViewById(R.id.fabEditar);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete = findViewById(R.id.fabEliminar);
        fabDelete.setVisibility(View.INVISIBLE);
        btnGuardarEq = findViewById(R.id.btnGuardarEq);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("idEquipo");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("idEquipo");
        }

        tblEquipo dbEq = new tblEquipo(editarEquipo.this);
        equipo = dbEq.verEquipo(id);

        if (equipo != null) {
            txtNombreEq.setText(equipo.getnombreEq());
            txtPaisEq.setText(equipo.getpaisEq());
            txtCorreoEq.setText(equipo.getcorreoEq());
        }

            btnGuardarEq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!txtNombreEq.getText().toString().equals("") && !txtPaisEq.getText().toString().equals("")) {
                        correcto = dbEq.editarEquipo(id, txtNombreEq.getText().toString(), txtPaisEq.getText().toString(),
                                txtCorreoEq.getText().toString());

                        if(correcto){
                            Toast.makeText(editarEquipo.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                            verRegistro();
                        } else {
                            Toast.makeText(editarEquipo.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(editarEquipo.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        private void verRegistro(){
            Intent intent = new Intent(this, infoEquipo.class);
            intent.putExtra("idEquipo", id);
            startActivity(intent);
        }
    }