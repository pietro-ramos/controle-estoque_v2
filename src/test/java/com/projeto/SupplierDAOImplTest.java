package com.projeto;

import com.projeto.DAO.impl.SupplierDAOImpl;
import com.projeto.entities.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierDAOImplTest {

    private SupplierDAOImpl supplierDAO;
    private Connection connection;

    @BeforeEach
    void setUp() {
        supplierDAO = new SupplierDAOImpl();
        connection = mock(Connection.class);
        DatabaseConnection.setConnection(connection); // Supondo que você tenha um método para definir a conexão mock
    }

    @Test
    void testRegisterObject() throws Exception {
        Supplier supplier = new Supplier(1, "12345678000195", "Fornecedor C", "fornecedorC@example.com");

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);

        supplierDAO.registerObject(supplier);

        verify(pstmt).setInt(1, supplier.getId());
        verify(pstmt).setString(2, supplier.getCnpj());
        verify(pstmt).setString(3, supplier.getName());
        verify(pstmt).setString(4, supplier.getEmail());
        verify(pstmt).executeUpdate();
    }

    @Test
    void testSearchObjectById() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(mock(PreparedStatement.class));

        Supplier supplier = supplierDAO.searchObjectById(1);
        assertNotNull(supplier);
    }

    @Test
    void testListObject() throws Exception {
        when(connection.createStatement()).thenReturn(mock(Statement.class));
        when(connection.createStatement().executeQuery(anyString())).thenReturn(mock(ResultSet.class));

        List<Supplier> suppliers = supplierDAO.listObject();
        assertNotNull(suppliers);
        // Adicione verificações adicionais conforme necessário
    }

    @Test
    void testRemoveObject() throws Exception {
        Supplier supplier = new Supplier(1, "12345678000195", "Fornecedor D", "fornecedorD@example.com");

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);

        supplierDAO.removeObject(supplier);

        verify(pstmt).setInt(1, supplier.getId());
        verify(pstmt).executeUpdate();
    }
}
