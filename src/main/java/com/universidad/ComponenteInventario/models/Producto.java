package com.universidad.ComponenteInventario.models;

import io.swagger.v3.oas.annotations.media.Schema;


public class Producto {

    @Schema(description = "ID único del producto", example = "9")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Licuadora Oster")
    private String nombreProducto;

    @Schema(description = "Precio unitario del producto", example = "89.99")
    private double precio;

    @Schema(description = "Cantidad disponible en el inventario", example = "10")
    private int cantidad;


    public Producto() {}

    public Producto(Long id, String nombreProducto, double precio, int cantidad) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
