package ar.org.centro8.curso.java.aplicaciones.entities;

import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;

public class Articulo {
    private int id;
    private String descripcion;
    private Tipo tipo;
    private String color;
    private String talle_num;
    private int stock;
    private int stockMin;
    private int stockMax;
    private double costo;
    private double precio;
    private Temporada temporada;

    public Articulo() {
    }

    public Articulo(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public Articulo(int id, String descripcion, double costo) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public Articulo(String descripcion, Tipo tipo, String color, String talle_num, int stock, int stockMin, int stockMax, double costo, double precio, Temporada temporada) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.color = color;
        this.talle_num = talle_num;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.costo = costo;
        this.precio = precio;
        this.temporada = temporada;
    }
     public Articulo(String descripcion, String tipo, String color, String talle_num, int stock, int stockMin, int stockMax, double costo, double precio, Temporada temporada) {
        this.descripcion = descripcion;
        this.tipo = Tipo.valueOf(tipo);
        this.color = color;
        this.talle_num = talle_num;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.costo = costo;
        this.precio = precio;
        this.temporada = temporada;
    }
   
   

    public Articulo(int id, String descripcion, Tipo tipo, String color, String talle_num, int stock, int stockMin, int stockMax, double costo, double precio, Temporada temporada) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.color = color;
        this.talle_num = talle_num;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.costo = costo;
        this.precio = precio;
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", color=" + color + ", talle_num=" + talle_num + ", stock=" + stock + ", stockMin=" + stockMin + ", stockMax=" + stockMax + ", costo=" + costo + ", precio=" + precio + ", temporada=" + temporada + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalle_num() {
        return talle_num;
    }

    public void setTalle_num(String talle_num) {
        this.talle_num = talle_num;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
    
}
