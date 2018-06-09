package br.com.senacrs.air;

import br.com.senacrs.air.view.ClienteUI;
import br.com.senacrs.air.view.MainUI;

/*
 * PT1 
 *
 * Cadastro de Clientes: anota-se para cada cliente o RG, nome e telefone.
 *
 * Cadastro de aviões: anota-se para cada avião um código, o nome do avião e a quantidade de assentos.
 *
 * Cadastro de vôos: anota-se para o vôo a origem, destino, o horário do vôo (LocalTime) e o avião.
 *
 * Venda de passagens: registra uma venda, relacionando o cliente, vôo e a data/hora da compra (LocalDateTime), realizando o controle da quantidade de assentos.
 *
 * Relatórios de Venda: por clientes, passageiros, origem, destino, períodos de vôos, etc.
*/

/*
 * PT2
 *
 * Utilizando JDBC, realize um CRUD (completo) de alguma entidade do seu trabalho. Utilize o 
 * padrão DAO e os conceitos de arquitetura visto em aula (MVC, RN).Em caso de trabalhos (T2) em 
 * dupla, os alunos deverão utilizar entidades diferentes (ex: sala/filme, cliente/tipoServico, cliente/avião,  cliente/produto)
*/


public class Aeroporto {

    public static void main(String[] args) {
        // new MainUI().executar();
        new ClienteUI().menu();
    }
}
