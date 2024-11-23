package com.projeto.entities;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Representa um fornecedor no sistema.
 * Contém informações como ID, CNPJ, nome e email do fornecedor.
 */
public class Supplier {
    private Integer id;
    private String cnpj;
    private String name;
    private String email;

    // Expressão regular para validação de email
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    /**
     * Construtor padrão.
     */
    public Supplier() {
    }

    /**
     * Construtor com parâmetros para inicialização de um fornecedor.
     *
     * @param id O identificador único do fornecedor.
     * @param cnpj O CNPJ do fornecedor.
     * @param name O nome do fornecedor.
     * @param email O email do fornecedor.
     */
    public Supplier(Integer id, String cnpj, String name, String email) {
        setId(id);
        setCnpj(cnpj);
        setName(name);
        setEmail(email);
    }

    // Getters e Setters com validação

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ cannot be null or empty");
        }
        if (!cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("Invalid CNPJ format. CNPJ must have 14 digits.");
        }
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + id + ", cnpj=" + cnpj + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Supplier other = (Supplier) obj;
        return Objects.equals(id, other.id);
    }
}
