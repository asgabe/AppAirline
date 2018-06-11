package br.com.senacrs.air.view;

import br.com.senacrs.air.dominio.Cliente;
import br.com.senacrs.air.negocio.ClienteNegocio;
import br.com.senacrs.air.negocio.NegocioException;
import br.com.senacrs.air.util.Console;
import br.com.senacrs.air.view.menu.ClienteMenu;
import java.util.InputMismatchException;
import java.util.List;

public class ClienteUI {

    private ClienteNegocio clienteNegocio;
    
    public ClienteUI() {
        clienteNegocio = new ClienteNegocio();
    }
    
    public void menu() {
        
        
        int opcao = 1;
        
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        CREATE();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        READ();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        UPDATE();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        DELETE();
                        break;
                    case ClienteMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");

                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }

    private void CREATE() {
        
        String rg = Console.scanString("RG: ");
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("Telefone (51) 99999-9999: ");
        try {
            clienteNegocio.salvar(new Cliente(rg, nome, telefone));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    public void READ() {
        List<Cliente> listaClientes = clienteNegocio.listar();
        this.READ(listaClientes);
    }
    
    private void UPDATE() {
        String rg = Console.scanString("RG do cliente a ser alterado: ");
        try {
            Cliente cli = clienteNegocio.procurarPorRg(rg);
            this.mostrarCliente(cli);

            System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone (99) 99999-9999: ");
            
            if (!nome.isEmpty()) {
                cli.setNome(nome);
            }
            if (!telefone.isEmpty()) {
                cli.setTelefone(telefone);
            }

            clienteNegocio.atualizar(cli);
            System.out.println("Cliente " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void DELETE() {
        String rg = Console.scanString("RG do paciente a ser deletado: ");
        try {
            Cliente cli = clienteNegocio.procurarPorRg(rg);
            this.mostrarCliente(cli);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                clienteNegocio.deletar(cli);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void READ(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "RG") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TELEFONE"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getTelefone()));
            }
        }
    }
    
    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("RG: " + c.getRg());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("-----------------------------");
    }
}
