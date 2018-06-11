package br.com.senacrs.air.negocio;

import br.com.senacrs.air.dominio.Aviao;
import br.com.senacrs.air.dao.impl_BD.AviaoDAO_BD;
import java.util.List;
import br.com.senacrs.air.dao.AviaoDAO;

public class AviaoNegocio {

    private AviaoDAO aviaoDAO;

    public AviaoNegocio() {
        aviaoDAO = new AviaoDAO_BD();
    }

    public void salvar(Aviao a) throws NegocioException {
        this.validarCamposObrigatorios(a);
        aviaoDAO.salvar(a);
    }

    public List<Aviao> listar() {
        return (aviaoDAO.listar());
    }

    public void deletar(Aviao aviao) throws NegocioException {
        if (aviao == null) {
            throw new NegocioException("Avião não existe!");
        }
        aviaoDAO.deletar(aviao);
    }

    public void atualizar(Aviao aviao) throws NegocioException {
        if (aviao == null) {
            throw new NegocioException("Avião não existe!");
        }
        this.validarCamposObrigatorios(aviao);
        aviaoDAO.atualizar(aviao);
    }

    public Aviao procurarPorCodigo(int codigo) throws NegocioException {
        if (codigo < 0) {
            throw new NegocioException("Campo CODIGO não informado");
        }
        
        Aviao aviao = aviaoDAO.procurarPorCodigo(codigo);
        if (aviao == null) {
            throw new NegocioException("Avião não encontrado");
        }
        
        return (aviao);
    }

    public List<Aviao> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        
        return(aviaoDAO.listarPorNome(nome));
    }

    public boolean aviaoExiste(int codigo) {
        Aviao aviao = aviaoDAO.procurarPorCodigo(codigo);
        
        return (aviao != null);
    }

    private void validarCamposObrigatorios(Aviao a) throws NegocioException {
        if (a.getQtdAssento() < 0) {
            throw new NegocioException("Campo ASSENTOS não informado");
        }

        if (a.getNome() == null || a.getNome().isEmpty()) {
            throw new NegocioException("Campo nome não informado");
        }
    }
}
