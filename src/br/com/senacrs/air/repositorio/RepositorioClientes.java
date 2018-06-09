package br.com.senacrs.air.repositorio;

import java.util.ArrayList;
import java.util.List;
import br.com.senacrs.air.model.Cliente;

public class RepositorioClientes {

    private List<Cliente> clientes;
    private static RepositorioClientes instance = null;

    private RepositorioClientes() {
        clientes = new ArrayList<Cliente>();
    }

    public static RepositorioClientes getInstance() {
        if (instance == null) {
            instance = new RepositorioClientes();
        }
        return instance;
    }

    public boolean add(Cliente cliente) {
        return (clientes.add(cliente));
    }

    public boolean estaVazio() {
        return clientes.isEmpty();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public boolean clienteExiste(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return cliente;
            }
        }
        return null;
    }
}
