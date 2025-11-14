package br.com.desafioposto.dao;

import br.com.desafioposto.model.Bomba;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BombaDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().connection();
    }
    
    public void inserir(Bomba bomba) {
        String sql = "INSERT INTO bomba (nome, tipo_combustivel_id) VALUES (?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bomba.getNome());
            stmt.setInt(2, bomba.getTipoCombustivelId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bomba.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir bomba", e);
        }
    }

    public List<Bomba> listarTodas() {
        String sql = "SELECT id, nome, tipo_combustivel_id FROM bomba";
        List<Bomba> lista = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bomba bomba = new Bomba();
                bomba.setId(rs.getInt("id"));
                bomba.setNome(rs.getString("nome"));
                bomba.setTipoCombustivelId(rs.getInt("tipo_combustivel_id"));
                lista.add(bomba);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar bombas", e);
        }

        return lista;
    }

    public Bomba buscarPorId(Integer id) {
        String sql = "SELECT id, nome, tipo_combustivel_id FROM bomba WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bomba bomba = new Bomba();
                    bomba.setId(rs.getInt("id"));
                    bomba.setNome(rs.getString("nome"));
                    bomba.setTipoCombustivelId(rs.getInt("tipo_combustivel_id"));
                    return bomba;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar bomba por id", e);
        }

        return null;
    }

    public void atualizar(Bomba bomba) {
        String sql = "UPDATE bomba SET nome = ?, tipo_combustivel_id = ? WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, bomba.getNome());
            stmt.setInt(2, bomba.getTipoCombustivelId());
            stmt.setInt(3, bomba.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar bomba", e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM bomba WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar bomba", e);
        }
    }
}
