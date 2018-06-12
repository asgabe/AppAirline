package br.com.senacrs.air.dominio;

import br.com.senacrs.air.dominio.Voo;
import java.time.LocalDateTime;

public class Voo implements Comparable<Voo> {

    private int id;
    
    private int codigo;
    private String origem;
    private String destino;
    private Aviao aviao;
    private LocalDateTime horario;

    public Voo(int id, int codigo, String origem, String destino, Aviao aviao, LocalDateTime horario) {
        this.id = id;
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.aviao = aviao;
        this.horario = horario;
    }

    public Voo(int codigo, String origem, String destino, Aviao aviao, LocalDateTime horario) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.aviao = aviao;
        this.horario = horario;
    }

    public LocalDateTime getHorario() {
        return horario;
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

    public Aviao getAviao() {
        return aviao;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public int compareTo(Voo o) {
        return (this.getHorario().compareTo(o.getHorario()));
    }

}
