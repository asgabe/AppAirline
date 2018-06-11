package br.com.senacrs.air.dominio;

public class Aviao {

    private int id;
    private int codigo;
    private String nome;
    private int qtdAssento;

    public Aviao(int id, int codigo, String nome, int qtdAssento) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdAssento = qtdAssento;
    }

    public Aviao(int codigo, String nome, int qtdAssento) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdAssento = qtdAssento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public void atualizaAssentos() {
        qtdAssento = qtdAssento - 1; 
    }

    public void setQtdAssento(int qtdAssento) {
        this.qtdAssento = qtdAssento;
    }
    
    public int getQtdAssento() {
        return qtdAssento;
    }
}
