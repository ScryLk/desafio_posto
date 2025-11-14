package br.com.desafioposto.dao;

import br.com.desafioposto.model.Abastecimento;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbastecimentoDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().connection();
    }

    public void inserir(Abastecimento a) {
        String sql =
                "INSERT INTO abastecimento " +
                "(bomba_id, data_abastecimento, litros, valor_total, preco_litro_praticado) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, a.getBombaId());
            stmt.setTimestamp(2, Timestamp.valueOf(a.getDataAbastecimento()));
            stmt.setDouble(3, a.getLitros());
            stmt.setDouble(4, a.getValorTotal());
            stmt.setDouble(5, a.getPrecoLitroPraticado());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir abastecimento", e);
        }
    }

    public List<Abastecimento> listarTodos() {
        String sql =
                "SELECT id, bomba_id, data_abastecimento, litros, valor_total, preco_litro_praticado " +
                "FROM abastecimento";

        List<Abastecimento> lista = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Abastecimento a = mapearAbastecimento(rs);
                lista.add(a);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar abastecimentos", e);
        }

        return lista;
    }

    public Abastecimento buscarPorId(Integer id) {
        String sql =
                "SELECT id, bomba_id, data_abastecimento, litros, valor_total, preco_litro_praticado " +
                "FROM abastecimento WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearAbastecimento(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar abastecimento por id", e);
        }

        return null;
    }

    public void atualizar(Abastecimento a) {
        String sql =
                "UPDATE abastecimento SET " +
                "bomba_id = ?, " +
                "data_abastecimento = ?, " +
                "litros = ?, " +
                "valor_total = ?, " +
                "preco_litro_praticado = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, a.getBombaId());
            stmt.setTimestamp(2, Timestamp.valueOf(a.getDataAbastecimento()));
            stmt.setDouble(3, a.getLitros());
            stmt.setDouble(4, a.getValorTotal());
            stmt.setDouble(5, a.getPrecoLitroPraticado());
            stmt.setInt(6, a.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar abastecimento", e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM abastecimento WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar abastecimento", e);
        }
    }

    private Abastecimento mapearAbastecimento(ResultSet rs) throws SQLException {
        Abastecimento a = new Abastecimento();

        a.setId(rs.getInt("id"));
        a.setBombaId(rs.getInt("bomba_id"));

        Timestamp ts = rs.getTimestamp("data_abastecimento");
        if (ts != null) {
            a.setDataAbastecimento(ts.toLocalDateTime());
        }

        a.setLitros(rs.getDouble("litros"));
        a.setValorTotal(rs.getDouble("valor_total"));
        a.setPrecoLitroPraticado(rs.getDouble("preco_litro_praticado"));

        return a;
    }
}
