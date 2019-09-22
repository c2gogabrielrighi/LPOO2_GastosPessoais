package com.example.lpoo2_gastospessoais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RegistrosAdapter extends ArrayAdapter {
    public RegistrosAdapter( Context context,  List<Registros> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_registro, parent, false);
        }
        Registros current = (Registros) getItem(position);
        TextView data = (TextView) listItemView.findViewById(R.id.textData);
        TextView descricao = (TextView) listItemView.findViewById(R.id.textDescricao);
        TextView valor = (TextView) listItemView.findViewById(R.id.textValor);
        data.setText(current.getData().toString());
        descricao.setText(current.getDescricao().toString());
        valor.setText(String.valueOf(current.getValor()));

        return  listItemView;


    }

}
