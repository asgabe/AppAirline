package repositorio;

import java.util.ArrayList;
import model.Aviao;
import java.util.List;

public class RepositorioAvioes {

    private List<Aviao> avioes;
    private static RepositorioAvioes instance = null;

    private RepositorioAvioes() {
        avioes = new ArrayList<Aviao>();
    }

    public static RepositorioAvioes getInstance() {

        if (instance == null) {
            instance = new RepositorioAvioes();
        }

        return instance;
    }

    public boolean add(Aviao aviao) {
        return (avioes.add(aviao));
    }

    public boolean estaVazio() {
        return avioes.isEmpty();
    }

    public List<Aviao> getAvioes() {
        return avioes;
    }

    public boolean aviaoExiste(int cod) {
        for (Aviao aviao : avioes) {
            if (aviao.getCodigo() == cod) {
                return true;
            }
        }
        return false;
    }
    
    public boolean aviaoExisteNome(String nome) {
        for (Aviao aviao : avioes) {
            if (aviao.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public Aviao buscarAviao(int codigo) {
        for (Aviao aviao : avioes) {
            if (aviao.getCodigo() == codigo) {
                return aviao;
            }
        }
        return null;
    }
    
    public int getQtdAssentosAviao(int codigo) {
        for (Aviao aviao : avioes) {
            if (aviao.getCodigo() == codigo) {
                return aviao.getQtdAssento();
            }
        }
        
        return 0;
    }
}
