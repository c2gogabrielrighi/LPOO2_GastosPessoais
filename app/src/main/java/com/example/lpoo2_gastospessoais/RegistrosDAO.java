package com.example.lpoo2_gastospessoais;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class RegistrosDAO {

    private SQLiteDatabase db;
    private DbHelper banco;


    public RegistrosDAO(Context context){
        banco = new DbHelper(context);
    }

    public String save(Registros registros){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        try{
            int tipo = registros.getTipo();
            float valor = registros.getValor();
            valores=new ContentValues();

            valores.put(banco.C_DATA,registros.getData());
            valores.put(banco.C_DESCRICAO,registros.getDescricao());
            valores.put(banco.C_TIPO,tipo);
            if(tipo == 1){
                valor = valor*(-1);
            }
            valores.put(banco.C_VALOR,valor);

            resultado= db.insert(banco.TABLE,null,valores);
            db.close();
            if (resultado !=-1){

                return "Registro incluido!";
            }
        }catch (SQLException e){
            Log.e("ERRO",e.getMessage());
        }

        return "Erro!";

    }

    public float getSaldo(){
        Cursor cur = db.rawQuery("SELECT SUM(valor) FROM Registros", null);
        if(cur.moveToFirst())
        {
            return cur.getInt(0);
        }
        return Float.valueOf("0.0");
    }

    public float getEntrada(){
        Cursor cur = db.rawQuery("SELECT SUM(valor) FROM Registros WHERE tipo = 0", null);
        if(cur.moveToFirst())
        {
            return cur.getInt(0);
        }
        return Float.valueOf("0.0");
    }

    public float getSaida(){
        Cursor cur = db.rawQuery("SELECT SUM(valor) FROM Registros WHERE tipo = 1", null);
        if(cur.moveToFirst())
        {
            return cur.getInt(0);
        }
        return Float.valueOf("0.0");
    }

    public ArrayList<Registros> list(){
        ArrayList<Registros> lista= new ArrayList<>();
        Cursor cursor;
        String[] campos={DbHelper.C_ID, DbHelper.C_DATA, DbHelper.C_DESCRICAO,DbHelper.C_TIPO,DbHelper.C_VALOR};
        db= banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABLE,campos,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int id= cursor.getInt(0) ;
                String data = cursor.getString(1);
                String descricao = cursor.getString(2);
                int tipo = cursor.getInt(3);
                float valor = cursor.getFloat(4);

                Registros c= new Registros(id,data,tipo,descricao,valor);
                lista.add(c);
            }
            return lista;
        }
        return null;
    }

    public String deletar(Registros c){
        String where = DbHelper.C_ID+ "= " +c.getId();
        db= banco.getReadableDatabase();
        db.delete(DbHelper.TABLE,where,null);
        db.close();
        return "Removido";
    }



}
