package br.com.senacrs.air.negocio;

import br.com.senacrs.air.dominio.Voo;
import br.com.senacrs.air.dao.impl_BD.VooDAO_BD;
import java.util.List;
import br.com.senacrs.air.dao.VooDAO;

public class VooNegocio {

    private VooDAO vooDAO;

    public VooNegocio() {
        vooDAO = new VooDAO_BD();
    }

    public void salvar(Voo v) throws NegocioException {
        this.validarCamposObrigatorios(v);
        vooDAO.salvar(v);
    }

    public List<Voo> listar() {
        return (vooDAO.listar());
    }

    public void deletar(Voo voo) throws NegocioException {
        if (voo == null) {
            throw new NegocioException("Voo não existe!");
        }
        vooDAO.deletar(voo);
    }

    public void atualizar(Voo voo) throws NegocioException {
        if (voo == null) {
            throw new NegocioException("Voo não existe!");
        }
        this.validarCamposObrigatorios(voo);
        vooDAO.atualizar(voo);
    }

    public Voo procurarPorCodigo(int codigo) throws NegocioException {
        if (codigo < 0) {
            throw new NegocioException("Campo CODIGO não informado");
        }
        
        Voo voo = vooDAO.procurarPorCodigo(codigo);
        if (voo == null) {
            throw new NegocioException("Voo não encontrado");
        }
        
        return (voo);
    }

    public List<Voo> listarPorDestino(String destino) throws NegocioException {
        if (destino == null || destino.isEmpty()) {
            throw new NegocioException("Campo destino não informado");
        }
        
        return(vooDAO.listarPorDestino(destino));
    }

    public boolean vooExiste(int codigo) {
        Voo voo = vooDAO.procurarPorCodigo(codigo);
        
        return (voo != null);
    }

    private void validarCamposObrigatorios(Voo v) throws NegocioException {
        if (v.getDestino() == null || v.getDestino().isEmpty()) {
            throw new NegocioException("Campo DESTINO nao informado");
        }
        
        if (v.getOrigem() == null || v.getOrigem().isEmpty()) {
            throw new NegocioException("Campo ORIGEM nao informado");
        }
    }
}
