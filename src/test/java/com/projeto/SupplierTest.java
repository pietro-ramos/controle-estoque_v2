package com.projeto;

import com.projeto.entities.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    @Test
    void testSupplierCreation() {
        Supplier supplier = new Supplier(1, "12345678000195", "Fornecedor A", "fornecedor@example.com");
        assertNotNull(supplier);
        assertEquals(1, supplier.getId());
        assertEquals("12345678000195", supplier.getCnpj());
        assertEquals("Fornecedor A", supplier.getName());
        assertEquals("fornecedor@example.com", supplier.getEmail());
    }

    @Test
    void testSetId() {
        Supplier supplier = new Supplier();
        supplier.setId(2);
        assertEquals(2, supplier.getId());
        assertThrows(IllegalArgumentException.class, () -> supplier.setId(-1));
    }

    @Test
    void testSetCnpj() {
        Supplier supplier = new Supplier();
        supplier.setCnpj("12345678000195");
        assertEquals("12345678000195", supplier.getCnpj());
        assertThrows(IllegalArgumentException.class, () -> supplier.setCnpj("invalid"));
    }

    @Test
    void testSetName() {
        Supplier supplier = new Supplier();
        supplier.setName("Fornecedor B");
        assertEquals("Fornecedor B", supplier.getName());
        assertThrows(IllegalArgumentException.class, () -> supplier.setName(""));
    }

    @Test
    void testSetEmail() {
        Supplier supplier = new Supplier();
        supplier.setEmail("fornecedorB@example.com");
        assertEquals("fornecedorB@example.com", supplier.getEmail());
        assertThrows(IllegalArgumentException.class, () -> supplier.setEmail("invalid-email"));
    }
}
