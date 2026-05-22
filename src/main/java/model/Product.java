package model;

import java.time.LocalDate;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private String type; // Categorías requeridas (pueden ser Cajas, Gestión de Cuentas, etc. según la UI de la guía)
    private LocalDate date;

    public Product(int id, String name, double price, int stock, String type, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.date = date;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id == product.id; // Identificamos productos por su ID único
    }

    @Override
    public String toString() {
        return "Prod[ID=" + id + ", Nombre=" + name + ", Precio=$" + price + ", Tipo=" + type + "]";
    }

}
