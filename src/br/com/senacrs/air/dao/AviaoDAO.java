
package br.com.senacrs.air.dao;

import java.util.List;
import br.com.senacrs.air.dominio.Aviao;

public interface AviaoDAO extends Dao<Aviao>{
    public Aviao procurarPorCodigo(int codigo);
    public List<Aviao> listarPorNome(String nome);
    
    
}
