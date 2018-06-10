package br.com.senacrs.air.dominio;

import br.com.senacrs.air.model.*;
import java.util.Objects;

public class Cliente {

    private int id;
    private String rg, nome, telefone;

    public Cliente(int id, String rg, String nome, String telefone) {
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }
    
    public Cliente(String rg, String nome, String telefone) {
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
