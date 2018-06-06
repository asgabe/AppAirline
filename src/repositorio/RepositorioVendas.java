package repositorio;

import java.util.ArrayList;
import java.util.List;

import model.Venda;

public class RepositorioVendas {

    private List<Venda> vendas;
    private static RepositorioVendas instance = null;

    private RepositorioVendas() {
        vendas = new ArrayList<Venda>();
    }

    public static RepositorioVendas getInstance() {

        if (instance == null) {
            instance = new RepositorioVendas();
        }

        return instance;
    }
    
    public boolean add(Venda venda) {
        return (vendas.add(venda));
    }
}
