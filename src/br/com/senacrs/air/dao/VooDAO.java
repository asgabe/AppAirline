
package br.com.senacrs.air.dao;

import java.util.List;
import br.com.senacrs.air.dominio.Voo;

public interface VooDAO extends Dao<Voo>{
    public Voo procurarPorCodigo(int codigo);
    public List<Voo> listarPorDestino(String nome);
    
    
}
