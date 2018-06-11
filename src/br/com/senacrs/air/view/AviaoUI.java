package br.com.senacrs.air.view;

import br.com.senacrs.air.dominio.Aviao;
import br.com.senacrs.air.negocio.AviaoNegocio;
import br.com.senacrs.air.negocio.NegocioException;
import br.com.senacrs.air.util.Console;
import br.com.senacrs.air.view.menu.AviaoMenu;
import java.util.InputMismatchException;
import java.util.List;


public class AviaoUI {
    
    private AviaoNegocio aviaoNegocio;
    
    public AviaoUI() {
        aviaoNegocio = new AviaoNegocio();
    }
    
    public void menu() {
        
        
        int opcao = 1;
        
        do {
            try {
                System.out.println(AviaoMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case AviaoMenu.OP_CADASTRAR:
                        CREATE();
                        break;
                    case AviaoMenu.OP_LISTAR:
                        READ();
                        break;
                    case AviaoMenu.OP_ATUALIZAR:
                        UPDATE();
                        break;
                    case AviaoMenu.OP_DELETAR:
                        DELETE();
                        break;
                    case AviaoMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");

                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }
        } while (opcao != AviaoMenu.OP_VOLTAR);
    }
    
    
    private void CREATE() {
        
        int codigo = Console.scanInt("Codigo:");
        String nome = Console.scanString("Nome: ");
        int qtdAssento = Console.scanInt("Quantidade de assento: ");

        try {
            aviaoNegocio.salvar(new Aviao(codigo, nome, qtdAssento));
            System.out.println("Avião " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void READ() {
        List<Aviao> listaAvioes = aviaoNegocio.listar();
        this.READ(listaAvioes);
    }
    
    private void UPDATE() {
        int codigo = Console.scanInt("Codigo do avião a ser alterado: ");
        
        try {
            Aviao avi = aviaoNegocio.procurarPorCodigo(codigo);
            this.mostrarAvioes(avi);

            System.out.println("Digite os dados do avião que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            int qtdAssento = Console.scanInt("Quantidade de assentos: ");
            
            if (!nome.isEmpty()) {
                avi.setNome(nome);
            }
            if (qtdAssento > 0) {
                avi.setQtdAssento(qtdAssento);
            }

            aviaoNegocio.atualizar(avi);
            System.out.println("Avião " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void READ(List<Aviao> listaAvioes) {
        if (listaAvioes.isEmpty()) {
            System.out.println("Aviões não encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CODIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|QUANTIDADE DE ASSENTOS"));
            for (Aviao aviao : listaAvioes) {
                System.out.println(String.format("%-10s", aviao.getCodigo()) + "\t"
                        + String.format("%-20s", "|" + aviao.getNome()) + "\t"
                        + String.format("%-20s", "|" + aviao.getQtdAssento()));
            }
        }
    }
    
    private void DELETE() {
        int codigo = Console.scanInt("Codigo do avião a ser deletado: ");
        try {
            Aviao avi = aviaoNegocio.procurarPorCodigo(codigo);
            this.mostrarAvioes(avi);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse avião?")) {
                aviaoNegocio.deletar(avi);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
     
    private void mostrarAvioes(Aviao a) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("Codigo: " + a.getCodigo());
        System.out.println("Nome: " + a.getNome());
        System.out.println("Quantidade de assentos: " + a.getQtdAssento());
        System.out.println("-----------------------------");
    }
}
