package br.com.senacrs.air.dao.impl_BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.senacrs.air.dominio.Aviao;
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
            String sql = "INSERT INTO VOO (codigo, origem, destino, aviao_nome, horario) "
                    + "VALUES (?,?,?,?,?)";
            
            conectarObtendoId(sql);
            comando.setInt(1, voo.getCodigo());
            comando.setString(2, voo.getOrigem());
            comando.setString(3, voo.getDestino());
            comando.setObject(4, voo.getAviao());
            
            
            Date horario = Date.valueOf(voo.getHorario());
            comando.setDate(5, horario);
            

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

            
            conectar(sql);
            comando.setInt(1, voo.getCodigo());
            comando.setString(2, voo.getOrigem());
            comando.setString(3, voo.getDestino());
            comando.setObject(4, voo.getAviao());
            
            Date horario = Date.valueOf(voo.getHorario());
            comando.setDate(5, horario);
            
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar voo no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Voo> listar() {
        List<Voo> listaVoos = new ArrayList<>();

        String sql = "SELECT * FROM VOO";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int codigo = resultado.getInt("codigo");
                String origem = resultado.getString("origem"); 
                String destino = resultado.getString("destino");
                Aviao aviao = resultado.getObject("aviao", Aviao aviao);
               
                Date dataSql = resultado.getDate("horario");
                LocalDate horario = dataSql.toLocalDate();

                Voo voo = new Voo(id, codigo, origem, destino, aviao, horario);
                listaVoos.add(voo);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os voos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaVoos);
    }

    @Override
    public Voo procurarPorId(int id) {
        String sql = "SELECT * FROM VOO WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String origem = resultado.getString("origem"); 
                String destino = resultado.getString("destino");
                String aviao = resultado.getString("aviao");
               
                Date dataSql = resultado.getDate("horario");
                LocalDate horario = dataSql.toLocalDate();

                Voo voo = new Voo(id, codigo, origem, destino, aviao, horario);

                return voo;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o voo pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Voo procurarPorCodigo(int codigo) {
        String sql = "SELECT * FROM VOO WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String origem = resultado.getString("origem"); 
                String destino = resultado.getString("destino");
                String aviao = resultado.getString("aviao");
               
                Date dataSql = resultado.getDate("horario");
                LocalDate horario = dataSql.toLocalDate();

                Voo voo = new Voo(id, codigo, origem, destino, aviao, horario);

                return voo;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o voo pelo codigo do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Voo> listarPorDestino(String nome) {
        List<Voo> listaVoos = new ArrayList<>();
        String sql = "SELECT * FROM VOO WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int codigo = resultado.getInt("codigo");
                String origem = resultado.getString("origem"); 
                String destino = resultado.getString("destino");
                String aviao = resultado.getString("aviao");
               
                Date dataSql = resultado.getDate("horario");
                LocalDate horario = dataSql.toLocalDate();

                Voo voo = new Voo(id, codigo, origem, destino, aviao, horario);

                listaVoos.add(voo);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os voos pelo destino no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaVoos);
    }
}
