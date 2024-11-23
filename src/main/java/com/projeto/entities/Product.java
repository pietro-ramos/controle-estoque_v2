package com.projeto.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer supplierId;

    public Product() {}

    public Product(Integer id, String name, BigDecimal price, Integer supplierId) {
        setId(id);
        setName(name);
        setPrice(price);
        setSupplierId(supplierId);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a non-negative number");
        }
        this.price = price;
    }

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", name='" + name + "', price=" + price + ", supplierId=" + supplierId + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, supplierId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product that = (Product) obj;
        return Objects.equals(id, that.id);
    }
}