package br.com.senacrs.air.model;

public class Aviao {

    private static int CODIGO_GERADO = 1;
    private int codigo;
    private String nome;
    private int qtdAssento;

    public Aviao(String nome, int qtdAssento) {
        this.codigo = generateCodigo();
        this.qtdAssento = qtdAssento;
        this.nome = nome;
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
    
    public int getQtdAssento() {
        return qtdAssento;
    }

    private int generateCodigo() {
        return (CODIGO_GERADO++);
    }
}
