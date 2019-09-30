package com.example.lpoo2_gastospessoais;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class RegistrosAdapter extends ArrayAdapter {
    private List<Registros> list;
    private Context context;

    public RegistrosAdapter( Context context,  List<Registros> objects) {
        super(context, 0, objects);
        this.list = objects;
        this.context = context;
    }

    public View getView(final int position, final View convertView, ViewGroup parent){
        View listItemView = convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_registro, parent, false);
        }

        final Registros current = (Registros) getItem(position);
        TextView textId = (TextView) listItemView.findViewById(R.id.textId);
        TextView data = (TextView) listItemView.findViewById(R.id.textData);
        TextView descricao = (TextView) listItemView.findViewById(R.id.textDescricao);
        TextView valor = (TextView) listItemView.findViewById(R.id.textValor);
        textId.setText(String.valueOf(current.getId()));
        data.setText(current.getData().toString());
        descricao.setText(current.getDescricao().toString());
        valor.setText(String.valueOf(current.getValor()));

        final RegistrosDAO registrosDAO = new RegistrosDAO(getContext());

        ImageButton btnDelete = (ImageButton) listItemView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle(R.string.msgExclusao)
                        .setMessage(R.string.msgExclusaoConfirma)
                        .setNegativeButton(R.string.msgNao,null)
                        .setPositiveButton(R.string.msgSim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                registrosDAO.deletar(current);
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();

            }
        });

        ImageButton btnEdit = (ImageButton) listItemView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return listItemView;

    }
}
