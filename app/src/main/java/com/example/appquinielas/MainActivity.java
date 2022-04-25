package com.example.appquinielas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvRegistro;
    Button btnIngresar;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDQuinielas",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegistro = (TextView) findViewById(R.id.tvRegistro);
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), registro.class);
                startActivity(i);
            }
        });

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtUsu = (EditText) findViewById(R.id.txtUsuario);
                EditText txtPas = (EditText) findViewById(R.id.txtPassword);

                try {
                    Cursor cursor =helper.ConsultarUsuario(txtUsu.getText().toString(), txtPas.getText().toString());
                    if(cursor.getCount()>0){
                        Intent i = new Intent(getApplicationContext(), principal.class);
                        startActivity(i);

                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña invalido.", Toast.LENGTH_LONG).show();

                    }
                    txtUsu.setText("");
                    txtPas.setText("");
                    txtUsu.findFocus();

                }catch (SQLException e){
                    e.printStackTrace();

                }

            }
        });
    }
}