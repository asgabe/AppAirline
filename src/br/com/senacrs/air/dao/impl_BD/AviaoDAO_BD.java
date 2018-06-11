package br.com.senacrs.air.dao.impl_BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.senacrs.air.dominio.Aviao;
import br.com.senacrs.air.dao.AviaoDAO;

public class AviaoDAO_BD extends DaoBd<Aviao> implements AviaoDAO {

    @Override
    public void salvar(Aviao aviao) {
        int id = 0;

        try {
            String sql = "INSERT INTO AVIAO (codigo, nome, qtd_assento) "
                    + "VALUES (?,?,?)";

            conectarObtendoId(sql);
            comando.setInt(1, aviao.getCodigo());
            comando.setString(2, aviao.getNome());
            comando.setInt(3, aviao.getQtdAssento());
            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();

            if (resultado.next()) {
                id = resultado.getInt(1);
                aviao.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar aviao no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Aviao aviao) {
        try {
            String sql = "DELETE FROM AVIAO WHERE id = ?";

            conectar(sql);
            comando.setInt(1, aviao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar avião no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Aviao aviao) {
        try {
            String sql = "UPDATE AVIAO SET codigo=?, nome=?, qtd_assento=?"
                    + "WHERE id=?";

            conectar(sql);
            
            comando.setInt(1, aviao.getCodigo());
            comando.setString(2, aviao.getNome());
            comando.setInt(3, aviao.getQtdAssento());
            comando.setInt(4, aviao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar avião no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Aviao> listar() {
        List<Aviao> listaAvioes = new ArrayList<>();

        String sql = "SELECT * FROM AVIAO";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id"); 
                int codigo = resultado.getInt("codigo"); 
                String nome = resultado.getString("nome");
                int qtdAssento = resultado.getInt("qtd_assento");

                Aviao avi = new Aviao(id, codigo, nome, qtdAssento);
                listaAvioes.add(avi);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os aviões no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAvioes);
    }

    @Override
    public Aviao procurarPorId(int id) {
        String sql = "SELECT * FROM AVIAO WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                int qtdAssento = resultado.getInt("qtd_assento");

                Aviao avi = new Aviao(id, codigo, nome, qtdAssento);

                return avi;

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
    public Aviao procurarPorCodigo(int codigo) {
        String sql = "SELECT * FROM AVIAO WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                int cod = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                int qtdAssento = resultado.getInt("qtd_assento");

                Aviao avi = new Aviao(id, cod, nome, qtdAssento);
                
                return avi;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar aviao pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Aviao> listarPorNome(String nome) {
        List<Aviao> listaAvioes = new ArrayList<>();
        String sql = "SELECT * FROM AVIAO WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int codigo = resultado.getInt("codigo");
                String nomeX = resultado.getString("nome");
                int qtdAssento = resultado.getInt("telefone");

                Aviao avi = new Aviao(id, codigo, nomeX, qtdAssento);

                listaAvioes.add(avi);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os aviões pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAvioes);
    }
}
