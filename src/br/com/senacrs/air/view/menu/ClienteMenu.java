package br.com.senacrs.air.view.menu;

public class ClienteMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_DELETAR = 4;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Cliente\n"
                + "2- Listar Cliente\n"
                + "3- Atualizar Cliente\n"
                + "4- Deletar Cliente\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
