package com.example.appquinielas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import OpenHelper.tblEquipo;
import adaptadores.ListaEquiposAdaptador;
import entidades.Equipos;

public class vistaEquipos extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaEquipos;
    ArrayList<Equipos> listArrayEquipos;
    FloatingActionButton fabNuevo;
    ListaEquiposAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_equipos);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaEquipos = findViewById(R.id.listaEquipos);
        fabNuevo = findViewById(R.id.favNuevo);
        listaEquipos.setLayoutManager(new LinearLayoutManager(this));

        tblEquipo dbContactos = new tblEquipo(vistaEquipos.this);

        listArrayEquipos = new ArrayList<>();

        adaptador = new ListaEquiposAdaptador(dbContactos.mostrarEquipos());
        listaEquipos.setAdapter(adaptador);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegEquipo();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    private void nuevoRegEquipo(){
        Intent intent = new Intent(this, regEquipo.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptador.filtrado(s);
        return false;
    }
}