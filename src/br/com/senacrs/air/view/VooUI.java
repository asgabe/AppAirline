package br.com.senacrs.air.view;

import br.com.senacrs.air.util.Console;
import br.com.senacrs.air.util.DateTimeUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import br.com.senacrs.air.model.Voo;
import br.com.senacrs.air.model.Aviao;
import br.com.senacrs.air.view.menu.VooMenu;
import br.com.senacrs.air.repositorio.RepositorioVoos;
import br.com.senacrs.air.repositorio.RepositorioAvioes;

public class VooUI {

    public void executar() {

        int opcao = 0;

        do {
            System.out.println(VooMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");

            switch (opcao) {
                case VooMenu.OP_NOVO:
                    cadastrarVoo();
                    break;
                case VooMenu.OP_LISTAR:
                    listarHorarios();
                    break;
                case VooMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != VooMenu.OP_VOLTAR);
    }

    private void cadastrarVoo() {

        System.out.println("Relacione o avião abaixo para o voo: ");

        new AviaoUI().mostrarAvioes();

        int cod = Console.scanInt("Escolha o CODIGO do avião: ");

        if (RepositorioAvioes.getInstance().aviaoExiste(cod)) {

            Aviao aviao = RepositorioAvioes.getInstance().buscarAviao(cod);

            String origem = Console.scanString("Origem:");
            String destino = Console.scanString("Destino:");

            String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
            LocalDateTime horario;

            try {
                horario = DateTimeUtil.stringToDateTime(dataHora);

                if (RepositorioVoos.getInstance().vooExiste(horario)) {
                    System.out.println("Nesse horario já existe um voo");
                } else {
                    RepositorioVoos.getInstance().addHorario(new Voo(origem, destino, aviao, horario));
                    System.out.println("Voo agendado com sucesso!");
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Data ou hora no formato inválido!");
            }
        } else {
            System.out.println("Codigo não encontrado!");
        }
    }

    public void listarHorarios() {
        if (RepositorioVoos.getInstance().getHorarios().size() <= 0) {
            System.out.println("-----------------------------");
            System.out.println("Nao ha horarios cadastrados");
            System.out.println("-----------------------------\n");
        } else {
            System.out.println("HORARIOS:");
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "DATA/HORA") + "\t"
                    + String.format("%-20s", "|NOME"));

            for (Voo horario : RepositorioVoos.getInstance().getHorarios()) {
                System.out.println(String.format("%-20s", DateTimeUtil.dateTimeToString(horario.getHorario()))
                        + "\t" + String.format("%-20s", horario.getAviao().getNome()));
            }
        }
    }
}
