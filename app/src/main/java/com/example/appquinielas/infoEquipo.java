package com.example.appquinielas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import OpenHelper.tblEquipo;
import entidades.Equipos;

public class infoEquipo extends AppCompatActivity {

    EditText txtNombreEq, txtPaisEq, txtCorreoEq;
    Button btnGuardarEq;
    FloatingActionButton fabEdit, fabDelete;

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
        fabDelete = findViewById(R.id.fabEliminar);
        btnGuardarEq = findViewById(R.id.btnGuardarEq);
        btnGuardarEq.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("idEquipo");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("idEquipo");
        }

        final tblEquipo dbEq = new tblEquipo(infoEquipo.this);
        equipo = dbEq.verEquipo(id);

        if(equipo != null){
            txtNombreEq.setText(equipo.getnombreEq());
            txtPaisEq.setText(equipo.getpaisEq());
            txtCorreoEq.setText(equipo.getcorreoEq());
            txtNombreEq.setInputType(InputType.TYPE_NULL);
            txtPaisEq.setInputType(InputType.TYPE_NULL);
            txtCorreoEq.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(infoEquipo.this, editarEquipo.class);
                intent.putExtra("idEquipo", id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(infoEquipo.this);
                builder.setMessage("¿Desea eliminar este Equipo?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbEq.eliminarEquipo(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, vistaEquipos.class);
        startActivity(intent);
    }
}