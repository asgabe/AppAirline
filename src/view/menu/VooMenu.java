package view.menu;

public class VooMenu {

    public static final int OP_NOVO = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Agendar Voo\n"
                + "2- Listar Horários\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
