package br.com.desafioposto.dao;

import br.com.desafioposto.model.TipoCombustivel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoCombustivelDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().connection();
    }

    public void inserir(TipoCombustivel tipo) {
        String sql = "INSERT INTO tipo_combustivel (nome, preco_litro) VALUES (?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipo.getNome());
            stmt.setDouble(2, tipo.getPrecoLitro());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tipo.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tipo de combustível", e);
        }
    }

    public List<TipoCombustivel> listarTodos() {
        String sql = "SELECT id, nome, preco_litro FROM tipo_combustivel";
        List<TipoCombustivel> lista = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoCombustivel tipo = new TipoCombustivel();
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                tipo.setPrecoLitro(rs.getDouble("preco_litro"));
                lista.add(tipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tipos de combustível", e);
        }

        return lista;
    }

    public TipoCombustivel buscarPorId(Integer id) {
        String sql = "SELECT id, nome, preco_litro FROM tipo_combustivel WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoCombustivel tipo = new TipoCombustivel();
                    tipo.setId(rs.getInt("id"));
                    tipo.setNome(rs.getString("nome"));
                    tipo.setPrecoLitro(rs.getDouble("preco_litro"));
                    return tipo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tipo de combustível por id", e);
        }

        return null;
    }

    public void atualizar(TipoCombustivel tipo) {
        String sql = "UPDATE tipo_combustivel SET nome = ?, preco_litro = ? WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, tipo.getNome());
            stmt.setDouble(2, tipo.getPrecoLitro());
            stmt.setInt(3, tipo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tipo de combustível", e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM tipo_combustivel WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tipo de combustível", e);
        }
    }
}
