package com.example.lpoo2_gastospessoais;

public class Registros {
    private int _id;
    private String data;
    private int tipo;
    private String descricao;
    private float valor;

    public Registros(String data, int tipo, String descricao, float valor) {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Registros(int id, String data, int tipo, String descricao, float valor) {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this._id=id;

    }

    public String getData() { return data; }

    public int getTipo() { return tipo; }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() { return valor; }

    public int getId(){
        return _id;
    }
    @Override
    public String toString() {
        return "Registros{" +
                "data='" + data + '\'' +
                ", descricao=" + descricao +
                ", valor='" + valor + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
