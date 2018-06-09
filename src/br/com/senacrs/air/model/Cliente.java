package br.com.senacrs.air.model;

import java.util.Objects;

public class Cliente {

    private String rg;
    private String nome;
    private String telefone;

    public Cliente(String rg, String nome ,String telefone) {
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String geTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.rg);
        return hash;
    }

    @Override
    public String toString() {
        return rg + " - " + nome + ", " + telefone;
    }
}
