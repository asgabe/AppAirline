package br.com.senacrs.air.view;

import br.com.senacrs.air.util.Console;
import br.com.senacrs.air.view.menu.MainMenu;

public class MainUI {

    public void executar() {
        
        int opcao = 0;
        
        do {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            
            switch (opcao) {
                case MainMenu.OP_CLIENTES:
//                    new ClienteUI().executar();
                    break;
                case MainMenu.OP_AVIOES:
                    new AviaoUI().executar();
                    break;
                case MainMenu.OP_VOOS:
                    new VooUI().executar();
                    break;
                case MainMenu.OP_PASSAGEM:
                    new VendaUI().executar();
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}
