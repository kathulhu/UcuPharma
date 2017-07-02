/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author kathe
 */
public class Venta {
    
    private static final AtomicInteger count = new AtomicInteger(0);     
    
    private final int id_venta;
    private Date fecha_venta;
    private Comparable id_producto;
    private int precio;
    private int cantidad;
    private int total;
    
    public void setFechaVenta(Date fecha_venta){
        this.fecha_venta = fecha_venta;
    }
    
    public void setIdProducto(int id_producto){
        this.id_producto = id_producto;
    }
    
    public void setPrecio(int precio){
        this.precio = precio;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    
    public int getIdVenta(){
        return this.id_venta;
    }
    
    public Date getFechaVenta(){
        return this.fecha_venta;
    }
    
    public Comparable getIdProducto(){
        return this.id_producto;
    }
    
    public int getPrecio(){
        return this.precio;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public Venta(Date fecha_venta, Comparable id_producto, int precio, int cantidad){
        this.id_venta = count.incrementAndGet(); 
        this.fecha_venta = fecha_venta;
        this.id_producto = id_producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = cantidad * precio;
    }
    
}
