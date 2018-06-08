package repositorio;

import java.util.List;
import java.util.ArrayList;


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
        venda.getVoo().getAviao().atualizaAssentos();
        
        return (vendas.add(venda));
    }
    
    public List<Venda> getVendas() {
        return vendas;
    }
}
