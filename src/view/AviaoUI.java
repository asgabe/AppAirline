package view;

import java.time.format.DateTimeParseException;
import model.Aviao;
import repositorio.RepositorioAvioes;
import util.Console;
import view.menu.AviaoMenu;


public class AviaoUI {
    public void executar() {
        
        int opcao = 0;
        
        do {
            System.out.println(AviaoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case AviaoMenu.OP_CADASTRAR:
                    cadastrarAvioes();
                    break;
                case AviaoMenu.OP_LISTAR:
                    mostrarAvioes();
                    break;
                case AviaoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != AviaoMenu.OP_VOLTAR);
    }
    
    
    private void cadastrarAvioes() {
        
        String nome = Console.scanString("Nome: ");
        
        if (RepositorioAvioes.getInstance().aviaoExisteNome(nome)) {
            System.out.println("Avião já existente no cadastro");
        } else {
            
            int qtdAssento = Console.scanInt("Quantidade de assentos: ");
            RepositorioAvioes.getInstance().add(new Aviao(nome, qtdAssento));
            System.out.println("Avião " + nome + " cadastrado com sucesso!");
        }
    }
    
    public void mostrarAvioes() {
        
        if (RepositorioAvioes.getInstance().estaVazio()) {
            System.out.println("-----------------------------");
            System.out.println("Não ha aviões cadastrados");
            System.out.println("-----------------------------\n");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "COD") + "\t"
                    + String.format("%-20s", "|QTD ASSENTOS") + "\t"
                    + String.format("%-20s", "|NOME"));

            for (Aviao aviao : RepositorioAvioes.getInstance().getAvioes()) {
                System.out.println(String.format("%-10s", aviao.getCodigo()) + "\t"
                        + String.format("%-20s", "|" + aviao.getQtdAssento()) + "\t"
                        + String.format("%-20s", "|" + aviao.getNome()));
            }
        }

    }
}
