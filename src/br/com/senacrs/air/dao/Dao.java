package br.com.senacrs.air.dao;

import java.util.List;

public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T cliente);
    public void atualizar(T cliente);
    public List<T> listar();
    public T procurarPorId(int id);
}
