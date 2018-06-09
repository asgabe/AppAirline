package br.com.senacrs.air.model;

import java.time.LocalDateTime;

public class Voo implements Comparable<Voo> {

    private String origem;
    private String destino;
    private Aviao aviao;
    private LocalDateTime horario;

    public Voo(String origem, String destino, Aviao aviao, LocalDateTime horario) {
        this.origem = origem;
        this.destino = destino;
        this.aviao = aviao;
        this.horario = horario;
    }

    public LocalDateTime getHorario() {
        return horario;
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
