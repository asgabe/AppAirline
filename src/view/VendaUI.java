package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
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
                    listarPassagens();
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
            System.out.println("RG invalido!");

            return;
        }
        
        Cliente cliente = RepositorioClientes.getInstance().buscarCliente(codCliente);
        
        System.out.println("\n Relacione horario do Voo para a venda");
        new VooUI().listarHorarios();

        String dataHora = Console.scanString("Data/Hora do voo (dd/mm/aaaa hh:mm):");
        LocalDateTime horario;

        try {
            horario = DateTimeUtil.stringToDateTime(dataHora);

            if (RepositorioVoos.getInstance().vooExiste(horario)) {
                Voo voo = RepositorioVoos.getInstance().buscarVoo(horario);
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                
                RepositorioVendas.getInstance().add(new Venda(cliente, voo, timeStamp));
            } else {
                System.out.println("Nesse horario não existe voos.");
            }

        } catch (DateTimeParseException ex) {
            System.out.println("Data ou hora no formato inválido!");
        }
    }
    
    private void listarPassagens() {
        if (RepositorioVendas.getInstance().getVendas().size() <= 0) {
            System.out.println("-----------------------------");
            System.out.println("Não ha VENDAS registadas");
            System.out.println("-----------------------------\n"); 
        } else {
            System.out.println("VENDAS REGISTRADAS:");
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "COD") + "\t"
                    + String.format("%-20s", "|RG CLIENTE")
                    + String.format("%-20s", "|DATA/HORA")
                    + String.format("%-20s", "|VOO"));
            
            for (Venda venda : RepositorioVendas.getInstance().getVendas()) {
                System.out.println(String.format("%-20s", venda.getCodigo())
                        + "\t" + String.format("%-20s", venda.getCliente().getRg())
                        + "\t" + String.format("%-20s", venda.getData())
                        + "\t" + String.format("%-20s", venda.getVoo().getAviao().getNome()));
            }
        }
    }
}
