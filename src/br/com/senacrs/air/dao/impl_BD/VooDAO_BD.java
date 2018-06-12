package br.com.senacrs.air.dao.impl_BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.senacrs.air.dominio.Voo;
import br.com.senacrs.air.dao.VooDAO;
import br.com.senacrs.air.util.DateTimeUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VooDAO_BD extends DaoBd<Voo> implements VooDAO {

    @Override
    public void salvar(Voo voo) {
        int id = 0;

        try {
            String sql = "INSERT INTO VOO (codigo, origem, destino, id_aviao, horario) "
                    + "VALUES (?,?,?,?,?)";

            String horario = DateTimeUtil.dateTimeToString(voo.getHorario());
            
            conectarObtendoId(sql);
            comando.setInt(1, voo.getCodigo());
            comando.setString(2, voo.getOrigem());
            comando.setString(3, voo.getDestino());
            comando.setInt(4, voo.getAviao().getId());
            
            comando.setString(5, horario);
            

            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();

            if (resultado.next()) {
                id = resultado.getInt(1);
                voo.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar voo no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Voo voo) {
        try {
            String sql = "DELETE FROM VOO WHERE id = ?";

            conectar(sql);
            comando.setInt(1, voo.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar voo no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Voo voo) {
        try {
            String sql = "UPDATE VOO SET codigo=?, origem=?, destino=?, id_aviao=?, horario=? "
                    + "WHERE id=?";

            String horario = DateTimeUtil.dateTimeToString(voo.getHorario());
            
            conectar(sql);
            comando.setInt(1, voo.getCodigo());
            comando.setString(2, voo.getOrigem());
            comando.setString(3, voo.getDestino());
            comando.setInt(4, voo.getAviao().getId());
            comando.setString(5, horario);
            
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar voo no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM CLIENTE";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg"); 
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                Cliente cli = new Cliente(id, rg, nome, telefone);
                listaClientes.add(cli);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }

    @Override
    public Cliente procurarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String rg = resultado.getString("rg");
                String telefone = resultado.getString("telefone");

                Cliente pac = new Cliente(id, nome, rg, telefone);

                return pac;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Cliente procurarPorRg(String rg) {
        String sql = "SELECT * FROM CLIENTE WHERE rg = ?";

        try {
            conectar(sql);
            comando.setString(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                Cliente cli = new Cliente(id, rg, nome, telefone);

                return cli;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Cliente> listarPorNome(String nome) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg");
                String nomeX = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                Cliente pac = new Cliente(id, rg, nomeX, telefone);

                listaClientes.add(pac);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }
}
