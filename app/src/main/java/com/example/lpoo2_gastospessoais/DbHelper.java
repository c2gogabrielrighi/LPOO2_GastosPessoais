package com.example.lpoo2_gastospessoais;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DbHelper extends SQLiteOpenHelper {

    /*public static final String NOME_BANCO ="BaseCadastros.db";
    public static final String TABELA ="Cadastros";
    public static final String ID ="_id";
    public static final String DESPESA ="despesa";
    public static final String VALOR ="valor";
    public static final String DIA ="dia";
    public static final int VERSAO = 1;*/



    public static final String DB_NAME = "BaseFinanceiro.db";
    public static final String TABLE = "Registros";
    public static final String C_ID = "_id";
    public static final String C_DATA = "data";
    public static final String C_TIPO = "tipo";
    public static final String C_DESCRICAO = "descricao";
    public static final String C_VALOR = "valor";
    public static final int VERSION = 1;

    public DbHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate = "CREATE TABLE " + TABLE + " (" +
                C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_DATA + " DATE NOT NULL," +
                C_TIPO + " INTEGER NOT NULL," +
                C_DESCRICAO + " TEXT NOT NULL," +
                C_VALOR + " FLOAT NOT NULL DEFAULT 0" +
                ");";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

