package com.example.lpoo2_gastospessoais;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textSaldo;
    TextView textEntrada;
    TextView textSaida;
    RegistrosDAO registrosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper db = new DbHelper(getBaseContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textSaldo = findViewById(R.id.textSaldo);
        textEntrada = findViewById(R.id.textEntrada);
        textSaida = findViewById(R.id.textSaida);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,InputActivity.class);
                startActivity(it);
            }
        });
        registrosDAO = new RegistrosDAO(getBaseContext());
        ArrayList<Registros> myRegistros = registrosDAO.list();
        final RegistrosAdapter adapter = new RegistrosAdapter(this,myRegistros);
        atualizaTotais();

        ListView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);
        adapter.registerDataSetObserver(observer);

    }

    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            atualizaTotais();
        }
    };

    public void atualizaTotais(){
        textSaldo.setText(String.valueOf(registrosDAO.getSaldo()));
        textEntrada.setText(String.valueOf(registrosDAO.getEntrada()));
        textSaida.setText(String.valueOf(registrosDAO.getSaida()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
