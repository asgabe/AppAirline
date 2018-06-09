package br.com.senacrs.air.view.menu;

public class AviaoMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Avião\n"
                + "2- Listar Aviões\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
