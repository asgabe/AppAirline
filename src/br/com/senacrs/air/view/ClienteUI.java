package br.com.senacrs.air.view;

import java.time.format.DateTimeParseException;
import br.com.senacrs.air.dominio.Cliente;
import br.com.senacrs.air.negocio.ClienteNegocio;
import br.com.senacrs.air.negocio.NegocioException;
import br.com.senacrs.air.repositorio.RepositorioClientes;
import br.com.senacrs.air.util.Console;
import br.com.senacrs.air.view.menu.ClienteMenu;
import java.util.InputMismatchException;

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
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
//                        mostrarClientes();
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

    private void cadastrarCliente() {
        
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
}
