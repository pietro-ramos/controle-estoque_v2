package com.projeto.DAO.impl;

import com.projeto.DAO.interfaces.ObjectDAO;
import com.projeto.entities.Supplier;

import java.com.projeto.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do DAO para a entidade Supplier.
 * Provê métodos para operações CRUD sobre fornecedores.
 */
public class SupplierDAOImpl implements ObjectDAO<Supplier> {

    /**
     * Adiciona um novo fornecedor no banco de dados.
     *
     * @param supplier O fornecedor a ser adicionado.
     * @throws IllegalArgumentException Se o fornecedor ou o ID do fornecedor for nulo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    @Override
    public void addObject(Supplier supplier) throws IllegalArgumentException, SQLException {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }

        String sql = "INSERT INTO suppliers (id, cnpj, name, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplier.getId());
            stmt.setString(2, supplier.getCnpj());
            stmt.setString(3, supplier.getName());
            stmt.setString(4, supplier.getEmail());
            stmt.executeUpdate();
        }
    }

    @Override
    public Supplier getObjectById(Integer id) throws IllegalArgumentException, SQLException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                            rs.getInt("id"),
                            rs.getString("cnpj"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public Supplier getObjectByString(String cnpj) throws IllegalArgumentException, SQLException {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ cannot be null");
        }

        String sql = "SELECT * FROM suppliers WHERE cnpj = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                            rs.getInt("id"),
                            rs.getString("cnpj"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Supplier> getAllObjects() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("cnpj"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }
        return suppliers;
    }

    @Override
    public void removeObject(Supplier supplier) throws IllegalArgumentException, SQLException {
        if (supplier == null || supplier.getId() == null) {
            throw new IllegalArgumentException("Supplier or Supplier ID is null");
        }

        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplier.getId());
            stmt.executeUpdate();
        }
    }
}
