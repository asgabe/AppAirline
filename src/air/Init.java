
package air;

import java.util.Random;

import model.Voo;
import model.Aviao;
import model.Cliente;

import repositorio.RepositorioVoos;
import repositorio.RepositorioAvioes;
import repositorio.RepositorioClientes;
import util.DateTimeUtil;

public class Init {
    
    Random rand = new Random();
    
    public void executar () {        
        int rg = 0;
        int cont = 0;
        
        while (cont < 5) {
            rg = rand.nextInt(10000) + 1;
            RepositorioClientes.getInstance().add(new Cliente("username", rg, "(99) 99999-9999"));
            cont++;
        }
        
        
        RepositorioAvioes.getInstance().add(new Aviao("TAM", 200));
        RepositorioAvioes.getInstance().add(new Aviao("GOL", 100));
        RepositorioAvioes.getInstance().add(new Aviao("AZUL", 300));
    }
}
