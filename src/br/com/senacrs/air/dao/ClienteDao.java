
package br.com.senacrs.air.dao;

import java.util.List;
import br.com.senacrs.air.dominio.Cliente;

public interface ClienteDao extends Dao<Cliente>{
    public Cliente procurarPorRg(String rg);
    public List<Cliente> listarPorNome(String nome);
    
    
}
