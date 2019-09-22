package com.example.lpoo2_gastospessoais;
import android.app.DatePickerDialog;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    DatePickerDialog datePicker;
    EditText editData;
    EditText editDescricao;
    EditText editValor;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonIncluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        editData = (EditText) findViewById(R.id.editData);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        editValor = (EditText) findViewById(R.id.editValor);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        buttonIncluir = (Button) findViewById(R.id.buttonIncluir);

        editData.setInputType(InputType.TYPE_NULL);
        editData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Calendar calendario = Calendar.getInstance();
                int dia = calendario.get(Calendar.DAY_OF_MONTH);
                int mes = calendario.get(Calendar.MONTH);
                int ano = calendario.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(InputActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                                editData.setText(dia + "/" + (mes+1) + "/" + ano);
                            }
                        },ano,mes,dia);
                datePicker.show();
            }
        });

        buttonIncluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioSelectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioSelectedId);

                DbHelper db = new DbHelper(getBaseContext());
                Registros registro = new Registros(editData.getText().toString(),Integer.valueOf(radioButton.getTag().toString()),editDescricao.getText().toString(),Float.valueOf(editValor.getText().toString()));
                RegistrosDAO registrosDAO = new RegistrosDAO(getBaseContext());
                String msg = registrosDAO.save(registro);
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}