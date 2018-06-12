package br.com.senacrs.air.repositorio;

import br.com.senacrs.air.dominio.Voo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;

public class RepositorioVoos {

    private List<Voo> listaVoos;
    private static RepositorioVoos instance = null;

    private RepositorioVoos() {
        listaVoos = new ArrayList<Voo>();
    }

    public static RepositorioVoos getInstance() {

        if (instance == null) {
            instance = new RepositorioVoos();
        }

        return instance;
    }

    public boolean vooExiste(LocalDateTime horario) {
        for (Voo hor : listaVoos) {
            if (hor.getHorario().equals(horario)) {
                return true;
            }
        }
        return false;
    }

    public boolean addHorario(Voo horario) {
        if (vooExiste(horario.getHorario())) {
            return false;
        }

        return (listaVoos.add(horario));
    }

    public List<Voo> getHorarios() {
        Collections.sort(listaVoos);

        return listaVoos;
    }

    public Voo buscarVoo(LocalDateTime horario) {
        for (Voo hora : listaVoos) {
            if (hora.getHorario().equals(horario)) {
                return hora;
            }
        }

        return null;
    }

    public boolean hasAssentos() {
        for (Voo assento : listaVoos) {
            if (assento.getAviao().getQtdAssento() > 0) {
                return true;
            }
        }
        return false;
    }
}
