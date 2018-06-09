package br.com.senacrs.air.model;

import java.time.LocalDate;

public class Venda {

    private static int CODIGO_GERADO = 1;

    private int codigo;
    private String data;

    private Cliente cliente;
    private Voo voo;

    public Venda(Cliente cliente, Voo voo, String data) {
        this.cliente = cliente;
        this.voo = voo;
        this.data = data;
        this.codigo = generateCodigo();
    }

    private int generateCodigo() {
        return (CODIGO_GERADO++);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Voo getVoo() {
        return voo;
    }

}
