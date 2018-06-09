package br.com.senacrs.air.view.menu;

public class VendaMenu {

    public static final int OP_COMPRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Comprar Passagem\n"
                + "2- Listar Passagens\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
