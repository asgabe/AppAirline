//package br.com.senacrs.air.view;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.Calendar;
//import br.com.senacrs.air.model.Venda;
//import br.com.senacrs.air.util.Console;
//
//import br.com.senacrs.air.model.Voo;
//import br.com.senacrs.air.model.Cliente;
//
//import br.com.senacrs.air.repositorio.RepositorioAvioes;
//import br.com.senacrs.air.repositorio.RepositorioClientes;
//import br.com.senacrs.air.repositorio.RepositorioVendas;
//import br.com.senacrs.air.repositorio.RepositorioVoos;
//import br.com.senacrs.air.util.DateTimeUtil;
//
//import br.com.senacrs.air.view.menu.VendaMenu;
//
//public class VendaUI {
//
//    public void executar() {
//
//        int opcao = 0;
//
//        do {
//            System.out.println(VendaMenu.getOpcoes());
//
//            opcao = Console.scanInt("Digite sua opção:");
//
//            switch (opcao) {
//                case VendaMenu.OP_COMPRAR:
//                    comprarPassagem();
//                    break;
//                case VendaMenu.OP_LISTAR:
//                    listarPassagens();
//                    break;
//                case VendaMenu.OP_VOLTAR:
//                    System.out.println("Retornando ao menu principal..");
//                    break;
//                default:
//                    System.out.println("Opção inválida..");
//
//            }
//        } while (opcao != VendaMenu.OP_VOLTAR);
//    }
//
//    private void comprarPassagem() {
//        System.out.println("-----------------------------\n");
//        System.out.println("ENTRE COM O RG DO CLIENTE");
//        System.out.println("-----------------------------\n"); 
//        
////        new ClienteUI().mostrarClientes();
//        String codCliente = Console.scanString("RG: ");
//
//        if (!RepositorioClientes.getInstance().clienteExiste(codCliente)) {
//            System.out.println("RG INVALIDO!");
//            return;
//        }
//        
//        Cliente cliente = RepositorioClientes.getInstance().buscarCliente(codCliente);
//        
//        System.out.println("-----------------------------\n");
//        System.out.println("ENTRE COM O HORARIO DO VOO");
//        System.out.println("-----------------------------\n"); 
//        
//        new VooUI().listarHorarios();
//        String dataHora = Console.scanString("DATA/HORA (dd/mm/aaaa hh:mm):");
//        LocalDateTime horario;
//
//        try {
//            horario = DateTimeUtil.stringToDateTime(dataHora);
//
//            if (RepositorioVoos.getInstance().vooExiste(horario)) {
//                Voo voo = RepositorioVoos.getInstance().buscarVoo(horario);
//                
//                if (RepositorioVoos.getInstance().hasAssentos()) {
//                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
//                    RepositorioVendas.getInstance().add(new Venda(cliente, voo, timeStamp));
//                } else {
//                    System.out.println("VOO LOTADO!");
//                }
//                
//            } else {
//                System.out.println("NESSE HORARIO NÃO EXISTE VOO REGISTRADO!");
//            }
//        } catch (DateTimeParseException ex) {
//            System.out.println("Data ou hora no formato inválido!");
//        }
//    }
//    
//    private void listarPassagens() {
//        if (RepositorioVendas.getInstance().getVendas().size() <= 0) {
//            System.out.println("-----------------------------");
//            System.out.println("Não ha VENDAS registadas");
//            System.out.println("-----------------------------\n"); 
//        } else {
//            System.out.println("VENDAS REGISTRADAS:");
//            System.out.println("-----------------------------\n");
//            System.out.println(String.format("%-20s", "COD") + "\t"
//                    + String.format("%-20s", "|RG")
//                    + String.format("%-20s", "|DATA")
//                    + String.format("%-20s", "|VOO")
//                    + String.format("%-20s", "|QTD ASSENTOS DISPONIVEIS"));
//            
//            for (Venda venda : RepositorioVendas.getInstance().getVendas()) {
//                System.out.println(String.format("%-20s", venda.getCodigo())
//                        + "\t" + String.format("%-20s", venda.getCliente().getRg())
//                        + "\t" + String.format("%-20s", venda.getData())
//                        + "\t" + String.format("%-20s", venda.getVoo().getAviao().getNome())
//                        + "\t" + String.format("%-20s", venda.getVoo().getAviao().getQtdAssento()));
//            }
//        }
//    }
//}
