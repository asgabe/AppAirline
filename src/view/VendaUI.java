package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import model.Venda;
import util.Console;

import model.Voo;
import model.Cliente;

import repositorio.RepositorioAvioes;
import repositorio.RepositorioClientes;
import repositorio.RepositorioVendas;
import repositorio.RepositorioVoos;
import util.DateTimeUtil;

import view.menu.VendaMenu;

public class VendaUI {
    
    public void executar() {
        
        int opcao = 0;
        
        do {
            System.out.println(VendaMenu.getOpcoes());
            
            opcao = Console.scanInt("Digite sua opção:");
            
            switch (opcao) {
                case VendaMenu.OP_COMPRAR:
                    comprarPassagem();
                    break;
                case VendaMenu.OP_LISTAR:
//                    mostrarCliente();
                    break;
                case VendaMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != VendaMenu.OP_VOLTAR);
    }
    
    private void comprarPassagem() {
        System.out.println("\n Relacione o CLIENTE abaixo para a venda");
        new ClienteUI().mostrarClientes();
        
        int codCliente = Console.scanInt("RG do cliente: ");
        
        if (!RepositorioClientes.getInstance().clienteExiste(codCliente)) {
            System.out.println("Codigo invalido!");
  
            return;
        }
        
        System.out.println("\n Relacione hoario do Voo para a venda");
        new VooUI().listarHorarios();

        String horarioVoo = Console.scanString("Entre com o HORARIO do voo (dd/mm/aaaa hh:mm):: ");
        LocalDateTime horario;
        
        horario = DateTimeUtil.stringToDateTime(horarioVoo);

    }
}
