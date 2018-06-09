package br.com.senacrs.air.view.menu;

public class MainMenu {

    public static final int OP_CLIENTES = 1;
    public static final int OP_AVIOES = 2;
    public static final int OP_VOOS = 3;
    public static final int OP_PASSAGEM = 4;
    public static final int OP_HISTORICO = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Cliente\n"
                + "2- Menu Aviões\n"
                + "3- Menu Voos\n"
                + "4- Menu Passagem\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}
