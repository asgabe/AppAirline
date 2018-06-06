package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

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

    public boolean clienteExiste(int rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg() == rg) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(int rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg() == rg) {
                return cliente;
            }
        }
        return null;
    }
}
